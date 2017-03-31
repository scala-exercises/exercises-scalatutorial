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

class ClassesVsCaseClassesSpec extends Spec with Checkers {

  def `check creation and manipulation`: Unit =
    check(Test.testSuccess(ClassesVsCaseClasses.creationAndManipulation _, "C" :: HNil))

  def `check equality`: Unit =
    check(Test.testSuccess(ClassesVsCaseClasses.equality _, false :: true :: HNil))

  def `check encoding`: Unit =
    check(
      Test.testSuccess(
        ClassesVsCaseClasses.encoding _,
        "Note(C,Quarter,3)" :: false :: "Note(C,Quarter,4)" :: HNil))

}
