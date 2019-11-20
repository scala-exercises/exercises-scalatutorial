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

class PolymorphicTypesSpec extends RefSpec with Checkers {

  def `check size`(): Unit =
    check(Test.testSuccess(PolymorphicTypes.sizeExercise _, 0 :: 1 :: HNil))

}
