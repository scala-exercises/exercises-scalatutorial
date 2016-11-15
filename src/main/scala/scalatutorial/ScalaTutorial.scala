package scalatutorial

import org.scalaexercises.definitions.Library

import sections._

/** Quickly learn Scala through an interactive tutorial.
  *
  * @param name scala_tutorial
  */
object ScalaTutorial extends Library {
  val owner = "scala-exercises"
  val repository = "exercises-fpprinciples"
  override val color = Some("#224951")
  val logoPath = "scala-tutorial"

  val sections = List(
    TermsAndTypes,
    DefinitionsAndEvaluation,
    FunctionalLoops,
    LexicalScopes,
    TailRecursion,
    StructuringInformation,
    HigherOrderFunctions,
    StandardLibrary,
    SyntacticConveniences,
    ObjectOrientedProgramming,
    ImperativeProgramming,
    ClassesVsCaseClasses,
    LazyEvaluation,
    PolymorphicTypes,
    TypeClasses
  )
}
