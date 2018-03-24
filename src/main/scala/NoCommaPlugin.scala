package sbtnocomma

import sbt._
import scala.language.experimental.macros

object NoCommaPlugin extends AutoPlugin {
  override def trigger = allRequirements
  object autoImport {
    def nocomma(a: SettingsDefinition): Vector[Setting[_]] =
      macro NoComma.nocommaImpl
  }
}
