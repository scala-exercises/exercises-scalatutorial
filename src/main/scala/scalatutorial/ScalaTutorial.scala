package scalatutorial

import org.scalaexercises.definitions.Library

import sections._

/** Quickly learn Scala through an interactive tutorial based on the first two courses of the Scala MOOCs.
  *
  * @param name scala_tutorial
  */
object ScalaTutorial extends Library {
  val owner = "scala-exercises"
  val repository = "exercises-scalatutorial"
  override val color = Some("#f26527")
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
    PolymorphicTypes,
    LazyEvaluation,
    TypeClasses
  )
}
