package sbtnocomma

import sbt.*

object NoCommaPlugin extends AutoPlugin {
  override def trigger = allRequirements
  object autoImport {
    inline def nocomma(inline a: SettingsDefinition): Vector[Setting[?]] =
      ${ NoComma.nocommaImpl('a) }
  }
}
