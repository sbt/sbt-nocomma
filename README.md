sbt-nocomma
===========

[![CI](https://github.com/sbt/sbt-nocomma/actions/workflows/ci.yml/badge.svg)](https://github.com/sbt/sbt-nocomma/actions/workflows/ci.yml)
[![Latest version](https://img.shields.io/github/tag/sbt/sbt-nocomma.svg)](https://index.scala-lang.org/sbt/sbt-nocomma)

sbt-nocomma reduces commas from your build.sbt.

setup
-----

sbt-nocomma is available for sbt 1.x. Put the following to `project/plugins.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-nocomma" % "x.y.z")
```

usage
-----

This introduces `nocomma { ... }` macro that returns `Vector[Setting[_]]`.


```scala
import Dependencies._

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.0.1"
ThisBuild / version      := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(nocomma {

    name := "Hello"
    scalacOptions += "-Ykind-projector"

    // comment works
    libraryDependencies += scalaTest % Test
  })
```

Note the lack of commas at the end of each line.
