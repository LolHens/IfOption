logLevel := Level.Warn

resolvers ++= Seq(
  "lolhens-maven" at "http://artifactory.lolhens.de/artifactory/maven-public/",
  Resolver.url("lolhens-ivy", url("http://artifactory.lolhens.de/artifactory/ivy-public/"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.4")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.1")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

addSbtPlugin("org.scala-native" % "sbt-crossproject" % "0.2.2")

//addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.0.0-M1")

//addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.3.3")
