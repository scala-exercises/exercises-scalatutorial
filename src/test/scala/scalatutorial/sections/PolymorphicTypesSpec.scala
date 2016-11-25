package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class PolymorphicTypesSpec extends Spec with Checkers {

  def `check size`: Unit = {
    check(Test.testSuccess(PolymorphicTypes.sizeExercise _, 0 :: 1 :: HNil))
  }

}
