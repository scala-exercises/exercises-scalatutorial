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

class LazyEvaluationSpec extends RefSpec with Checkers {

  def `check stream range`(): Unit =
    check(Test.testSuccess(LazyEvaluation.streamRangeExercise _, 3 :: HNil))

  def `check lazy val`(): Unit =
    check(Test.testSuccess(LazyEvaluation.lazyVal _, "xzyz" :: HNil))

}
