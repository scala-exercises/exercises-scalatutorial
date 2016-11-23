package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class HigherOrderFunctionsSpec extends Spec with Checkers {

  def `check tail rec sum`: Unit = {
    check(Test.testSuccess(HigherOrderFunctions.tailRecSum _, 1 :: 0 :: HNil))
  }

}
