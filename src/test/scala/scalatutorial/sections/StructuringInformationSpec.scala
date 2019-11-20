/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

import org.scalacheck.ScalacheckShapeless._
import org.scalaexercises.Test
import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers
import shapeless.HNil

class StructuringInformationSpec extends RefSpec with Checkers {

  def `check case class projection`(): Unit =
    check(Test.testSuccess(StructuringInformation.caseClassProjection _, "Quarter" :: 3 :: HNil))

  def `check case class equals`(): Unit =
    check(Test.testSuccess(StructuringInformation.caseClassEquals _, true :: false :: HNil))

  def `check adts`(): Unit =
    check(Test.testSuccess(StructuringInformation.adts _, 0.5 :: 0.25 :: HNil))

}
