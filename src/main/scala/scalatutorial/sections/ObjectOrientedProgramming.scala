/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

import scalatutorial.utils.{Empty, NonEmpty}

/** @param name object_oriented_programming */
object ObjectOrientedProgramming extends ScalaTutorialSection {

  /**
   * = Functions and Data =
   *
   * Let’s see how functions create and encapsulate data
   * structures.
   *
   * We want to design a package for doing rational arithmetic.
   *
   * A rational number `x / y` is represented by two integers:
   *
   *  - its ''numerator'' `x`, and
   *  - its ''denominator'' `y`.
   *
   * = Rational Addition =
   *
   * Suppose we want to implement the addition of two rational numbers.
   *
   * {{{
   *   def addRationalNumerator(n1: Int, d1: Int, n2: Int, d2: Int): Int
   *   def addRationalDenominator(n1: Int, d1: Int, n2: Int, d2: Int): Int
   * }}}
   *
   * It would be difficult to manage all these numerators and denominators!
   *
   * A better choice is to combine the numerator and
   * denominator of a rational number in a data structure.
   *
   * = Classes =
   *
   * In Scala, we do this by defining a ''class'':
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     def numer = x
   *     def denom = y
   *   }
   * }}}
   *
   * This definition introduces two entities:
   *
   *  - A new ''type'', named `Rational`.
   *  - A ''constructor'' `Rational` to create elements of this type.
   *
   * Scala keeps the names of types and values in ''different namespaces''.
   * So there's no conflict between the two definitions of `Rational`.
   *
   * = Objects =
   *
   * We call the elements of a class type ''objects''.
   *
   * We create an object by prefixing an application of the constructor of
   * the class with the operator `new`.
   *
   * {{{
   *   new Rational(1, 2)
   * }}}
   *
   * = Members of an Object =
   *
   * Objects of the class `Rational` have two ''members'',
   * `numer` and `denom`.
   *
   * We select the members of an object with the infix operator `.` (like in Java).
   *
   * {{{
   *   val x = new Rational(1, 2) // x: Rational = Rational@2abe0e27
   *   x.numer // 1
   *   x.denom // 2
   * }}}
   *
   * = Rational Arithmetic =
   *
   * We can now define the arithmetic functions that implement the standard rules.
   *
   * {{{
   *   n1 / d1 + n2 / d2 = (n1 * d2 + n2 * d1) / (d1 * d2)
   *   n1 / d1 - n2 / d2 = (n1 * d2 - n2 * d1) / (d1 * d2)
   *   n1 / d1 * n2 / d2 = (n1 * n2) / (d1 * d2)
   *   n1 / d1 / n2 / d2 = (n1 * d2) / (d1 * n2)
   *   n1 / d1 = n2 / d2 iff n1 * d2 = d1 * n2
   * }}}
   *
   * = Implementing Rational Arithmetic =
   *
   * {{{
   *   def addRational(r: Rational, s: Rational): Rational =
   *     new Rational(
   *       r.numer * s.denom + s.numer * r.denom,
   *       r.denom * s.denom
   *     )
   *
   *   def makeString(r: Rational) =
   *     r.numer + "/" + r.denom
   * }}}
   *
   * And then:
   *
   * {{{
   *   makeString(addRational(new Rational(1, 2), new Rational(2, 3)))
   * }}}
   *
   * = Methods =
   *
   * One can go further and also package functions operating on a data
   * abstraction in the data abstraction itself.
   *
   * Such functions are called ''methods''.
   *
   * Rational numbers now would have, in addition to the functions `numer`
   * and `denom`, the functions  `add`, `sub`,
   * `mul`, `div`, `equal`, `toString`.
   *
   * Here's a possible implementation:
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     def numer = x
   *     def denom = y
   *     def add(r: Rational) =
   *       new Rational(numer * r.denom + r.numer * denom, denom * r.denom)
   *     def mul(r: Rational) = ...
   *     ...
   *     override def toString = numer + "/" + denom
   *   }
   * }}}
   *
   * Note that the modifier `override` declares that `toString`
   * redefines a method that already exists (in the class `java.lang.Object`).
   *
   * Here is how one might use the new `Rational` abstraction:
   *
   * {{{
   *   val x = new Rational(1, 3)
   *   val y = new Rational(5, 7)
   *   val z = new Rational(3, 2)
   *   x.add(y).mul(z)
   * }}}
   *
   * = Data Abstraction =
   *
   * In the above example rational numbers weren't always
   * represented in their simplest form.
   *
   * One would expect the rational numbers to be ''simplified'':
   *
   *  - reduce them to their smallest numerator and denominator by dividing both with a divisor.
   *
   * We could implement this in each rational operation, but it would be easy
   * to forget this division in an operation.
   *
   * A better alternative consists of simplifying the representation in
   * the class when the objects are constructed:
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
   *     private val g = gcd(x, y)
   *     def numer = x / g
   *     def denom = y / g
   *     ...
   *   }
   * }}}
   *
   * `gcd` and `g` are ''private'' members; we can only access them
   * from inside the `Rational` class.
   *
   * In this example, we calculate `gcd` immediately, so that its value can be re-used
   * in the calculations of `numer` and `denom`.
   *
   * It is also possible to call `gcd` in the code of
   * `numer` and `denom`:
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
   *     def numer = x / gcd(x, y)
   *     def denom = y / gcd(x, y)
   *   }
   * }}}
   *
   * This can be advantageous if it is expected that the functions `numer`
   * and `denom` are called infrequently.
   *
   * It is equally possible to turn `numer` and `denom` into `val`s, so that they are computed only once:
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
   *     val numer = x / gcd(x, y)
   *     val denom = y / gcd(x, y)
   *   }
   * }}}
   *
   * This can be advantageous if the functions `numer` and `denom` are called often.
   *
   * == The Client's View ==
   *
   * Clients observe exactly the same behavior in each case.
   *
   * This ability to choose different implementations of the data without affecting
   * clients is called ''data abstraction''.
   *
   * It is a cornerstone of software engineering.
   *
   * = Self Reference =
   *
   * On the inside of a class, the name `this` represents the object
   * on which the current method is executed.
   *
   * Add the functions `less` and `max` to the class `Rational`.
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     ...
   *     def less(that: Rational) =
   *     numer * that.denom < that.numer * denom
   *
   *     def max(that: Rational) =
   *       if (this.less(that)) that else this
   *   }
   * }}}
   *
   * Note that a simple name `x`, which refers to another member
   * of the class, is an abbreviation of `this.x`. Thus, an equivalent
   * way to formulate `less` is as follows.
   *
   * {{{
   *   def less(that: Rational) =
   *     this.numer * that.denom < that.numer * this.denom
   * }}}
   *
   * = Preconditions =
   *
   * Let's say our `Rational` class requires that the denominator is positive.
   *
   * We can enforce this by calling the `require` function.
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     require(y > 0, "denominator must be positive")
   *     ...
   *   }
   * }}}
   *
   * `require` is a predefined function. It takes a condition and an optional message string.
   * If the condition passed to `require` is `false`, an `IllegalArgumentException` is thrown
   * with the given message string.
   *
   * = Assertions =
   *
   * Besides `require`, there is also `assert`.
   *
   * Assert also takes a condition and an optional message string as parameters. E.g.
   *
   * {{{
   *   val x = sqrt(y)
   *   assert(x >= 0)
   * }}}
   *
   * Like `require`, a failing `assert` will also throw an exception, but it's a
   * different one: `AssertionError` for `assert`, `IllegalArgumentException` for `require`.
   *
   * This reflects a difference in intent
   *
   *  - `require` is used to enforce a precondition on the caller of a function.
   *  - `assert` is used as to check the code of the function itself.
   *
   * = Constructors =
   *
   * In Scala, a class implicitly introduces a constructor. This one
   * is called the ''primary constructor'' of the class.
   *
   * The primary constructor:
   *
   *  - takes the parameters of the class
   *  - and executes all statements in the class body
   *    (such as the `require` a couple of slides back).
   *
   * == Auxiliary Constructors ==
   *
   * Scala also allows the declaration of ''auxiliary constructors''.
   *
   * These are methods named `this`.
   *
   * Adding an auxiliary constructor to the class `Rational`:
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     def this(x: Int) = this(x, 1)
   *     ...
   *   }
   * }}}
   *
   * = Classes and Substitutions =
   *
   * We previously defined the meaning of a function application using
   * a computation model based on substitution. Now we extend this
   * model to classes and objects.
   *
   * How is an instantiation of the class `new C(e1, …, en)` evaluated?
   *
   * The expression arguments `e1, …, en`
   * are evaluated like the arguments of a normal function. That's it.
   *
   * The resulting expression, say, `new C(v1, …, vn)`, is
   * already a value.
   *
   * Now suppose that we have a class definition,
   *
   * {{{
   *   class C(x1, …, xn) {
   *     …
   *     def f(y1, …, ym) = b
   *     …
   *   }
   * }}}
   *
   * where:
   *
   *  - The formal parameters of the class are `x1, …, xn`.
   *  - The class defines a method `f` with formal parameters
   *    `y1, …, ym`.
   *
   * (The list of function parameters can be absent. For simplicity, we
   * have omitted the parameter types.)
   *
   * How is the following expression evaluated?
   *
   * {{{
   *   new C(v1, …, vn).f(w1, …, wm)
   * }}}
   *
   * The following three substitutions happen:
   *
   *  - the substitution of the formal parameters `y1, …, ym` of the function `f` by the
   *    arguments `w1, …, wm`,
   *  - the substitution of the formal parameters `x1, …, xn` of the class `C` by the class
   *    arguments `v1, …, vn`,
   *  - the substitution of the self reference `this` by the value of the
   *    object `new C(v1, …, vn)`.
   *
   * = Operators =
   *
   * In principle, the rational numbers defined by `Rational` are
   * as natural as integers.
   *
   * But for the user of these abstractions, there is a noticeable
   * difference:
   *
   *  - We write `x + y`, if `x` and `y` are integers, but
   *  - We write `r.add(s)` if `r` and `s` are rational numbers.
   *
   * In Scala, we can eliminate this difference because operators can be used as identifiers.
   *
   * Thus, an identifier can be:
   *
   *  - ''Alphanumeric'': starting with a letter, followed by a sequence of letters or numbers
   *  - ''Symbolic'': starting with an operator symbol, followed by other operator symbols.
   *  - The underscore character `'_'` counts as a letter.
   *  - Alphanumeric identifiers can also end in an underscore, followed by some operator symbols.
   *
   * Examples of identifiers:
   *
   * {{{
   *   x1     *     +?%&     vector_++     counter_=
   * }}}
   *
   * == Operators for Rationals ==
   *
   * So, here is a more natural definition of class `Rational`:
   *
   * {{{
   *   class Rational(x: Int, y: Int) {
   *     private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
   *     private val g = gcd(x, y)
   *     def numer = x / g
   *     def denom = y / g
   *     def + (r: Rational) =
   *       new Rational(
   *         numer * r.denom + r.numer * denom,
   *         denom * r.denom
   *       )
   *     def - (r: Rational) = ...
   *     def * (r: Rational) = ...
   *     ...
   *   }
   * }}}
   *
   * and then rational numbers can be used like `Int` or `Double`:
   *
   * {{{
   *   val x = new Rational(1, 2)
   *   val y = new Rational(1, 3)
   *   x * x + y * y
   * }}}
   *
   * = Precedence Rules =
   *
   * The ''precedence'' of an operator is determined by its first character.
   *
   * The following table lists the characters in increasing order of priority precedence:
   *
   * {{{
   *   (all letters)
   *   |
   *   ^
   *   &
   *   < >
   *   = !
   *   :
   *   + -
   *   * / %
   *   (all other special characters)
   * }}}
   *
   * = Abstract Classes =
   *
   * Consider the task of writing a class for sets of integers with
   * the following operations.
   *
   * {{{
   *   abstract class IntSet {
   *     def incl(x: Int): IntSet
   *     def contains(x: Int): Boolean
   *   }
   * }}}
   *
   * `IntSet` is an ''abstract class''.
   *
   * Abstract classes can contain members which are
   * missing an implementation (in our case, `incl` and `contains`).
   *
   * Consequently, no instances of an abstract class can be created with
   * the operator `new`.
   *
   * = Class Extensions =
   *
   * Let's consider implementing sets as binary trees.
   *
   * There are two types of possible trees: a tree for the empty set, and
   * a tree consisting of an integer and two sub-trees.
   *
   * Here are their implementations:
   *
   * {{{
   *   class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
   *
   *     def contains(x: Int): Boolean =
   *       if (x < elem) left contains x
   *       else if (x > elem) right contains x
   *       else true
   *
   *     def incl(x: Int): IntSet =
   *       if (x < elem) new NonEmpty(elem, left incl x, right)
   *       else if (x > elem) new NonEmpty(elem, left, right incl x)
   *       else this
   *   }
   *
   *   class Empty extends IntSet {
   *     def contains(x: Int): Boolean = false
   *     def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
   *   }
   * }}}
   *
   * `Empty` and `NonEmpty` both ''extend'' the class `IntSet`.
   *
   * This implies that the types `Empty` and `NonEmpty` ''conform'' to the type `IntSet`
   *
   *  - an object of type `Empty` or `NonEmpty` can be used wherever an object of type
   *    `IntSet` is required.
   *
   * `IntSet` is called the ''superclass'' of `Empty`
   * and `NonEmpty`.
   *
   * `Empty` and `NonEmpty` are ''subclasses'' of
   * `IntSet`.
   *
   * In Scala, any user-defined class extends another class.
   *
   * If no superclass is given, the standard class `Object` in the Java package `java.lang` is assumed.
   *
   * The direct or indirect superclasses of a class `C` are called ''base classes'' of `C`.
   *
   * So, the base classes of `NonEmpty` are `IntSet` and `Object`.
   *
   * = Implementation and Overriding =
   *
   * The definitions of `contains` and `incl` in the classes
   * `Empty` and `NonEmpty` ''implement'' the abstract
   * functions in the base trait `IntSet`.
   *
   * It is also possible to ''redefine'' an existing, non-abstract
   * definition in a subclass by using `override`.
   *
   * {{{
   *   abstract class Base {
   *     def foo = 1
   *     def bar: Int
   *   }
   *
   *   class Sub extends Base {
   *     override def foo = 2
   *     def bar = 3
   *   }
   * }}}
   *
   * = Object Definitions =
   *
   * In the `IntSet` example, one could argue that there is really only a
   * single empty `IntSet`.
   *
   * So it seems overkill to have the user create many instances of it.
   *
   * We can express this case better with an ''object definition'':
   *
   * {{{
   *   object Empty extends IntSet {
   *     def contains(x: Int): Boolean = false
   *     def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
   *   }
   * }}}
   *
   * This defines a ''singleton object'' named `Empty`.
   *
   * No other `Empty` instances can be (or need to be) created.
   *
   * Singleton objects are values, so `Empty` evaluates to itself.
   *
   * = Dynamic Binding =
   *
   * Object-oriented languages (including Scala) implement
   * ''dynamic method dispatch''.
   *
   * This means that the code invoked by a method call depends on the
   * runtime type of the object that contains the method.
   */
  def dynamicBinding(res0: Boolean, res1: Boolean): Unit = {
    Empty contains 1 shouldBe res0
    new NonEmpty(7, Empty, Empty) contains 7 shouldBe res1
  }

