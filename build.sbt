lazy val scalaTest = "org.scalatest" %% "scalatest-flatspec" % "3.2.19"

ThisBuild / organization := "com.eed3si9n"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / scalafmtOnCompile := true
ThisBuild / version := "0.1.3-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(pomConsistency2021DraftSettings)
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
    crossScalaVersions += "3.3.4",
    (pluginCrossBuild / sbtVersion) := {
      scalaBinaryVersion.value match {
        case "2.12" => "1.2.8"
        case _      => "2.0.0-M2"
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
ThisBuild / licenses := Seq(
  "Apache-2.0" -> url("https://github.com/sbt/sbt-nocomma/blob/master/LICENSE")
)
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

// See https://eed3si9n.com/pom-consistency-for-sbt-plugins
lazy val pomConsistency2021Draft = settingKey[Boolean]("experimental")

/**
 * this is an unofficial experiment to re-publish plugins with better Maven compatibility
 */
def pomConsistency2021DraftSettings: Seq[Setting[_]] = Seq(
  pomConsistency2021Draft := Set("true", "1")(sys.env.get("POM_CONSISTENCY").getOrElse("false")),
  moduleName := {
    if (pomConsistency2021Draft.value)
      sbtPluginModuleName2021Draft(moduleName.value, (pluginCrossBuild / sbtBinaryVersion).value)
    else moduleName.value
  },
  projectID := {
    if (pomConsistency2021Draft.value) sbtPluginExtra2021Draft(projectID.value)
    else projectID.value
  },
)

def sbtPluginModuleName2021Draft(n: String, sbtV: String): String =
  s"""${n}_sbt${if (sbtV == "1.0") "1" else if (sbtV == "2.0") "2" else sbtV}"""

def sbtPluginExtra2021Draft(m: ModuleID): ModuleID =
  m.withExtraAttributes(Map.empty)
    .withCrossVersion(CrossVersion.binary)
