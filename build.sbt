lazy val akkaHttpVersion = "10.1.8"
lazy val akkaVersion    = "2.5.23"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "net.litola",
      scalaVersion    := "2.12.8"
    )),
    name := "Akka HTTP Microservice bootstrap",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
			"de.heikoseeberger" %% "akka-http-play-json"  % "1.26.0",
      "com.google.inject" %  "guice"                % "4.2.2",
      "org.slf4j"         %  "slf4j-api"            % "1.7.26",
      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.8"         % Test,
      "org.scalamock"     %% "scalamock"            % "4.2.0"         % Test
    )
  )
