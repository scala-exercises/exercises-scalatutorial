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

class TermsAndTypesSpec extends RefSpec with Checkers {

  def `check evaluation`(): Unit =
    check(Test.testSuccess(TermsAndTypes.evaluation _, 3 :: "Hello, Scala!" :: HNil))

  def `check methods`(): Unit =
    check(Test.testSuccess(TermsAndTypes.methods _, "HELLO, SCALA!" :: 42 :: HNil))

  def `check more methods`(): Unit =
    check(Test.testSuccess(TermsAndTypes.moreMethods _, "10" :: false :: "ba" :: HNil))

}
