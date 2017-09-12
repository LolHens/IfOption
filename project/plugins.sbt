logLevel := Level.Warn

externalResolvers := Seq(
  Resolver.defaultLocal,
  "artifactory-maven" at "http://lolhens.no-ip.org/artifactory/maven-public/",
  Resolver.url("artifactory-ivy", url("http://lolhens.no-ip.org/artifactory/ivy-public/"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.1")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.0.1")

addSbtPlugin("com.lucidchart" % "sbt-cross" % "3.0")

addSbtPlugin("org.scala-native" % "sbt-crossproject" % "0.2.0")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.0.0-M1")

addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.3.2")
