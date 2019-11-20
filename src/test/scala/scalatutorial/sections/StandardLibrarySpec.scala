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

class StandardLibrarySpec extends RefSpec with Checkers {

  def `check insertion sort`(): Unit =
    check(
      Test.testSuccess(
        StandardLibrary.insertionSort _,
        ((_: Int) < (_: Int)) :: List.empty[Int] :: HNil))

  def `check either`(): Unit =
    check(
      Test.testSuccess(
        StandardLibrary.either _,
        (Right[String, Int](3): Either[String, Int]) :: (Left[String, Int]("not a number"): Either[
          String,
          Int]) :: HNil))

}
