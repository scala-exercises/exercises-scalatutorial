package scalatutorial.sections

/** @param name terms_and_types */
object TermsAndTypes extends ScalaTutorialSection {

  /**
    * = Scala Tutorial =
    *
    * The following set of sections provides a quick tutorial on the Scala language.
    *
    * The contents is based on the MOOCS [[https://www.coursera.org/learn/progfun1/home Functional Programming Principles in Scala]]
    * and [[https://www.coursera.org/learn/progfun2/home Functional Program Design in Scala]].
    *
    * = Elements of Programming =
    *
    * Programming languages give programmers ways to express computations.
    *
    * Every non-trivial programming language provides:
    *
    *  - primitive expressions representing the simplest elements ;
    *  - ways to ''combine'' expressions ;
    *  - ways to ''abstract'' expressions, which introduce a name for an expression by which it can then be referred to.
    *
    * = Primitive Expressions =
    *
    * Here are some examples of ''primitive expressions'':
    *
    *  - The number “1”:
    *
    * {{{
    *   1
    * }}}
    *
    *  - The boolean value “true”:
    *
    * {{{
    *   true
    * }}}
    *
    *  - The text “Hello, Scala!”:
    *
    * {{{
    *   "Hello, Scala!"
    * }}}
    *
    * (Note the usage of double quotes, `"`).
    *
    * = Compound Expressions =
    *
    * More complex expressions can be expressed by ''combining'' simpler expressions
    * using ''operators''. They can therefore express more complex computations:
    *
    *  - How many is one plus two?
    *
    * {{{
    *   1 + 2
    * }}}
    *
    *  - What is the result of the concatenation of the texts “Hello, ” and “Scala!”?
    *
    * {{{
    *   "Hello, " ++ "Scala!"
    * }}}
    *
    * = Evaluation =
    *
    * A non-primitive expression is evaluated as follows.
    *
    *  1. Take the leftmost operator
    *  1. Evaluate its operands (left before right)
    *  1. Apply the operator to the operands
    *
    *  The evaluation process stops once it results in a value.
    *
    * == Example ==
    *
    * Here is the evaluation of an arithmetic expression:
    *
    * {{{
    *   (1 + 2) * 3
    *   3 * 3
    *   9
    * }}}
    *
    */
  def evaluation(res0: Int, res1: String): Unit = {
    1 + 2 shouldBe res0
    "Hello, " ++ "Scala!" shouldBe res1
  }

  /**
    * = Method Calls =
    *
    * Another way to make complex expressions out of simpler expressions is to call
    * ''methods'' on expressions:
    *
    *  - What is the size of the text “Hello, Scala!”?
    *
    * {{{
    *   "Hello, Scala!".size
    * }}}
    *
    * Methods are ''applied'' on expressions using the ''dot notation''.
    *
    * The object on which the method is applied is named the ''target object''.
    *
    *  - What is the range of numbers between 1 and 10?
    *
    * {{{
    *   1.to(10)
    * }}}
    *
    * Methods can have ''parameters''. They are supplied between parentheses.
    *
    * In the below examples, the `abs` method returns the absolute value of a
    * number, and the `toUpperCase` method returns the target `String` in
    * upper case.
    */
  def methods(res0: Int, res1: String): Unit = {
    "Hello, Scala!".toUpperCase shouldBe res1
    -42.abs shouldBe res0
  }

  /**
    * = Operators Are Methods =
    *
    * Actually, operators are just methods with symbolic names:
    *
    * {{{
    *   3 + 2 == 3.+(2)
    * }}}
    *
    * The ''infix syntax'' allows you to omit the dot and the parentheses.
    *
    * The infix syntax can also be used with regular methods:
    *
    * {{{
    *   1.to(10) == 1 to 10
    * }}}
    *
    * = Values and Types =
    *
    * Expressions have a ''value'' and a ''type''. The evaluation model
    * defines how to get a value out of an expression. Types classify values.
    *
    * Both `0` and `1` are numbers, their type is `Int`.
    *
    * `"foo"` and `"bar"` are text, their type is `String`.
    *
    * = Static Typing =
    *
    * The Scala compiler statically checks that you don’t combine incompatible
    * expressions.
    *
    * Fill the following blank with values whose type is
    * different from `Int` and see the result:
    */
  def staticTyping(res0: Int): Unit = {
    1 to res0
  }

  /**
    * = Common Types =
    *
    *  - `Int`: 32-bit integers
    *  - `Double`: 64-bit floating point numbers
    *  - `Boolean`: boolean values
    *  - `String`: text
    *
    *  Note that type names always begin with an upper case.
    */
  def nothing(): Unit = ()

}
