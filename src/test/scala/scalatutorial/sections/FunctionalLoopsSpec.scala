/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

import org.scalacheck.{Arbitrary, Gen}
//import org.scalacheck.ScalacheckShapeless._
import org.scalaexercises.Test
import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers
import shapeless._

class FunctionalLoopsSpec extends RefSpec with Checkers {

  implicit val arb: Arbitrary[Int :: Int :: HNil] = Arbitrary {
    for {
      num1 <- Gen.choose(-10, 0)
      num2 <- Gen.choose(0, 10)
    } yield num1 :: num2 :: HNil
  }

  def `factorial exercise with recursion`(): Unit =
    check(Test.testSuccess(FunctionalLoops.factorialExercise _, 0 :: 1 :: HNil))

}
