/*
 * scala-exercises - exercises-scalatutorial
 * Copyright (C) 2015-2016 47 Degrees, LLC. <http://www.47deg.com>
 */

package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class ObjectOrientedProgrammingSpec extends Spec with Checkers {

  def `check dynamic binding`: Unit =
    check(Test.testSuccess(ObjectOrientedProgramming.dynamicBinding _, false :: true :: HNil))

  def `check reducer`: Unit =
    check(Test.testSuccess(ObjectOrientedProgramming.reducer _, 24 :: 10 :: HNil))

}
