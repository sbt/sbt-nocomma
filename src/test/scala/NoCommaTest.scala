import org.scalatest.flatspec._

import sbtnocomma.NoComma._
import sbt._, Keys._
import sjsonnew.BasicJsonProtocol._

class NoCommaSpec extends AnyFlatSpec {
  "nocomma" should "expand to a Vector" in {
    val seq = Seq(
      scalacOptions += "-deprecation"
    )
    val xs = nocomma {
      seq
      name := "something"
      ThisBuild / organization := "com.example"
    }
    val ys = Vector[SettingsDefinition](
      seq,
      name := "something",
      ThisBuild / organization := "com.example",
    )
    assert((xs map { _.key }) === (ys flatMap { _.settings.map(_.key) }))
  }

  /*
  "nocomma" should "expand to a Vector" in {
    assert(List(nocomma {
      f1

      2; 3

      if (true) 4
      else 5
    }: _*) === List(1, 2, 3, 4))
  }

  it should "expand even when parameters do not line up" in {
    assert(nocomma[Any] {
      1
      "b"
      3
    } == Vector(1, "b", 3))
  }

  def f1 = 1
   */
}
