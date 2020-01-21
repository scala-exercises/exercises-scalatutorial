resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("org.scala-exercises" % "sbt-exercise"     % "0.5.0-SNAPSHOT")
addSbtPlugin("com.47deg"           % "sbt-org-policies" % "0.12.3")