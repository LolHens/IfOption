import sbtcrossproject.{CrossType, crossProject}

name := (name in ThisBuild).value

inThisBuild(Seq(
  name := "ifoption",
  organization := "org.lolhens",
  version := "0.1.1",

  scalaVersion := "2.12.1",

  externalResolvers := Seq("artifactory" at "http://lolhens.no-ip.org/artifactory/maven-public/"),

  dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang"),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254"),

  publishTo := Some(Resolver.file("file", new File("target/releases")))
))

lazy val root = project.in(file("."))
  .settings(publishArtifact := false)
  .aggregate(ifoptionJVM, ifoptionJS)

lazy val ifoption = crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Pure)
  .settings(name := (name in ThisBuild).value)
  .settings(
    libraryDependencies ++= Seq(
      //toCrossGroupID("org.typelevel") %%% "cats" % "0.9.0"
    )
  )

lazy val ifoptionJVM = ifoption.jvm
lazy val ifoptionJS = ifoption.js
