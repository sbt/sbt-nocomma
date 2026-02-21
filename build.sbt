lazy val scalaTest = "org.scalatest" %% "scalatest-flatspec" % "3.2.19"

ThisBuild / organization := "com.eed3si9n"
ThisBuild / scalaVersion := "2.12.21"
ThisBuild / scalafmtOnCompile := true
ThisBuild / version := "0.1.3-SNAPSHOT"

lazy val root = (project in file("."))
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
    crossScalaVersions += "3.8.1",
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
        case "2.12" => "1.4.9"
        case _      => "2.0.0-RC9"
      }
    },
  )

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/sbt/sbt-nocomma"),
    "scm:git@github.com:sbt/sbt-nocomma.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "eed3si9n",
    name  = "Eugene Yokota",
    email = "@eed3si9n",
    url   = url("https://eed3si9n.com/")
  ),
)
ThisBuild / description := "sbt plugin to reduce commas from your build.sbt"
ThisBuild / homepage := Some(url("https://github.com/sbt/sbt-nocomma"))
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  val v                = (ThisBuild / version).value
  if (v.endsWith("SNAPSHOT")) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
}
ThisBuild / publishMavenStyle := true
