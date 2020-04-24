/*
 * Copyright 2016-2020 47 Degrees <https://47deg.com>
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

package scalatutorial.utils

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
