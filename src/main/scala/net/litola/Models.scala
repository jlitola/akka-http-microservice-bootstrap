package net.litola

import play.api.libs.json.{ Json, OFormat }

object Models {
  case class ExampleData(example: String)

  object Formats {
    val exampleDataFormat: OFormat[ExampleData] = Json.format[ExampleData]
    object Implicits {
      implicit val implicitExampleDataFormat: OFormat[ExampleData] = exampleDataFormat
    }
  }
}
