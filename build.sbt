lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"

lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    ThisBuild / organization := "com.eed3si9n",
    ThisBuild / scalaVersion := "2.12.4",
    ThisBuild / scalafmtVersion := "1.2.0",
    ThisBuild / scalafmtOnCompile := true,
    ThisBuild / Sbt / scalafmtOnCompile := false,

    ThisBuild / version := "0.1.0",

    name := "sbt-nocomma",
    description := "sbt plugin to reduce commas from your build.sbt",
    licenses := Seq("Apache v2" -> url("https://github.com/sbt/sbt-nocomma/blob/master/LICENSE")),
    scmInfo := Some(ScmInfo(url("https://github.com/sbt/sbt-nocomma"), "git@github.com:sbt/sbt-nocomma.git")),

    sbtPlugin := true,
    libraryDependencies ++= Vector(scalaTest),
    libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value,
    scriptedLaunchOpts := { scriptedLaunchOpts.value ++
      Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
    },
    scriptedBufferLog := false,

    publishMavenStyle := false,
    bintrayOrganization in bintray := None,
    bintrayRepository := "sbt-plugins",
  )
