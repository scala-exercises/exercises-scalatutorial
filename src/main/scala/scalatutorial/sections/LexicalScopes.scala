package scalatutorial.sections

/** @param name lexical_scopes */
object LexicalScopes extends ScalaTutorialSection {

  /**
    * = Nested functions =
    *
    * It's good functional programming style to split up a task into many small functions.
    *
    * But the names of functions like `sqrtIter`, `improve`, and `isGoodEnough` (defined in the
    * previous section) matter only for the ''implementation'' of `sqrt`, not for its ''usage''.
    *
    * Normally we would not like users to access these functions directly.
    *
    * We can achieve this and at the same time avoid “name-space pollution” by
    * putting the auxiliary functions inside `sqrt`.
    *
    * = The `sqrt` Function, Take 2 =
    *
    * {{{
    *   def sqrt(x: Double) = {
    *     def sqrtIter(guess: Double, x: Double): Double =
    *       if (isGoodEnough(guess, x)) guess
    *       else sqrtIter(improve(guess, x), x)
    *
    *     def improve(guess: Double, x: Double) =
    *       (guess + x / guess) / 2
    *
    *     def isGoodEnough(guess: Double, x: Double) =
    *       abs(square(guess) - x) < 0.001
    *
    *     sqrtIter(1.0, x)
    *   }
    * }}}
    *
    * = Blocks in Scala =
    *
    *  - A block is delimited by braces `{ ... }`.
    *
    *  {{{
    *    {
    *      val x = f(3)
    *      x * x
    *    }
    *  }}}
    *
    *  - It contains a sequence of definitions or expressions.
    *  - The last element of a block is an expression that defines its value.
    *  - This return expression can be preceded by auxiliary definitions.
    *  - Blocks are themselves expressions; a block may appear everywhere an expression can.
    *
    * = Blocks and Visibility =
    *
    *  - The definitions inside a block are only visible from within the block.
    *  - The definitions inside a block ''shadow'' definitions of the same names
    *    outside the block.
    *
    * == Exercise: Scope Rules ==
    *
    * What is the value of `result` in the following program?
    *
    */
    def scopeRules(res0: Int): Unit = {
      val x = 0
      def f(y: Int) = y + 1
      val result = {
        val x = f(3)
        x * x
      } + x
      result shouldBe res0
    }

  /**
    * = Lexical Scoping =
    *
    * Definitions of outer blocks are visible inside a block unless they are shadowed.
    *
    * Therefore, we can simplify `sqrt` by eliminating redundant occurrences of the `x` parameter, which means
    * everywhere the same thing:
    *
    * = The `sqrt` Function, Take 3 =
    *
    * {{{
    *   def sqrt(x: Double) = {
    *     def sqrtIter(guess: Double): Double =
    *       if (isGoodEnough(guess)) guess
    *       else sqrtIter(improve(guess))
    *
    *     def improve(guess: Double) =
    *       (guess + x / guess) / 2
    *
    *     def isGoodEnough(guess: Double) =
    *       abs(square(guess) - x) < 0.001
    *
    *     sqrtIter(1.0)
    *   }
    * }}}
    *
    * = Semicolons =
    *
    * In Scala, semicolons at the end of lines are in most cases optional.
    *
    * You could write:
    *
    * {{{
    *   val x = 1;
    * }}}
    *
    * but most people would omit the semicolon.
    *
    * On the other hand, if there are more than one statements on a line, they need to be
    * separated by semicolons:
    *
    * {{{
    *   val y = x + 1; y * y
    * }}}
    *
    * = Semicolons and infix operators =
    *
    * One issue with Scala's semicolon convention is how to write expressions that span
    * several lines. For instance:
    *
    * {{{
    *   someLongExpression
    *   + someOtherExpression
    * }}}
    *
    * would be interpreted as ''two'' expressions:
    *
    * {{{
    *   someLongExpression;
    *   + someOtherExpression
    * }}}
    *
    * There are two ways to overcome this problem.
    *
    * You could write the multi-line expression in parentheses, because semicolons
    * are never inserted inside `(…)`:
    *
    * {{{
    *   (someLongExpression
    *   + someOtherExpression)
    * }}}
    *
    * Or you could write the operator on the first line, because this tells the Scala
    * compiler that the expression is not yet finished:
    *
    * {{{
    *   someLongExpression +
    *   someOtherExpression
    * }}}
    *
    * = Top-Level Definitions =
    *
    * In real Scala programs, `def` and `val` definitions must be written
    * within a top-level ''object definition'', in .scala file:
    *
    * {{{
    *   object MyExecutableProgram {
    *     val myVal = …
    *     def myMethod = …
    *   }
    * }}}
    *
    * The above code defines an ''object'' named `MyExecutableProgram`. You
    * can refer to its ''members'' using the usual dot notation:
    *
    * {{{
    *   MyExecutableProgram.myMethod
    * }}}
    *
    * The definition of `MyExecutableProgram` is ''top-level'' because it
    * is not nested within another definition.
    *
    * = Packages and Imports =
    *
    * Top-level definitions can be organized in ''packages'':
    *
    * {{{
    *   // file foo/Bar.scala
    *   package foo
    *   object Bar { … }
    * }}}
    *
    * {{{
    *   // file foo/Baz.scala
    *   package foo
    *   object Baz { … }
    * }}}
    *
    * Definitions located in a package are visible from other definitions
    * located in the same package:
    *
    * {{{
    *   // file foo/Baz.scala
    *   package foo
    *   object Baz {
    *     // Bar is visible because it is in the `foo` package too
    *     Bar.someMethod
    *   }
    * }}}
    *
    * On the other hand, definitions located in other packages are not directly
    * visible: you must use ''fully qualified names'' to refer to them:
    *
    * {{{
    *   // file quux/Quux.scala
    *   package quux
    *   object Quux {
    *     foo.Bar.someMethod
    *   }
    * }}}
    *
    * Finally, you can import names to avoid repeating their fully qualified form:
    *
    * {{{
    *   // file quux/Quux.scala
    *   package quux
    *   import foo.Bar
    *   object Quux {
    *     // Bar refers to the imported `foo.Bar`
    *     Bar.someMethod
    *   }
    * }}}
    */
  def nothing(): Unit = ()
}