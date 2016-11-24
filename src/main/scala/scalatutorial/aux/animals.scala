package scalatutorial.aux

trait Animal {
  def fitness: Int
}

trait Reptile extends Animal

trait Mammal extends Animal

trait Zebra extends Mammal {
  def zebraCount: Int
}

trait Giraffe extends Mammal
