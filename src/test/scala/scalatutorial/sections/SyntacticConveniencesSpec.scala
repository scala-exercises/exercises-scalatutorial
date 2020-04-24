/*
 * Copyright 2016-2020 47 Degrees <https://47deg.com>
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

import org.scalacheck.ScalacheckShapeless._
import org.scalaexercises.Test
import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers
import shapeless.HNil

class SyntacticConveniencesSpec extends RefSpec with Checkers {

  def `check string interpolation`(): Unit =
    check(
      Test.testSuccess(
        SyntacticConveniences.stringInterpolation _,
        "Hello, Functional Programming!" :: HNil
      )
    )

  def `check string interpolation2`(): Unit =
    check(Test.testSuccess(SyntacticConveniences.stringInterpolation2 _, "Hello, SCALA!" :: HNil))

  def `check tuples`(): Unit =
    check(Test.testSuccess(SyntacticConveniences.tupleExtraction _, "foo" :: HNil))

  def `check tuples2`(): Unit =
    check(Test.testSuccess(SyntacticConveniences.tupleExtraction2 _, "foo" :: HNil))

  def `check tuples manipulation`(): Unit =
    check(Test.testSuccess(SyntacticConveniences.tupleManipulation _, "foo" :: HNil))

  def `check default parameters`(): Unit =
    check(Test.testSuccess(SyntacticConveniences.defaultParameters _, 1 :: HNil))

  def `check repeated parameters`(): Unit =
    check(Test.testSuccess(SyntacticConveniences.repeatedParameters _, 2.0 :: HNil))

  def `check type alias`(): Unit =
    check(
      Test.testSuccess(
        SyntacticConveniences.typeAlias _,
        (Right((2, 0)): Either[String, (Int, Int)]) :: HNil
      )
    )

}
