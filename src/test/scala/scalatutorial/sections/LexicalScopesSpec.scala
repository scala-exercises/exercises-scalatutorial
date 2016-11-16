package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class LexicalScopesSpec extends Spec with Checkers {

  def `check scope rules`: Unit = {
    check(Test.testSuccess(LexicalScopes.scopeRules _, 16 :: HNil))
  }

}
