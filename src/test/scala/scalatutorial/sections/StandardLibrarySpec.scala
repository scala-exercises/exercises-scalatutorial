package scalatutorial.sections

import org.scalacheck.Shapeless._
import org.scalaexercises.Test
import org.scalatest.Spec
import org.scalatest.prop.Checkers
import shapeless.HNil

class StandardLibrarySpec extends Spec with Checkers {

  def `check insertion sort`: Unit = {
    check(Test.testSuccess(StandardLibrary.insertionSort _, ((_: Int) < (_: Int)) ::  List.empty[Int] :: HNil))
  }

}
