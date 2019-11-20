/*
 *  scala-exercises - exercises-scalatutorial
 *  Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 */

package scalatutorial.sections

import scalatutorial.utils.{BankAccount, Note}

/** @param name classes_vs_case_classes */
object ClassesVsCaseClasses extends ScalaTutorialSection {

  /**
   * In the previous sections we have seen how case classes could be
   * used to achieve information aggregation, and also how classes
   * could be used to achieve data abstraction or to define stateful
   * objects.
   *
   * What are the relationship between classes and case classes? How
   * do they differ?
   *
   * = Creation and Manipulation =
   *
   * Remember the class definition of `BankAccount`:
   *
   * {{{
   *   class BankAccount {
   *
   *     private var balance = 0
   *
   *     def deposit(amount: Int): Unit = {
   *       if (amount > 0) balance = balance + amount
   *     }
   *
   *     def withdraw(amount: Int): Int =
   *       if (0 < amount && amount <= balance) {
   *         balance = balance - amount
   *         balance
   *       } else throw new Error("insufficient funds")
   *   }
   * }}}
   *
   * And the case class definition of `Note`:
   *
   * {{{
   *   case class Note(name: String, duration: String, octave: Int)
   * }}}
   *
   * Let’s create some instances of `BankAccount` and `Note` and manipulate them:
   */
  def creationAndManipulation(res0: String): Unit = {
    val aliceAccount = new BankAccount
    val c3           = Note("C", "Quarter", 3)

    c3.name shouldBe res0
  }

  /**
   * We see that creating a class instance requires the keyword `new`, whereas
   * this is not required for case classes.
   *
   * Also, we see that the case class constructor parameters are promoted to
   * members, whereas this is not the case with regular classes.
   *
   * = Equality =
   *
   */
  def equality(res0: Boolean, res1: Boolean): Unit = {
    val aliceAccount = new BankAccount
    val bobAccount   = new BankAccount

    aliceAccount == bobAccount shouldBe res0

    val c3     = Note("C", "Quarter", 3)
    val cThree = Note("C", "Quarter", 3)

    c3 == cThree shouldBe res1
  }

  /**
   * In the above example, the same definitions of bank accounts lead to different
   * values, whereas the same definitions of notes lead to equal values.
   *
   * As we have seen in the previous sections, stateful classes introduce a notion of ''identity''
   * that does not exist in case classes. Indeed, the value of `BankAccount` can change over
   * time whereas the value of a `Note` is immutable.
   *
   * In Scala, by default, comparing objects will compare their identity, but in the
   * case of case class instances, the equality is redefined to compare the values of
   * the aggregated information.
   *
   * = Pattern Matching =
   *
   * We saw how pattern matching can be used to extract information from a case class instance:
   *
   * {{{
   *   c3 match {
   *     case Note(name, duration, octave) => s"The duration of c3 is $duration"
   *   }
   * }}}
   *
   * By default, pattern matching does not work with regular classes.
   *
   * = Extensibility =
   *
   * A class can extend another class, whereas a case class can not extend
   * another case class (because it would not be possible to correctly
   * implement their equality).
   *
   * = Case Classes Encoding =
   *
   * We saw the main differences between classes and case classes.
   *
   * It turns out that case classes are just a special case of classes,
   * whose purpose is to aggregate several values into a single value.
   *
   * The Scala language provides explicit support for this use case
   * because it is very common in practice.
   *
   * So, when we define a case class, the Scala compiler defines a class
   * enhanced with some more methods and a companion object.
   *
   * For instance, the following case class definition:
   *
   * {{{
   *   case class Note(name: String, duration: String, octave: Int)
   * }}}
   *
   * Expands to the following class definition:
   *
   * {{{
   *   class Note(_name: String, _duration: String, _octave: Int) extends Serializable {
   *
   *     // Constructor parameters are promoted to members
   *     val name = _name
   *     val duration = _duration
   *     val octave = _octave
   *
   *     // Equality redefinition
   *     override def equals(other: Any): Boolean = other match {
   *       case that: Note =>
   *         (that canEqual this) &&
   *           name == that.name &&
   *           duration == that.duration &&
   *           octave == that.octave
   *       case _ => false
   *     }
   *
   *     def canEqual(other: Any): Boolean = other.isInstanceOf[Note]
   *
   *     // Java hashCode redefinition according to equality
   *     override def hashCode(): Int = {
   *       val state = Seq(name, duration, octave)
   *       state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
   *     }
   *
   *     // toString redefinition to return the value of an instance instead of its memory addres
   *     override def toString = s"Note($name,$duration,$octave)"
   *
   *     // Create a copy of a case class, with potentially modified field values
   *     def copy(name: String = name, duration: String = duration, octave: Int = octave): Note =
   *       new Note(name, duration, octave)
   *
   *   }
   *
   *   object Note {
   *
   *     // Constructor that allows the omission of the `new` keyword
   *     def apply(name: String, duration: String, octave: Int): Note =
   *       new Note(name, duration, octave)
   *
   *     // Extractor for pattern matching
   *     def unapply(note: Note): Option[(String, String, Int)] =
   *       if (note eq null) None
   *       else Some((note.name, note.duration, note.octave))
   *
   *   }
   * }}}
   */
  def encoding(res0: String, res1: Boolean, res2: String): Unit = {
    val c3 = Note("C", "Quarter", 3)
    c3.toString shouldBe res0
    val d = Note("D", "Quarter", 3)
    c3.equals(d) shouldBe res1
    val c4 = c3.copy(octave = 4)
    c4.toString shouldBe res2
  }
}
