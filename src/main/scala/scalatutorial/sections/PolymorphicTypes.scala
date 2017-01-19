package scalatutorial.sections

/** @param name polymorphic_types */
object PolymorphicTypes extends ScalaTutorialSection {

  /**
    * Remember the definition of `IntSet` (in section Object Oriented Programming):
    *
    * {{{
    *   abstract class IntSet {
    *     def incl(x: Int): IntSet
    *     def contains(x: Int): Boolean
    *   }
    * }}}
    *
    * = Type Parameters =
    *
    * It seems too narrow to define only sets with `Int` elements.
    *
    * We'd need another class hierarchy for `Double` lists, and so on,
    * one for each possible element type.
    *
    * We can generalize the definition using a ''type parameter'':
    *
    * {{{
    *   abstract class Set[A] {
    *     def incl(a: A): Set[A]
    *     def contains(a: A): Boolean
    *   }
    *   class Empty[A] extends Set[A] {
    *     …
    *   }
    *   class NonEmpty[A](elem: A, left: Set[A], right: Set[A]) extends Set[A] {
    *     …
    *   }
    * }}}
    *
    * Type parameters are written in square brackets, e.g. `[A]`.
    *
    * = Generic Functions =
    *
    * Like classes, functions can have type parameters.
    *
    * For instance, here is a function that creates a set consisting of a single element.
    *
    * {{{
    *   def singleton[A](elem: A) = new NonEmpty[A](elem, new Empty[A], new Empty[A])
    * }}}
    *
    * We can then write:
    *
    * {{{
    *   singleton[Int](1)
    *   singleton[Boolean](true)
    * }}}
    *
    * = Type Inference =
    *
    * In fact, the Scala compiler can usually deduce the correct type
    * parameters from the value arguments of a function call.
    *
    * So, in most cases, type parameters can be left out. You could also write:
    *
    * {{{
    *   singleton(1)
    *   singleton(true)
    * }}}
    *
    * = Types and Evaluation =
    *
    * Type parameters do not affect evaluation in Scala.
    *
    * We can assume that all type parameters and type arguments are removed
    * before evaluating the program.
    *
    * This is also called ''type erasure''.
    *
    * Languages that use type erasure include Java, Scala, Haskell, ML, OCaml.
    *
    * Some other languages keep the type parameters around at run time, these include C++, C#, F#.
    *
    * = Polymorphism =
    *
    * Polymorphism means that a function type comes "in many forms".
    *
    * In programming it means that
    *
    *  - the function can be applied to arguments of many types, or
    *  - the type can have instances of many types.
    *
    * We have seen two principal forms of polymorphism:
    *
    *  - subtyping: instances of a subclass can be passed to a base class
    *  - generics: instances of a function or class are created by type parameterization.
    *
    * The remaining subsections compare their interaction.
    *
    * Consider the following class hierarchy:
    *
    * <img src="/assets/scala_tutorial/animals.svg" style="max-width: 20em;" />
    *
    * {{{
    *   trait Animal {
    *     def fitness: Int
    *   }
    *
    *   trait Reptile extends Animal
    *
    *   trait Mammal extends Animal
    *
    *   trait Zebra extends Mammal {
    *     def zebraCount: Int
    *   }
    *
    *   trait Giraffe extends Mammal
    * }}}
    *
    * = Type Bounds =
    *
    * Consider the method `selection` that takes two animals as parameters
    * and returns the one with the highest `fitness` value:
    *
    * What would be the best type you can give to `selection`? Maybe:
    *
    * {{{
    *   def selection(a1: Animal, a2: Animal): Animal
    * }}}
    *
    * In most situations this is fine, but can one be more precise?
    *
    * One might want to express that `selection`
    * takes `Zebra`s to `Zebra`s and `Reptile`s to `Reptile`s.
    *
    * == Upper Bounds ==
    *
    * A way to express this is:
    *
    * {{{
    *   def selection[A <: Animal](a1: A, a2: A): A =
    *     if (a1.fitness > a2.fitness) a1 else a2
    * }}}
    *
    * Here, “`<: Animal`” is an ''upper bound'' of the type parameter `A`.
    *
    * It means that `A` can be instantiated only to types that conform to `Animal`.
    *
    * Generally, the notation
    *
    *  - `A <: B` means: ''`A` is a subtype of `B`'', and
    *  - `A >: B` means: ''`A` is a supertype of `B`'', or ''`B` is a subtype of `A`''.
    *
    * == Lower Bounds ==
    *
    * You can also use a lower bound for a type variable.
    *
    * {{{
    *   A >: Reptile
    * }}}
    *
    * The type parameter `A` that can range only over ''supertypes'' of `Reptile`.
    *
    * So `A` could be one of `Reptile`, `Animal`, `AnyRef`, or `Any`.
    *
    * (We will see later on in this section where lower bounds are useful).
    *
    * == Mixed Bounds ==
    *
    * Finally, it is also possible to mix a lower bound with an upper bound.
    *
    * For instance,
    *
    * {{{
    *   A >: Zebra <: Animal
    * }}}
    *
    * would restrict `A` any type on the interval between `Zebra` and `Animal`.
    *
    * = Covariance =
    *
    * There's another interaction between subtyping and type parameters we
    * need to consider.
    *
    * Consider the following type modeling a field containing an animal:
    *
    * {{{
    *   trait Field[A] {
    *     def get: A // returns the animal that lives in this field
    *   }
    * }}}
    *
    * Given
    *
    * {{{
    *   Zebra <: Mammal
    * }}}
    *
    * is
    *
    * {{{
    *   Field[Zebra] <: Field[Mammal]
    * }}}
    *
    * ?
    *
    * Intuitively, this makes sense: a field containing a zebra is a special case of a field
    * containing an arbitrary mammal.
    *
    * We call types for which this relationship holds ''covariant''
    * because their subtyping relationship varies with the type parameter.
    *
    * Does covariance make sense for all types, not just for `Field`?
    *
    * === Arrays ===
    *
    * For perspective, let's look at arrays in Java (and C#).
    *
    * Reminder:
    *
    *  - An array of `T` elements is written `T[]` in Java.
    *  - In Scala we use parameterized type syntax `Array[T]` to refer to the same type.
    *
    * Arrays in Java are covariant, so one would have:
    *
    * {{{
    *   Zebra[] <: Mammal[]
    * }}}
    *
    * But covariant array typing causes problems.
    *
    * To see why, consider the Java code below:
    *
    * {{{
    *   Zebra[] zebras = new Zebra[]{ new Zebra() }  // Array containing 1 `Zebra`
    *   Mammal[] mammals = zebras      // Allowed because arrays are covariant in Java
    *   mammals[0] = new Giraffe()     // Allowed because a `Giraffe` is a subtype of `Mammal`
    *   Zebra zebra = zebras[0]        // Get the first `Zebra` … which is actually a `Giraffe`!
    * }}}
    *
    * It looks like we assigned in the last line a `Giraffe` to a
    * variable of type `Zebra`!
    *
    * What went wrong?
    *
    * === The Liskov Substitution Principle ===
    *
    * The following principle, stated by Barbara Liskov, tells us when a
    * type can be a subtype of another.
    *
    * If `A <: B`, then everything one can to do with a value of type `B` one should also
    * be able to do with a value of type `A`.
    *
    * The problematic array example would be written as follows in Scala:
    *
    * {{{
    *   val zebras: Array[Zebra] = Array(new Zebra)
    *   val mammals: Array[Mammal] = zebras
    *   mammals(0) = new Giraffe
    *   val zebra: Zebra = zebras(0)
    * }}}
    *
    * If you try to compile this example you will get a compile error at line 2:
    *
    * {{{
    *   type mismatch;
    *     found   : Array[Zebra]
    *     required: Array[Mammal]
    * }}}
    *
    * = Variance =
    *
    * We have seen that some types should be covariant whereas
    * others should not.
    *
    * Roughly speaking, a type that accepts mutations of its elements should
    * not be covariant.
    *
    * But immutable types can be covariant, if some conditions
    * on methods are met.
    *
    * = Definition of Variance =
    *
    * Say `C[T]` is a parameterized type and `A`, `B` are types such that `A <: B`.
    *
    * In general, there are ''three'' possible relationships between `C[A]` and `C[B]`:
    *
    *  - `C[A] <: C[B]`, `C` is ''covariant'',
    *  - `C[A] >: C[B]`, `C` is ''contravariant'',
    *  - neither `C[A]` nor `C[B]` is a subtype of the other, `C` is ''nonvariant''.
    *
    * Scala lets you declare the variance of a type by annotating the type parameter:
    *
    *  - `class C[+A] { … }`, `C` is ''covariant'',
    *  - `class C[-A] { … }`, `C` is ''contravariant'',
    *  - `class C[A] { … }`, `C` is ''nonvariant''.
    *
    * == Typing Rules for Functions ==
    *
    * Generally, we have the following rule for subtyping between function types:
    *
    * If `A2 <: A1` and `B1 <: B2`, then
    *
    * `A1 => B1  <:  A2 => B2`
    *
    * So functions are ''contravariant'' in their argument type(s) and
    * ''covariant'' in their result type.
    *
    * This leads to the following revised definition of the `Function1` trait:
    *
    * {{{
    *   trait Function1[-T, +U] {
    *     def apply(x: T): U
    *   }
    * }}}
    *
    * == Contravariance Example ==
    *
    * Consider the following type modeling a veterinary:
    *
    * {{{
    *   trait Vet[A] {
    *     def treat(a: A): Unit // Treats an animal of type `A`
    *   }
    * }}}
    *
    * In such a case, intuitively, it makes sense to have `Vet[Mammal] <: Vet[Zebra]` because
    * a vet that can treat any mammal is able to to treat a zebra in particular. This is
    * an example of a contravariant type.
    *
    * == Variance Checks ==
    *
    * We have seen in the array example that the combination of covariance with
    * certain operations is unsound.
    *
    * In the case of `Array`, the problematic combination is:
    *  - the covariant type parameter `T`
    *  - which appears in parameter position of the method `update`.
    *
    * The Scala compiler will check that there are no problematic combinations when
    * compiling a class with variance annotations.
    *
    * Roughly,
    *
    *  - ''covariant'' type parameters can only appear in method results.
    *  - ''contravariant'' type parameters can only appear in method parameters.
    *  - ''invariant'' type parameters can appear anywhere.
    *
    * The precise rules are a bit more involved, fortunately the Scala compiler performs them for us.
    *
    * === Variance-Checking the Function Trait ===
    *
    * Let's have a look again at Function1:
    *
    * {{{
    *   trait Function1[-T, +U] {
    *     def apply(x: T): U
    *   }
    * }}}
    *
    * Here,
    *
    *  - `T` is contravariant and appears only as a method parameter type
    *  - `U` is covariant and appears only as a method result type
    *
    * So the method is checks out OK.
    *
    * = Making Classes Covariant =
    *
    * Sometimes, we have to put in a bit of work to make a class covariant.
    *
    * Consider adding a `prepend` method to `Stream` which prepends a given
    * element, yielding a new stream.
    *
    * A first implementation of `prepend` could look like this:
    *
    * {{{
    *   trait Stream[+T] {
    *     def prepend(elem: T): Stream[T] = Stream.cons(elem, this)
    *   }
    * }}}
    *
    * But that does not work!
    *
    * Why does the above code not type-check?
    *
    * `prepend` fails variance checking.
    *
    * Indeed, the compiler is right to throw out `Stream` with `prepend`,
    * because it violates the Liskov Substitution Principle:
    *
    * Here's something one can do with a stream `mammals` of type `Stream[Mammal]`:
    *
    * {{{
    *   mammals.prepend(new Giraffe)
    * }}}
    *
    * But the same operation on a list `zebras` of type
    * `Stream[Zebra]` would lead to a type error:
    *
    * {{{
    *   zebras.prepend(new Giraffe)
    *                  ^ type mismatch
    *                  required: Zebra
    *                  found: Giraffe
    * }}}
    *
    * So, `Stream[Zebra]` cannot be a subtype of `Stream[Mammal]`.
    *
    * But `prepend` is a natural method to have on immutable lists!
    *
    * How can we make it variance-correct?
    *
    * We can use a ''lower bound'':
    *
    * {{{
    *   def prepend [U >: T](elem: U): Stream[U] = Stream.cons(elem, this)
    * }}}
    *
    * This passes variance checks, because:
    *
    *  - covariant type parameters may appear in lower bounds of method type parameters
    *  - contravariant type parameters may appear in upper bounds of method
    *
    * = Exercise =
    *
    * Complete the following implementation of the `size` function that returns
    * the size of a given list.
    */
  def sizeExercise(res0: Int, res1: Int): Unit = {
    def size[A](xs: List[A]): Int =
      xs match {
        case Nil => res0
        case y :: ys => res1 + size(ys)
      }
    size(Nil) shouldBe 0
    size(List(1, 2)) shouldBe 2
    size(List("a", "b", "c")) shouldBe 3
  }

}
