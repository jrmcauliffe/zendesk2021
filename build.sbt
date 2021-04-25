name := "zendesk2021"

version := "1.0"

scalaVersion := "2.13.3"

mainClass in assembly := Some("org.birchavenue.zendesk2021.Main")

scalacOptions += "-deprecation"

libraryDependencies ++= Seq("org.scalatest"    %% "scalatest"     % "3.2.7" % "test",
                            "org.scalactic"    %% "scalactic"     % "3.2.7" % "test",
                            "io.circe"         %% "circe-core"    % "0.12.3",
                            "io.circe"         %% "circe-generic" % "0.12.3",
                            "io.circe"         %% "circe-parser"  % "0.12.3",
                            "com.github.scopt" %% "scopt"         % "4.0.1")
