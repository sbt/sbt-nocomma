lazy val scalaTest = "org.scalatest" %% "scalatest-flatspec" % "3.2.19"

organization := "com.eed3si9n"
scalaVersion := "3.9.0"
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
    scriptedSbt := (if (scalaBinaryVersion.value == "2.12") "1.13.1" else "2.1.0"),
    scriptedBufferLog := false,
    crossScalaVersions := Seq("3.9.0", "2.12.21"),
    scalacOptions ++= {
      scalaBinaryVersion.value match {
        case "2.12" =>
          Seq("-Xsource:3", "-release:8")
        case _ =>
          Nil
      }
    },
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.10.9"
        case _      => "2.1.0"
      }
    },
  )
