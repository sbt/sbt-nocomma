sbt-nocomma
===========

sbt-nocomma reduces commas from your build.sbt.

setup
-----

sbt-nocomma is available for sbt 1.x. Put the following to `project/plugins.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-nocomma" % "0.1.0")
```

usage
-----

This introduces `nocomma { ... }` macro that returns `Vector[Setting[_]]`.


```scala
import Dependencies._

lazy val root = (project in file("."))
  .settings(nocomma {
    ThisBuild / organization := "com.example"
    ThisBuild / scalaVersion := "2.12.4"
    ThisBuild / version      := "0.1.0-SNAPSHOT"

    name := "Hello"

    // comment works
    libraryDependencies += scalaTest % Test
  })
```

Note the lack of commas at the end of each line.
