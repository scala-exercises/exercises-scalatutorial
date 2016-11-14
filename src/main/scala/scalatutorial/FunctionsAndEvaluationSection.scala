package scalatutorial

import org.scalatest._
import org.scalaexercises.definitions._

/** @param name functions_and_evaluation
  */

object FunctionsAndEvaluationSection extends FlatSpec with Matchers with Section {
  /** = Exercise block title =
    *
    * Text describing background about the exercise, can be as long as needed.
    *
    * {{{
    *   // Scala code blocks can also be added to enhance your documentation.
    * }}}
    *
    * Also, documentation can be broken in as many paragraphs as necessary.
    */
  def functionAssert(res0: Boolean): Unit = {
    true shouldBe res0
  }

  /** And obviously you can add as many documentation and exercises as you need
    * to make your point ;-).
    */
  def functionFalseAssert(res0: Boolean): Unit = {
    false shouldBe res0
  }
}
