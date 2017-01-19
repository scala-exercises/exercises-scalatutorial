package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class DefinitionsAndEvaluationSpec extends Spec with Checkers {

  def `check using square`: Unit = {
    check(Test.testSuccess(DefinitionsAndEvaluation.usingSquare _, 9.0 :: HNil))
  }

  def `check area exercise`: Unit = {
    check(Test.testSuccess(DefinitionsAndEvaluation.areaExercise _, 314.159 :: HNil))
  }

  def `check triangle area`: Unit = {
    check(Test.testSuccess(DefinitionsAndEvaluation.triangleAreaExercise _, 2.0 :: 15.0 :: HNil))
  }

}
