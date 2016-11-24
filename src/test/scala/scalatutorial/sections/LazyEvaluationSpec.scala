package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class LazyEvaluationSpec extends Spec with Checkers {

  def `check stream range`: Unit = {
    check(Test.testSuccess(LazyEvaluation.streamRangeExercise _, 3 :: HNil))
  }

  def `check lazy val`: Unit = {
    check(Test.testSuccess(LazyEvaluation.lazyVal _, "xzyz" :: HNil))
  }

}
