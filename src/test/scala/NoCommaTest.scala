import org.scalatest._

import sbtnocomma.NoComma._
import sbt._, Keys._

class NoCommaSpec extends FlatSpec with Matchers {
  "nocomma" should "expand to a Vector" in {
    val xs = nocomma {
      name := "something"
      organization in ThisBuild := "com.example"
    }
    val ys = Vector(
      name := "something",
      organization in ThisBuild := "com.example",
    )
    assert((xs map { _.key }) === (ys map { _.key }) )
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
