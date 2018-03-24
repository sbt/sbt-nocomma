lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"
lazy val check = taskKey[Unit]("")

val commonSettings = Seq(
  scalacOptions += "-deprecation"
)

lazy val root = (project in file("."))
  .settings(nocomma {
    commonSettings
    ThisBuild / organization := "com.eed3si9n"
    ThisBuild / scalaVersion := "2.12.4"

    name := "foo"

    sbtPlugin := true

    libraryDependencies ++= Vector(scalaTest)
    libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value

    // check
    check := {
      assert(name.value == "foo", s"unexpected name: ${name.value}")
    }
  })
