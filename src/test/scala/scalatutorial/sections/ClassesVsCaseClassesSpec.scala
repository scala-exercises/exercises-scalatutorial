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

class ClassesVsCaseClassesSpec extends RefSpec with Checkers {

  def `check creation and manipulation`(): Unit =
    check(Test.testSuccess(ClassesVsCaseClasses.creationAndManipulation _, "C" :: HNil))

  def `check equality`(): Unit =
    check(Test.testSuccess(ClassesVsCaseClasses.equality _, false :: true :: HNil))

  def `check encoding`(): Unit =
    check(
      Test.testSuccess(
        ClassesVsCaseClasses.encoding _,
        "Note(C,Quarter,3)" :: false :: "Note(C,Quarter,4)" :: HNil))

}
