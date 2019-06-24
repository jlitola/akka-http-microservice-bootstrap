package net.litola

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.google.inject.{ Guice, Stage }
import net.litola.modules.BootstrapModule
import net.litola.routes.ExampleRoutes

import scala.concurrent.duration.Duration
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.util.{ Failure, Success }

object Server extends App {
  val injector = Guice.createInjector(Stage.PRODUCTION, new BootstrapModule())
  implicit val system: ActorSystem = injector.getInstance(classOf[ActorSystem])
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val exampleRoutes = injector.getInstance(classOf[ExampleRoutes])
  val routes: Route = concat(exampleRoutes.all)

  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "localhost", 12345)

  serverBinding.onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}
