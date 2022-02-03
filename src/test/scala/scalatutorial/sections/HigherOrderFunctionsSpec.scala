/*
 * Copyright 2016-2020 47 Degrees Open Source <https://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
