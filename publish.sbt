scmInfo := Some(
  ScmInfo(
    url("https://github.com/sbt/sbt-nocomma"),
    "scm:git@github.com:sbt/sbt-nocomma.git"
  )
)
developers := List(
  Developer(
    id    = "eed3si9n",
    name  = "Eugene Yokota",
    email = "@eed3si9n",
    url   = url("https://eed3si9n.com/")
  ),
)
description := "sbt plugin to reduce commas from your build.sbt"
homepage := Some(url("https://github.com/sbt/sbt-nocomma"))
licenses := Seq(License.Apache2)
pomIncludeRepository := { _ => false }
publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  val v                = (ThisBuild / version).value
  if (v.endsWith("SNAPSHOT")) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
}
publishMavenStyle := true
