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
 *   lazy_evaluation
 */
object LazyEvaluation extends ScalaTutorialSection {

  /**
   * =Motivation=
   *
   * Consider the following program that finds the second prime number between 1000 and 10000:
   *
   * {{{
   *   ((1000 to 10000) filter isPrime)(1)
   * }}}
   *
   * This is ''much'' shorter than the recursive alternative:
   *
   * {{{
   *   def nthPrime(from: Int, to: Int, n: Int): Int =
   *     if (from >= to) throw new Error("no prime")
   *     else if (isPrime(from))
   *       if (n == 1) from else nthPrime(from + 1, to, n - 1)
   *     else nthPrime(from + 1, to, n)
   * }}}
   * def secondPrime(from: Int, to: Int) = nthPrime(from, to, 2)
   *
   * But from a standpoint of performance, the first version is pretty bad; it constructs ''all''
   * prime numbers between `1000` and `10000` in a list, but only ever looks at the first two
   * elements of that list.
   *
   * Reducing the upper bound would speed things up, but risks that we miss the second prime number
   * all together.
   *
   * =Delayed Evaluation=
   *
   * However, we can make the short-code efficient by using a trick:
   *
   *   - Avoid computing the tail of a sequence until it is needed for the evaluation result (which
   *     might be never)
   *
   * This idea is implemented in a new class, the `LazyList`.
   *
   * LazyLists are similar to lists, but their elements are evaluated only ''on demand''.
   *
   * =Defining LazyLists=
   *
   * LazyLists are defined from a constructor `LazyList.cons`.
   *
   * For instance,
   *
   * {{{
   *   val xs = LazyList.cons(1, LazyList.cons(2, LazyList.empty))
   * }}}
   *
   * =LazyList Ranges=
   *
   * Let's try to write a function that returns a `LazyList` representing a range of numbers between
   * `lo` and `hi`:
   *
   * {{{
   *   def llRange(lo: Int, hi: Int): LazyList[Int] =
   *     if (lo >= hi) LazyList.empty
   *     else LazyList.cons(lo, llRange(lo + 1, hi))
   * }}}
   *
   * Compare to the same function that produces a list:
   *
   * {{{
   *   def listRange(lo: Int, hi: Int): List[Int] =
   *     if (lo >= hi) Nil
   *     else lo :: listRange(lo + 1, hi)
   * }}}
   *
   * The functions have almost identical structure yet they evaluate quite differently.
   *
   *   - `listRange(start, end)` will produce a list with `end - start` elements and return it.
   *   - `llRange(start, end)` returns a single object of type `LazyList` with `start` as head
   *     element.
   *     - The other elements are only computed when they are needed, where “needed” means that
   *       someone calls `tail` on the stream.
   *
   * =Methods on LazyLists=
   *
   * `LazyList` supports almost all methods of `List`.
   *
   * For instance, to find the second prime number between 1000 and 10000:
   *
   * {{{
   *   (llRange(1000, 10000) filter isPrime)(1)
   * }}}
   *
   * The one major exception is `::`.
   *
   * `x :: xs` always produces a list, never a lazy list.
   *
   * There is however an alternative operator `#::` which produces a lazy list.
   *
   * {{{
   *   x #:: xs  ==   LazyList.cons(x, xs)
   * }}}
   *
   * `#::` can be used in expressions as well as patterns.
   *
   * =Implementation of LazyLists=
   *
   * The implementation of lazy lists is quite close to the one of lists.
   *
   * Here's the class `LazyList`:
   *
   * {{{
   *   final class LazyList[+A] ... extends ... {
   *     override def isEmpty: Boolean = ...
   *     override def head: A = ...
   *     override def tail: LazyList[A] = ...
   *     ...
   *   }
   * }}}
   *
   * As for lists, all other methods can be defined in terms of these three.
   *
   * Concrete implementations of streams are defined in the `LazyList.State` companion object.
   * Here's a first draft:
   *
   * {{{
   *   private object State {
   *     object Empty extends State[Nothing] {
   *       def head: Nothing = throw new NoSuchElementException("head of empty lazy list")
   *       def tail: LazyList[Nothing] = throw new UnsupportedOperationException("tail of empty lazy list")
   *     }
   *
   *     final class Cons[A](val head: A, val tail: LazyList[A]) extends State[A]
   *   }
   * }}}
   *
   * The only important difference between the implementations of `List` and `LazyList` concern
   * `tail`, the second parameter of `LazyList.cons`.
   *
   * For lazy lists, this is a by-name parameter: the type of `tail` starts with `=>`. In such a
   * case, this parameter is evaluated by following the rules of the call-by-name model.
   *
   * That's why the second argument to `LazyList.cons` is not evaluated at the point of call.
   *
   * Instead, it will be evaluated each time someone calls `tail` on a `LazyList` object.
   *
   * In Scala 2.13, LazyList (previously Stream) became fully lazy from head to tail. To make it
   * possible, methods (`filter`, `flatMap`...) are implemented in a way where the head is not being
   * evaluated if is not explicitly indicated.
   *
   * For instance, here's `filter`:
   *
   * {{{
   *   object LazyList extends SeqFactory[LazyList] {
   *     ...
   *     private def filterImpl[A](ll: LazyList[A], p: A => Boolean, isFlipped: Boolean): LazyList[A] = {
   *     // DO NOT REFERENCE `ll` ANYWHERE ELSE, OR IT WILL LEAK THE HEAD
   *     var restRef = ll                         // val restRef = new ObjectRef(ll)
   *     newLL {
   *       var elem: A = null.asInstanceOf[A]
   *       var found   = false
   *       var rest    = restRef                  // var rest = restRef.elem
   *       while (!found && !rest.isEmpty) {
   *         elem    = rest.head
   *         found   = p(elem) != isFlipped
   *         rest    = rest.tail
   *         restRef = rest                       // restRef.elem = rest
   *       }
   *       if (found) sCons(elem, filterImpl(rest, p, isFlipped)) else State.Empty
   *     }
   *   }
   * }}}
   *
   * =Exercise=
   *
   * Consider the following modification of `llRange`. When you write `llRange(1,
   * 10).take(3).toList` what is the value of `rec`?
   *
   * Be careful, head is evaluating too!
   */
  def llRangeExercise(res0: Int): Unit = {
    var rec = 0
    def llRange(lo: Int, hi: Int): LazyList[Int] = {
      rec = rec + 1
      if (lo >= hi) LazyList.empty
      else LazyList.cons(lo, llRange(lo + 1, hi))
    }
    llRange(1, 10).take(3).toList
    rec shouldBe res0
  }

