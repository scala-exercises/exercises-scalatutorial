package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class ImperativeProgrammingSpec extends Spec with Checkers {

  def `check observational equivalence`: Unit = {
    check(Test.testSuccess(ImperativeProgramming.observationalEquivalence _, 10 :: HNil))
  }

}
