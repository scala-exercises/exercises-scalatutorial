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

/**
 * @param name syntactic_conveniences */
object SyntacticConveniences extends ScalaTutorialSection {

  /**
   * This section introduces several syntactic sugars supported
   * by the language.
   *
   * = String Interpolation =
   *
   * To splice values into constant `String` at runtime, you can
   * use ''string interpolation'':
   */
  def stringInterpolation(res0: String): Unit = {
    def greet(name: String): String =
      s"Hello, $name!"

    greet("Scala") shouldBe "Hello, Scala!"
    greet("Functional Programming") shouldBe res0
  }

  /**
   * After having prefixed the string literal with `s` you can introduce
   * dynamic values in it with `$`.
   *
   * If you want to splice a complex expression (more than just an identifier),
   * surround it with braces:
   */
  def stringInterpolation2(res0: String): Unit = {
    def greet(name: String): String =
      s"Hello, ${name.toUpperCase}!"

    greet("Scala") shouldBe res0
  }

  /**
   * = Tuples =
   *
   * We saw earlier that case classes are useful to aggregate information.
   * However, sometimes you want to aggregate information without having to define
   * a complete case class for it. In such a case you can use ''tuples'':
   */
  def tuples(res0: (Int, String)): Unit = {
    def pair(i: Int, s: String): (Int, String) = (i, s)

    pair(42, "foo") shouldBe ((42, "foo"))
    pair(0, "bar") shouldBe res0
  }

  /**
   * In the above example, the type `(Int, String)` represents a pair whose
   * first element is an `Int` and whose second element is a `String`.
   *
   * Similarly, the value `(i, s)` is a pair whose first element is `i` and
   * whose second element is `s`.
   *
   * More generally, a type `(T1, …, Tn)` is a ''tuple type'' of n elements
   * whose i^th^ element has type `Ti`.
   *
   * And a value `(t1, … tn)` is a ''tuple value'' of n elements.
   *
   * == Manipulating Tuples ==
   *
   * You can retrieve the elements of a tuple by using a ''tuple pattern'':
   */
  def tupleExtraction(res0: String): Unit = {
    val is: (Int, String) = (42, "foo")

    is match {
      case (i, s) =>
        i shouldBe 42
        s shouldBe res0
    }
  }

  /**
   * Or, simply:
   */
  def tupleExtraction2(res0: String): Unit = {
    val is: (Int, String) = (42, "foo")

    val (i, s) = is
    i shouldBe 42
    s shouldBe res0
  }

  /**
   * Alternatively, you can retrieve the 1st element with the `_1` member,
   * the 2nd element with the `_2` member, etc:
   */
  def tupleManipulation(res0: String): Unit = {
    val is: (Int, String) = (42, "foo")
    is._1 shouldBe 42
    is._2 shouldBe res0
  }

