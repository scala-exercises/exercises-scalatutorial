/*
 * scala-exercises - exercises-scalatutorial
 * Copyright (C) 2015-2016 47 Degrees, LLC. <http://www.47deg.com>
 */

package scalatutorial.aux

object Sorting {

  def insertionSort[A](xs: List[A])(implicit ord: Ordering[A]): List[A] = {
    def insert(y: A, ys: List[A]): List[A] =
      ys match {
        case List() => y :: List()
        case z :: zs =>
          if (ord.lt(y, z)) y :: z :: zs
          else z :: insert(y, zs)
      }

    xs match {
      case List()  => List()
      case y :: ys => insert(y, insertionSort(ys))
    }
  }

}
