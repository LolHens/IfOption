name := "ifoption"
organization := "org.lolhens"
version := "0.0.0"

scalaVersion := "2.12.1"

resolvers := Seq("artifactory" at "http://lolhens.no-ip.org/artifactory/maven-public/")

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % "0.9.0"
)

dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang")

scalacOptions ++= Seq("-Xmax-classfile-name", "254")

publishTo := Some(Resolver.file("file", new File("target/releases")))