  /**
   * =Lazy Evaluation=
   *
   * The proposed `LazyList` implementation suffers from a serious potential performance problem: If
   * `tail` is called several times, the corresponding stream will be recomputed each time.
   *
   * This problem can be avoided by storing the result of the first evaluation of `tail` and
   * re-using the stored result instead of recomputing `tail`.
   *
   * This optimization is sound, since in a purely functional language an expression produces the
   * same result each time it is evaluated.
   *
   * We call this scheme ''lazy evaluation'' (as opposed to ''by-name evaluation'' in the case where
   * everything is recomputed, and ''strict evaluation'' for normal parameters and `val`
   * definitions.)
   *
   * ==Lazy Evaluation in Scala==
   *
   * Haskell is a functional programming language that uses lazy evaluation by default.
   *
   * Scala uses strict evaluation by default, but allows lazy evaluation of value definitions with
   * the `lazy val` form:
   *
   * {{{
   *   lazy val x = expr
   * }}}
   *
   * ==Exercise==
   */
  def lazyVal(res0: String): Unit = {
    val builder = new StringBuilder

    val x      = { builder += 'x'; 1 }
    lazy val y = { builder += 'y'; 2 }
    def z      = { builder += 'z'; 3 }

    z + y + x + z + y + x

    builder.result() shouldBe res0
  }

}
