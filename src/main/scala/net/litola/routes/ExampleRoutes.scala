package net.litola.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import javax.inject.{ Inject, Singleton }
import net.litola.JsonSupport
import net.litola.Models.ExampleData

import scala.concurrent.ExecutionContext

@Singleton
class ExampleRoutes @Inject() (system: ActorSystem, implicit val ec: ExecutionContext) extends JsonSupport {
  import net.litola.Models.Formats.Implicits._

  val all: Route =
    pathPrefix("examples") {
      get {
        complete(ExampleData("value"))
      }
    }
}
