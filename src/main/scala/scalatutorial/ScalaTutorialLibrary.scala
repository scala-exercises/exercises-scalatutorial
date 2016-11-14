package scalatutorial
import org.scalaexercises.definitions._

/** Quickly learn Scala through an interactive tutorial.
  *
  * @param name scala_tutorial
  */
object ScalaTutorialLibrary extends Library {
  override def owner = "scala-exercises"
  override def repository = "exercises-fpprinciples"
  override def color = Some("#224951")
  override def logoPath = "scala-tutorial"

  override def sections = List(
    FunctionsAndEvaluationSection
  )
}
