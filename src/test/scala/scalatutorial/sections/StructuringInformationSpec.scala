/*
 * scala-exercises - exercises-scalatutorial
 * Copyright (C) 2015-2016 47 Degrees, LLC. <http://www.47deg.com>
 */

package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class StructuringInformationSpec extends Spec with Checkers {

  def `check case class projection`: Unit =
    check(Test.testSuccess(StructuringInformation.caseClassProjection _, "Quarter" :: 3 :: HNil))

  def `check case class equals`: Unit =
    check(Test.testSuccess(StructuringInformation.caseClassEquals _, true :: false :: HNil))

  def `check adts`: Unit =
    check(Test.testSuccess(StructuringInformation.adts _, 0.5 :: 0.25 :: HNil))

}
