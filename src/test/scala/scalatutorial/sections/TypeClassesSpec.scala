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

import scalatutorial.aux.Rational

class TypeClassesSpec extends Spec with Checkers {

  def `check rational ordering`: Unit = {
    val ordering =
      (x: Rational, y: Rational) => x.numer * y.denom - y.numer * x.denom
    check(Test.testSuccess(TypeClasses.rationalOrdering _, ordering :: HNil))
  }

}
