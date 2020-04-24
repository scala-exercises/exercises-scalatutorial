import sbt.Keys._
import sbt._
import com.alejandrohdezma.sbt.github.SbtGithubPlugin

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = plugins.JvmPlugin && SbtGithubPlugin

  object autoImport {

    lazy val V = new {
      val shapeless: String           = "2.3.3"
      val scala: String               = "2.13.2"
      val scalaExercises: String      = "0.6.0-SNAPSHOT"
      val scalacheck: String          = "1.14.3"
      val scalacheckShapeless: String = "1.2.5"
      val scalatest: String           = "3.1.1"
      val scalatestplusScheck: String = "3.1.1.1"
    }

    def dep(artifactId: String) = "org.scala-exercises" %% artifactId % V.scalaExercises

    lazy val exercisesSettings = Seq(
      libraryDependencies ++= Seq(
        dep("exercise-compiler"),
        dep("definitions"),
        "com.chuusai"                %% "shapeless"                 % V.shapeless,
        "org.scalatest"              %% "scalatest"                 % V.scalatest,
        "org.scalacheck"             %% "scalacheck"                % V.scalacheck,
        "org.scalatestplus"          %% "scalacheck-1-14"           % V.scalatestplusScheck,
        "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % V.scalacheckShapeless
      )
    )
  }

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      organization := "org.scala-exercises",
      organizationName := "47 Degrees",
      organizationHomepage := Some(url("https://47deg.com")),
      scalaVersion := autoImport.V.scala,
      resolvers ++= Seq(
        Resolver.sonatypeRepo("snapshots"),
        Resolver.sonatypeRepo("releases")
      )
    )
}
