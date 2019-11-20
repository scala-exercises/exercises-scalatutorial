/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

import org.scalacheck.ScalacheckShapeless._
import org.scalaexercises.Test
import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers
import shapeless.HNil

class LexicalScopesSpec extends RefSpec with Checkers {

  def `check scope rules`(): Unit =
    check(Test.testSuccess(LexicalScopes.scopeRules _, 16 :: HNil))

  def `check objects scopes`(): Unit =
    check(Test.testSuccess(LexicalScopes.objectScopes _, 3 :: HNil))

}
