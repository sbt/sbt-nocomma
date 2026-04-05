lazy val scalaTest = "org.scalatest" %% "scalatest-flatspec" % "3.2.19"

organization := "com.eed3si9n"
scalaVersion := "3.8.2"
scalafmtOnCompile := true

lazy val root = rootProject
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-nocomma",
    libraryDependencies ++= Vector(scalaTest % Test),
    libraryDependencies ++= {
      scalaBinaryVersion.value match {
        case "3" =>
          Nil
        case _ =>
          Seq("org.scala-lang" % "scala-compiler" % scalaVersion.value)
      }
    },
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false,
    crossScalaVersions += "2.12.21",
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" =>
          Seq("-release:8")
        case _ =>
          Nil
      }
    },
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.10.9"
        case _      => "2.0.0-RC10"
      }
    },
  )
