import sbtcrossproject.{CrossType, crossProject}

inThisBuild(Seq(
  name := "ifoption",
  organization := "org.lolhens",
  version := "0.1.0",

  scalaVersion := "2.12.1",

  externalResolvers := Seq("artifactory" at "http://lolhens.no-ip.org/artifactory/maven-public/"),

  dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang"),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254"),

  publishTo := Some(Resolver.file("file", new File("target/releases")))
))

lazy val ifoption = crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Full).in(file("."))
  .settings(libraryDependencies ++= Seq(
    //toCrossGroupID("org.typelevel") %%% "cats" % "0.9.0"
  ))

lazy val ifoptionJVM = ifoption.jvm
lazy val ifoptionJS = ifoption.js
