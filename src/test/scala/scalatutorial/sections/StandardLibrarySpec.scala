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

class StandardLibrarySpec extends Spec with Checkers {

  def `check insertion sort`: Unit =
    check(
      Test.testSuccess(
        StandardLibrary.insertionSort _,
        ((_: Int) < (_: Int)) :: List.empty[Int] :: HNil))

  def `check either`: Unit =
    check(
      Test.testSuccess(
        StandardLibrary.either _,
        (Right[String, Int](3): Either[String, Int]) :: (Left[String, Int]("not a number"): Either[
          String,
          Int]) :: HNil))

}
