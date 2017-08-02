name := (name in ThisBuild).value

inThisBuild(Seq(
  name := "ifoption",
  organization := "org.lolhens",
  version := "0.2.0",

  scalaVersion := "2.12.3",

  externalResolvers := Seq(
    "artifactory-maven" at "http://lolhens.no-ip.org/artifactory/maven-public/",
    Resolver.url("artifactory-ivy", url("http://lolhens.no-ip.org/artifactory/ivy-public/"))(Resolver.ivyStylePatterns)
  ),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254"),

  publishTo := Some(Resolver.file("file", new File("target/releases")))
))

lazy val root = project.in(file("."))
  .settings(publishArtifact := false)
  .aggregate(
    ifoptionJVM_2_11,
    ifoptionJVM_2_12,
    ifoptionJVM_2_13,
    ifoptionJS_2_11,
    ifoptionJS_2_12
  )

lazy val ifoption = crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Pure)
  .settings(name := (name in ThisBuild).value)
  .settings(
    libraryDependencies ++= Seq(
      //"org.typelevel" %%% "cats" % "0.9.0"
    )
  )

lazy val ifoptionJVM_2_11 = ifoption.jvm.cross("2.11.11").settings(name := (name in ThisBuild).value)
lazy val ifoptionJVM_2_12 = ifoption.jvm.cross("2.12.3").settings(name := (name in ThisBuild).value)
lazy val ifoptionJVM_2_13 = ifoption.jvm.cross("2.13.0-M2").settings(name := (name in ThisBuild).value)
lazy val ifoptionJS_2_11 = ifoption.js.cross("2.11.11").settings(name := (name in ThisBuild).value)
lazy val ifoptionJS_2_12 = ifoption.js.cross("2.12.3").settings(name := (name in ThisBuild).value)
