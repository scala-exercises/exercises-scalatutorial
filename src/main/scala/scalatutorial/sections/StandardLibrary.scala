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
 * @param name
 *   standard_library
 */
object StandardLibrary extends ScalaTutorialSection {

  /**
   * =List=
   *
   * The list is a fundamental data structure in functional programming.
   *
   * A list having `x1`, …, `xn` as elements is written `List(x1, …, xn)`:
   *
   * {{{
   *   val fruit  = List("apples", "oranges", "pears")
   *   val nums   = List(1, 2, 3, 4)
   *   val diag3  = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
   *   val empty  = List()
   * }}}
   *
   *   - Lists are immutable --- the elements of a list cannot be changed,
   *   - Lists are recursive (as you will see in the next subsection),
   *   - Lists are ''homogeneous'': A list is intended to be composed of elements that all have the
   *     same type.
   *
   * That's because when you create a `List` of elements with different types it will look for a
   * common ancestor. The common ancestor for all types is `Any`
   *
   * {{{
   *   val heterogeneousList: List[Any] = List(1, "1", '1')
   * }}}
   *
   * The type of a list with elements of type `T` is written `List[T]`:
   *
   * {{{
   *   val fruit: List[String]    = List("apples", "oranges", "pears")
   *   val nums : List[Int]       = List(1, 2, 3, 4)
   *   val diag3: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
   *   val empty: List[Nothing]   = List()
   * }}}
   *
   * ==Constructors of Lists==
   *
   * Actually, all lists are constructed from:
   *
   *   - the empty list `Nil`, and
   *   - the construction operation `::` (pronounced ''cons''): `x :: xs` gives a new list with the
   *     first element `x`, called the `head`, followed by the `tail` `xs`, which is itself a list
   *     of elements.
   *
   * For example:
   *
   * {{{
   *   val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
   *   val nums  = 1 :: (2 :: (3 :: (4 :: Nil)))
   *   val empty = Nil
   * }}}
   *
   * ===Right Associativity===
   *
   * Convention: Operators ending in “`:`” associate to the right.
   *
   * `A :: B :: C` is interpreted as `A :: (B :: C)`.
   *
   * We can thus omit the parentheses in the definition above.
   *
   * {{{
   *   val nums = 1 :: 2 :: 3 :: 4 :: Nil
   * }}}
   *
   * Operators ending in “`:`” are also different in the fact that they are seen as method calls of
   * the ''right-hand'' operand.
   *
   * So the expression above is equivalent to:
   *
   * {{{
   *   val nums = Nil.::(4).::(3).::(2).::(1)
   * }}}
   *
   * ==Manipulating Lists==
   *
   * It is possible to decompose lists with pattern matching:
   *
   *   - `Nil`: the `Nil` constant,
   *   - `p :: ps`: A pattern that matches a list with a `head` matching `p` and a `tail` matching
   *     `ps`.
   *
   * {{{
   *   nums match {
   *     // Lists of `Int` that starts with `1` and then `2`
   *     case 1 :: 2 :: xs => …
   *     // Lists of length 1
   *     case x :: Nil => …
   *     // Same as `x :: Nil`
   *     case List(x) => …
   *     // The empty list, same as `Nil`
   *     case List() =>
   *     // A list that contains as only element another list that starts with `2`
   *     case List(2 :: xs) => …
   *   }
   * }}}
   *
   * ==Exercise: Sorting Lists==
   *
   * Suppose we want to sort a list of numbers in ascending order:
   *
   *   - One way to sort the list `List(7, 3, 9, 2)` is to sort the tail `List(3, 9, 2)` to obtain
   *     `List(2, 3, 9)`.
   *   - The next step is to insert the head `7` in the right place to obtain the result `List(2, 3,
   *     7, 9)`.
   *
   * This idea describes ''Insertion Sort'':
   *
   * {{{
   *   def insertionSort(xs: List[Int]): List[Int] = xs match {
   *     case List() => List()
   *     case y :: ys => insert(y, insertionSort(ys))
   *   }
   * }}}
   *
   * Complete the definition insertion sort by filling in the blanks in the definition below:
   */
  def insertionSort(res0: (Int, Int) => Boolean, res1: List[Int]): Unit = {
    val cond: (Int, Int) => Boolean = res0
    def insert(x: Int, xs: List[Int]): List[Int] =
      xs match {
        case List() => x :: res1
        case y :: ys =>
          if (cond(x, y)) x :: y :: ys
          else y :: insert(x, ys)
      }
    insert(2, 1 :: 3 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
    insert(1, 2 :: 3 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
    insert(3, 1 :: 2 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
  }

  /**
   * ==Common Operations on Lists==
   *
   * Transform the elements of a list using `map`:
   *
   * {{{
   *   List(1, 2, 3).map(x => x + 1) == List(2, 3, 4)
   * }}}
   *
   * Filter elements using `filter`:
   *
   * {{{
   *   List(1, 2, 3).filter(x => x % 2 == 0) == List(2)
   * }}}
   *
   * Transform each element of a list into a list and flatten the results into a single list using
   * `flatMap`:
   *
   * {{{
   *   val xs =
   *     List(1, 2, 3).flatMap { x =>
   *       List(x, 2 * x, 3 * x)
   *     }
   *   xs == List(1, 2, 3, 2, 4, 6, 3, 6, 9)
   * }}}
   *
   * =Optional Values=
   *
   * We represent an optional value of type `A` with the type `Option[A]`. This is useful to
   * implement, for instance, partially defined functions:
   *
   * {{{
   *   // The `sqrt` function is not defined for negative values
   *   def sqrt(x: Double): Option[Double] = …
   * }}}
   *
   * An `Option[A]` can either be `None` (if there is no value) or `Some[A]` (if there is a value):
   *
   * {{{
   *   def sqrt(x: Double): Option[Double] =
   *     if (x < 0) None else Some(…)
   * }}}
   *
   * ==Manipulating Options==
   *
   * It is possible to decompose options with pattern matching:
   *
   * {{{
   *   def foo(x: Double): String =
   *     sqrt(x) match {
   *       case None => "no result"
   *       case Some(y) => y.toString
   *     }
   * }}}
   *
   * ==Common Operations on Options==
   *
   * Transform an optional value with `map`:
   */
  def optionMap(res0: Option[Int], res1: Option[Int]): Unit = {
    Some(1).map(x => x + 1) shouldBe Some(2)
    None.map((x: Int) => x + 1) shouldBe None
    res0.map(x => x + 1) shouldBe res1
  }

  /**
   * Filter values with `filter`:
   */
  def optionFilter(res0: Option[Int], res1: Option[Int]): Unit = {
    Some(1).filter(x => x % 2 == 0) shouldBe None
    Some(2).filter(x => x % 2 == 0) shouldBe Some(2)
    res0.filter(x => x % 2 == 0) shouldBe res1
  }

  /**
   * Use `flatMap` to transform a successful value into an optional value:
   */
  def optionFlatMap(res0: Option[Int], res1: Option[Int]): Unit = {
    Some(1).flatMap(x => Some(x + 1)) shouldBe Some(2)
    None.flatMap((x: Int) => Some(x + 1)) shouldBe None
    res0.flatMap(x => Some(x + 1)) shouldBe res1
  }

  /**
   * =Error Handling=
   *
   * This subsection introduces types that are useful to handle failures.
   *
   * ==Try==
   *
   * `Try[A]` represents a computation that attempted to return an `A`. It can either be:
   *   - a `Success[A]`,
   *   - or a `Failure`.
   *
   * The key difference between `None` and `Failure`s is that the latter provide the reason for the
   * failure:
   *
   * {{{
   *   def sqrt(x: Double): Try[Double] =
   *     if (x < 0) Failure(new IllegalArgumentException("x must be positive"))
   *     else Success(…)
   * }}}
   *
   * ===Manipulating `Try[A]` values===
   *
   * Like options and lists, `Try[A]` is an algebraic data type, so it can be decomposed using
   * pattern matching.
   *
   * `Try[A]` also have `map`, `filter` and `flatMap`. They behave the same as with `Option[A]`,
   * except that any exception that is thrown during their execution is converted into a `Failure`.
   *
   * ==Either==
   *
   * `Either` can also be useful to handle failures. Basically, the type `Either[A, B]` represents a
   * value that can either be of type `A` or of type `B`. It can be decomposed in two cases: `Left`
   * or `Right`.
   *
   * You can use one case to represent the failure and the other to represent the success. What
   * makes it different from `Try` is that you can choose a type other than `Throwable` to represent
   * the exception. Another difference is that exceptions that occur when transforming `Either`
   * values are not converted into failures.
   *
   * {{{
   *   def sqrt(x: Double): Either[String, Double] =
   *     if (x < 0) Left("x must be positive")
   *     else Right(…)
   * }}}
   *
   * ===Manipulating `Either[A, B]` Values===
   *
   * Since Scala 2.12, `Either` has `map` and `flatMap`. These methods transform the `Right` case
   * only. We say that `Either` is “right biased”:
   *
   * {{{
   *   Right(1).map((x: Int) => x + 1) shouldBe Right(2)
   *   Left("foo").map((x: Int) => x + 1) shouldBe Left("foo")
   * }}}
   *
   * `Either` also has a `filterOrElse` method that turns a `Right` value into a `Left` value if it
   * does not satisfy a given predicate:
   *
   * {{{
   *   Right(1).filterOrElse(x => x % 2 == 0, "Odd value") shouldBe Left("Odd value")
   * }}}
   *
   * Specify which “side” (`Left` or `Right`) you wanted to `map`:
   */
  def either(res0: Either[String, Int], res1: Either[String, Int]): Unit = {
    def triple(x: Int): Int = 3 * x

    def tripleEither(x: Either[String, Int]): Either[String, Int] =
      x.map(triple)

    tripleEither(Right(1)) shouldBe res0
    tripleEither(Left("not a number")) shouldBe res1
  }

}
