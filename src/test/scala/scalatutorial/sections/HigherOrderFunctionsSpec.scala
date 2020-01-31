/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

//import org.scalacheck.ScalacheckShapeless._
import org.scalacheck.{Arbitrary, Gen}
import org.scalaexercises.Test
import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers
import shapeless.{::, HNil}

class HigherOrderFunctionsSpec extends RefSpec with Checkers {

  implicit val arb: Arbitrary[Int :: Int :: HNil] = Arbitrary {
    for {
      num1 <- Gen.posNum[Int]
      num2 <- Gen.posNum[Int]
    } yield num1 :: num2 :: HNil
  }

  def `check tail recursive sum function`(): Unit =
    check(Test.testSuccess(HigherOrderFunctions.tailRecSum _, 0 :: 10 :: HNil))

}
