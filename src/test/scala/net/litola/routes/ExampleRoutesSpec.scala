package net.litola.routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.google.inject._
import net.litola.JsonSupport
import net.litola.modules.TestModule
import org.scalamock.scalatest.MockFactory
import org.scalatest.{ Matchers, WordSpec }
import play.api.libs.json.{ JsObject, Json }

class ExampleRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with MockFactory with JsonSupport {
  private val injector = Guice.createInjector(Stage.DEVELOPMENT, new TestModule())
  private def route = injector.getInstance(classOf[ExampleRoutes])

  "ExampleRoutes" should {
    "return ok" in {
      Get("/examples") ~> route.all ~> check {
        entityAs[JsObject] shouldEqual Json.obj("example" -> "value")
      }
    }
  }
}
