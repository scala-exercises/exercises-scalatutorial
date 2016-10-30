package fpprincipleslib
import org.scalaexercises.definitions._

/** Exercises for the "Functional Programming Principles in Scala", part of the FP in Scala specialized program by EPFL.
  *
  * @param name fp_principles
  */
object FpPrinciplesLibrary extends Library {
  override def owner = "scala-exercises"
  override def repository = "exercises-fpprinciples"
  override def color = Some("#585858")
  override def logoPath = "fpprinciples"

  override def sections = List(
    FunctionsAndEvaluationSection
  )
}
