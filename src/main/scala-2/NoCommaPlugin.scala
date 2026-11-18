package sbtnocomma

import sbt.*
import scala.language.experimental.macros


object NoCommaPlugin extends AutoPlugin {
  override def trigger = allRequirements

  object autoImport {
    def nocomma(a: SettingsDefinition): Vector[Setting[?]] =
      macro NoComma.nocommaImpl
  }
}
