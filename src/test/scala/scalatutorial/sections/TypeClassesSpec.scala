/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

import org.scalatest.refspec.RefSpec
import org.scalatestplus.scalacheck.Checkers

import scalatutorial.utils.Rational

class TypeClassesSpec extends RefSpec with Checkers {

  def `check rational ordering`(): Unit = {
    val ordering =
      (x: Rational, y: Rational) => x.numer * y.denom - y.numer * x.denom

    TypeClasses.rationalOrdering(ordering)
  }

}
