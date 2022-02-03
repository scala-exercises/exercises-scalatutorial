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

package scalatutorial

import org.scalaexercises.definitions.Library

import sections._

/**
 * Quickly learn Scala through an interactive tutorial based on the first two courses of the Scala
 * MOOCs.
 *
 * @param name
 *   scala_tutorial
 */
object ScalaTutorial extends Library {
  val owner          = "scala-exercises"
  val repository     = "exercises-scalatutorial"
  override val color = Some("#f26527")
  val logoPath       = "scala-tutorial"

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
