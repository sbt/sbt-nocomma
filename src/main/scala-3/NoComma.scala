package sbtnocomma

import sbt.*
import scala.quoted.Expr
import scala.quoted.Quotes
import scala.quoted.Varargs

object NoComma {
  inline def nocomma(inline a: SettingsDefinition): Vector[Setting[?]] =
    ${ nocommaImpl('a) }

  def nocommaImpl(a: Expr[SettingsDefinition])(using q: Quotes): Expr[Vector[Setting[?]]] = {
    import q.reflect.*
    a.asTerm match {
      case Inlined(_, _, t: Block) =>
        val values = (t.statements :+ t.expr).map {
          case t: Term =>
            t.asExpr match {
              case '{ $x: Seq[Setting[?]] } =>
                '{ Def.SettingList($x) }
              case '{ $x: Setting[?] } =>
                '{ Def.SettingList($x :: Nil) }
              case '{ $x: SettingsDefinition } =>
                x
              case _ =>
                report.errorAndAbort("unexpected")
            }
          case _ =>
            report.errorAndAbort("unexpected")
        }
        '{ Vector[SettingsDefinition](${ Varargs(values) }*).flatMap(_.settings) }
      case other =>
        report.errorAndAbort("unexpected")
    }
  }
}
