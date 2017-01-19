package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class SyntacticConveniencesSpec extends Spec with Checkers {

  def `check string interpolation`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.stringInterpolation _, "Hello, Functional Programming!" :: HNil))
  }

  def `check string interpolation2`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.stringInterpolation2 _, "Hello, SCALA!" :: HNil))
  }

  def `check tuples`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.tupleExtraction _, "foo" :: HNil))
  }

  def `check tuples2`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.tupleExtraction2 _, "foo" :: HNil))
  }

  def `check tuples manipulation`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.tupleManipulation _, "foo" :: HNil))
  }

  def `check default parameters`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.defaultParameters _, 1 :: HNil))
  }

  def `check repeated parameters`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.repeatedParameters _, 2.0 :: HNil))
  }

  def `check type alias`: Unit = {
    check(Test.testSuccess(SyntacticConveniences.typeAlias _, (Right((2, 0)): Either[String, (Int, Int)]) :: HNil))
  }

}
