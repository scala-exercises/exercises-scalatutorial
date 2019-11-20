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

class DefinitionsAndEvaluationSpec extends RefSpec with Checkers {

  def `check using square`(): Unit =
    check(Test.testSuccess(DefinitionsAndEvaluation.usingSquare _, 9.0 :: HNil))

  def `check area exercise`(): Unit =
    check(Test.testSuccess(DefinitionsAndEvaluation.areaExercise _, 314.159 :: HNil))

  def `check triangle area`(): Unit =
    check(Test.testSuccess(DefinitionsAndEvaluation.triangleAreaExercise _, 2.0 :: 15.0 :: HNil))

}
