package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class ClassesVsCaseClassesSpec extends Spec with Checkers {

  def `check creation and manipulation`: Unit = {
    check(Test.testSuccess(ClassesVsCaseClasses.creationAndManipulation _, "C" :: HNil))
  }

  def `check equality`: Unit = {
    check(Test.testSuccess(ClassesVsCaseClasses.equality _, false :: true :: HNil))
  }

}
