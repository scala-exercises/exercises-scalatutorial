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

class PolymorphicTypesSpec extends Spec with Checkers {

  def `check size`: Unit =
    check(Test.testSuccess(PolymorphicTypes.sizeExercise _, 0 :: 1 :: HNil))

}