  /**
   * Dynamic dispatch of methods is analogous to calls to
   * higher-order functions.
   *
   * Can we implement one concept in terms of the other?
   *
   *  - Objects in terms of higher-order functions?
   *  - Higher-order functions in terms of objects?
   *
   * = Traits =
   *
   * In Scala, a class can only have one superclass.
   *
   * But what if a class has several natural supertypes to which it conforms
   * or from which it wants to inherit code?
   *
   * Here, you could use `trait`s.
   *
   * A trait is declared like an abstract class, just with `trait` instead of
   * `abstract class`.
   *
   * {{{
   *   trait Planar {
   *     def height: Int
   *     def width: Int
   *     def surface = height * width
   *   }
   * }}}
   *
   * Classes, objects and traits can inherit from at most one class but
   * arbitrary many traits:
   *
   * {{{
   *   class Square extends Shape with Planar with Movable …
   * }}}
   *
   * On the other hand, traits cannot have (value) parameters, only classes can.
   *
   * = Scala's Class Hierarchy =
   *
   * <img src="/assets/scala_tutorial/scala_type_hierarchy.png" style="max-width: 100%" />
   *
   * == Top Types ==
   *
   * At the top of the type hierarchy we find:
   *
   *  - `Any`
   *    - The base type of all types
   *    - Methods: `==`, `!=`, `equals`, `hashCode`, `toString`
   *  - `AnyRef`
   *    - The base type of all reference types
   *    - Alias of `java.lang.Object`
   *  - `AnyVal`
   *    - The base type of all primitive types
   *
   * == Bottom Type ==
   *
   * `Nothing` is at the bottom of Scala's type hierarchy. It is a subtype
   * of every other type.
   *
   * There is no value of type `Nothing`.
   *
   * Why is that useful?
   *
   *  - To signal abnormal termination
   *  - As an element type of empty collections
   *
   * == The Null Type ==
   *
   * Every reference class type also has `null` as a value.
   *
   * The type of `null` is `Null`.
   *
   * `Null` is a subtype of every class that inherits from `Object`; it is
   * incompatible with subtypes of `AnyVal`.
   *
   * {{{
   *   val x = null         // x: Null
   *   val y: String = null // y: String
   *   val z: Int = null    // error: type mismatch
   * }}}
   *
   * = Exercise =
   *
   * The following `Reducer` abstract class defines how to
   * reduce a list of values into a single value by starting
   * with an initial value and combining it with each element
   * of the list:
   */
  def reducer(res0: Int, res1: Int): Unit = {
    abstract class Reducer(init: Int) {
      def combine(x: Int, y: Int): Int
      def reduce(xs: List[Int]): Int =
        xs match {
          case Nil     => init
          case y :: ys => combine(y, reduce(ys))
        }
    }

    object Product extends Reducer(1) {
      def combine(x: Int, y: Int): Int = x * y
    }

    object Sum extends Reducer(0) {
      def combine(x: Int, y: Int): Int = x + y
    }

    val nums = List(1, 2, 3, 4)

    Product.reduce(nums) shouldBe res0
    Sum.reduce(nums) shouldBe res1
  }
}
