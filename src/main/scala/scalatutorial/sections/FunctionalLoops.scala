/*
 * scala-exercises - exercises-scalatutorial
 * Copyright (C) 2015-2016 47 Degrees, LLC. <http://www.47deg.com>
 */

package scalatutorial.sections

/** @param name functional_loops */
object FunctionalLoops extends ScalaTutorialSection {

  /**
   * = Conditional Expressions =
   *
   * To express choosing between two alternatives, Scala
   * has a conditional expression `if-else`.
   *
   * It looks like a `if-else` in Java, but is used for expressions, not statements.
   *
   * Example:
   *
   * {{{
   *   def abs(x: Double) = if (x >= 0) x else -x
   * }}}
   *
   * `x >= 0` is a ''predicate'', of type `Boolean`.
   *
   * = Boolean Expressions =
   *
   * Boolean expressions `b` can be composed of
   *
   * {{{
   *   true  false      // Constants
   *   !b               // Negation
   *   b && b           // Conjunction
   *   b || b           // Disjunction
   * }}}
   *
   * and of the usual comparison operations:
   * {{{
   *   e <= e, e >= e, e < e, e > e, e == e, e != e
   * }}}
   *
   * = Rewrite rules for Booleans =
   *
   * Here are reduction rules for Boolean expressions (`e` is an arbitrary expression):
   *
   * {{{
   *   !true       -->   false
   *   !false      -->   true
   *   true && e   -->   e
   *   false && e  -->   false
   *   true || e   -->   true
   *   false || e  -->   e
   * }}}
   *
   * Note that `&&` and `||` do not always need their right operand to be evaluated.
   *
   * We say these expressions use “short-circuit evaluation”.
   *
   * = Computing the Square Root of a Value =
   *
   * We will define in this section a method
   *
   * {{{
   *   /** Calculates the square root of parameter x */
   *   def sqrt(x: Double): Double = ...
   * }}}
   *
   * The classical way to achieve this is by successive approximations using
   * Newton's method.
   *
   * = Method =
   *
   * To compute `sqrt(x)`:
   *
   *  - Start with an initial ''estimate'' `y` (let's pick `y = 1`).
   *  - Repeatedly improve the estimate by taking the mean of `y` and `x/y`.
   *
   * Example: Evaluation of the square root of 2 (x = 2):
   *
   * {{{
   *   Estimation          Quotient              Mean
   *   1                   2 / 1 = 2             1.5
   *   1.5                 2 / 1.5 = 1.333       1.4167
   *   1.4167              2 / 1.4167 = 1.4118   1.4142
   *   1.4142              ...                   ...
   * }}}
   *
   * = Implementation in Scala =
   *
   * First, we define a method which computes one iteration step:
   *
   * {{{
   *   def sqrtIter(guess: Double, x: Double): Double =
   *     if (isGoodEnough(guess, x)) guess
   *     else sqrtIter(improve(guess, x), x)
   * }}}
   *
   * Note that `sqrtIter` is ''recursive'', its right-hand side calls itself.
   *
   * Recursive methods need an explicit return type in Scala.
   *
   * For non-recursive methods, the return type is optional.
   *
   * Second, we define a method `improve` to improve an estimate and a test to check for termination:
   *
   * {{{
   *   def improve(guess: Double, x: Double) =
   *     (guess + x / guess) / 2
   *
   *   def isGoodEnough(guess: Double, x: Double) =
   *     abs(guess * guess - x) < 0.001
   * }}}
   *
   * Third, we define the `sqrt` function:
   *
   * {{{
   *   def sqrt(x: Double) = sqrtIter(1.0, x)
   * }}}
   *
   * = Summary =
   *
   * You have seen simple elements of functional programing in Scala.
   *
   *  - arithmetic and boolean expressions
   *  - conditional expressions if-else
   *  - functions with recursion
   *
   * You have learned the difference between the call-by-name and
   * call-by-value evaluation strategies.
   *
   * You have learned a way to reason about program execution: reduce expressions using
   * the substitution model.
   *
   * = Exercise =
   *
   * Complete the following method definition that computes the factorial of a number:
   */
  def factorialExercise(res0: Int, res1: Int, res2: Int): Unit = {
    def factorial(n: Int): Int =
      if (n == res0) res1
      else factorial(n - res2) * n

    factorial(3) shouldBe 6
    factorial(4) shouldBe 24
  }

}
