resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("org.scala-exercises" % "sbt-exercise" % "0.4.0-SNAPSHOT", "0.13", "2.10")
addSbtPlugin("com.47deg"         % "sbt-org-policies" % "0.5.13")
