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

class ImperativeProgrammingSpec extends RefSpec with Checkers {

  def `check observational equivalence`(): Unit =
    check(Test.testSuccess(ImperativeProgramming.observationalEquivalence _, 10 :: HNil))

}
