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

class ObjectOrientedProgrammingSpec extends RefSpec with Checkers {

  def `check dynamic binding`(): Unit =
    check(Test.testSuccess(ObjectOrientedProgramming.dynamicBinding _, false :: true :: HNil))

  def `check reducer`(): Unit =
    check(Test.testSuccess(ObjectOrientedProgramming.reducer _, 24 :: 10 :: HNil))

}
