package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class StructuringInformationSpec extends Spec with Checkers {

  def `check case class projection`: Unit = {
    check(Test.testSuccess(StructuringInformation.caseClassProjection _, "Quarter" :: 3 :: HNil))
  }

  def `check case class equals`: Unit = {
    check(Test.testSuccess(StructuringInformation.caseClassEquals _, true :: false :: HNil))
  }



}