  /**
   * = Functions as Objects =
   *
   * We have seen that Scala's numeric types and the `Boolean`
   * type can be implemented like normal classes.
   *
   * But what about functions?
   *
   * In fact function values ''are'' treated as objects in Scala.
   *
   * The function type `A => B` is just an abbreviation for the class
   * `scala.Function1[A, B]`, which is defined as follows.
   *
   * {{{
   *   package scala
   *   trait Function1[A, B] {
   *     def apply(x: A): B
   *   }
   * }}}
   *
   * So functions are objects with `apply` methods.
   *
   * There are also traits `Function2`, `Function3`, ... for functions which take more parameters (currently up to 22).
   *
   * == Expansion of Function Values ==
   *
   * An anonymous function such as
   *
   * {{{
   *   (x: Int) => x * x
   * }}}
   *
   * is expanded to:
   *
   * {{{
   *   {
   *     class AnonFun extends Function1[Int, Int] {
   *       def apply(x: Int) = x * x
   *     }
   *     new AnonFun
   *   }
   * }}}
   *
   * or, shorter, using ''anonymous class syntax'':
   *
   * {{{
   *   new Function1[Int, Int] {
   *     def apply(x: Int) = x * x
   *   }
   * }}}
   *
   * == Expansion of Function Calls ==
   *
   * A function call, such as `f(a, b)`, where `f` is a value of some class
   * type, is expanded to:
   *
   * {{{
   *   f.apply(a, b)
   * }}}
   *
   * So the OO-translation of:
   *
   * {{{
   *   val f = (x: Int) => x * x
   *   f(7)
   * }}}
   *
   * would be:
   *
   * {{{
   *   val f = new Function1[Int, Int] {
   *     def apply(x: Int) = x * x
   *   }
   *   f.apply(7)
   * }}}
   *
   * == Functions and Methods ==
   *
   * Note that a method such as
   *
   * {{{
   *   def f(x: Int): Boolean = …
   * }}}
   *
   * is not itself a function value.
   *
   * But if `f` is used in a place where a Function type is expected, it is
   * converted automatically to the function value
   *
   * {{{
   *   (x: Int) => f(x)
   * }}}
   *
   * = `for` expressions =
   *
   * You probably noticed that several data types of the standard library
   * have methods named `map`, `flatMap` and `filter`.
   *
   * These methods are so common in practice that Scala supports a dedicated
   * syntax: ''for expressions''.
   *
   * == `map` ==
   *
   * Thus, instead of writing the following:
   *
   * {{{
   *   xs.map(x => x + 1)
   * }}}
   *
   * You can write:
   *
   * {{{
   *   for (x <- xs) yield x + 1
   * }}}
   *
   * You can read it as “for every value, that I name ‘x’, in ‘xs’, return ‘x + 1’”.
   *
   * == `filter` ==
   *
   * Also, instead of writing the following:
   *
   * {{{
   *   xs.filter(x => x % 2 == 0)
   * }}}
   *
   * You can write:
   *
   * {{{
   *   for (x <- xs if x % 2 == 0) yield x
   * }}}
   *
   * The benefit of this syntax becomes more apparent when it is combined
   * with the previous one:
   *
   * {{{
   *   for (x <- xs if x % 2 == 0) yield x + 1
   *
   *   // Equivalent to the following:
   *   xs.filter(x => x % 2 == 0).map(x => x + 1)
   * }}}
   *
   * == `flatMap` ==
   *
   * Finally, instead of writing the following:
   *
   * {{{
   *   xs.flatMap(x => ys.map(y => (x, y)))
   * }}}
   *
   * You can write:
   *
   * {{{
   *   for (x <- xs; y <- ys) yield (x, y)
   * }}}
   *
   * You can read it as “for every value ‘x’ in ‘xs’, and then for
   * every value ‘y’ in ‘ys’, return ‘(x, y)’”.
   *
   * == Putting Things Together ==
   *
   * Here is an example that puts everything together:
   *
   * {{{
   *   for {
   *     x <- xs if x % 2 == 0
   *     y <- ys
   *   } yield (x, y)
   * }}}
   *
   * The equivalent de-sugared code is the following:
   *
   * {{{
   *   xs.filter { x =>
   *     x % 2 == 0
   *   }.flatMap { x =>
   *     ys.map { y =>
   *       (x, y)
   *     }
   *   }
   * }}}
   *
   * = Method’s Parameters =
   *
   * == Named Parameters ==
   *
   * It can sometimes be difficult to figure out what is the meaning of
   * each parameter passed to a function. Consider for instance the following
   * expression:
   *
   * {{{
   *   Range(1, 10, 2)
   * }}}
   *
   * What does it mean? We can improve the readability by using ''named
   * parameters''.
   *
   * Based on the fact that the `Range` constructor is defined as follows:
   *
   * {{{
   *   case class Range(start: Int, end: Int, step: Int)
   * }}}
   *
   * We can rewrite our expression as follows:
   *
   * {{{
   *   Range(start = 1, end = 10, step = 2)
   * }}}
   *
   * It is now clearer that this expression defines a range of numbers
   * from 1 to 10 by increments of 2.
   *
   * == Default Values ==
   *
   * Methods’ parameters can have default values. Let’s refine the `Range`
   * constructor:
   *
   * {{{
   *   case class Range(start: Int, end: Int, step: Int = 1)
   * }}}
   *
   * Here, we say that the `step` parameter has a default value, `1`.
   *
   * Then, at use site we can omit to supply this parameter and the compiler
   * will supply it for us, by using its default value:
   */
  def defaultParameters(res0: Int): Unit = {
    case class Range(start: Int, end: Int, step: Int = 1)

    val xs = Range(start = 1, end = 10)

    xs.step shouldBe res0
  }

  /**
   * == Repeated Parameters ==
   *
   * You can define a function that can receive an arbitrary number of
   * parameters (of the same type) as follows:
   */
  def repeatedParameters(res0: Double): Unit = {
    def average(x: Int, xs: Int*): Double =
      (x :: xs.to(List)).sum.toDouble / (xs.size + 1)

    average(1) shouldBe 1.0
    average(1, 2) shouldBe 1.5
    average(1, 2, 3) shouldBe res0
  }

  /**
   * The `average` function takes at least one `Int` parameter and then
   * an arbitrary number of other values and computes their average.
   * By forcing users to supply at least one parameter, we make it impossible
   * for them to compute the average of an empty list of numbers.
   *
   * Sometimes you want to supply each element of a list as many parameters.
   * You can do that by adding a `: _*` type ascription to your list:
   *
   * {{{
   *   val xs: List[Int] = …
   *   average(1, xs: _*)
   * }}}
   *
   * = Type Aliases =
   *
   * In the same way as you can give meaningful names to expressions,
   * you can give meaningful names to ''type expressions'':
   */
  def typeAlias(res0: Either[String, (Int, Int)]): Unit = {
    type Result = Either[String, (Int, Int)]
    def divide(dividend: Int, divisor: Int): Result =
      if (divisor == 0) Left("Division by zero")
      else Right((dividend / divisor, dividend % divisor))
    divide(6, 4) shouldBe Right((1, 2))
    divide(2, 0) shouldBe Left("Division by zero")
    divide(8, 4) shouldBe res0
  }

}
