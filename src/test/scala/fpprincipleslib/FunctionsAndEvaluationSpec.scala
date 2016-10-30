package fpprincipleslib

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class FunctionsAndEvaluationSectionSpec extends Spec with Checkers {
  def `function asserts` = {
    check(Test.testSuccess(FunctionsAndEvaluationSection.functionAssert _, true :: HNil))
  }

  def `function false asserts` = {
    check(Test.testSuccess(FunctionsAndEvaluationSection.functionFalseAssert _, false :: HNil))
  }
}