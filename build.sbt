name := (name in ThisBuild).value

inThisBuild(Seq(
  name := "ifoption",
  organization := "org.lolhens",
  version := "0.2.1-SNAPSHOT",

  scalaVersion := "2.12.6",

  resolvers ++= Seq(
    "lolhens-maven" at "http://artifactory.lolhens.de/artifactory/maven-public/",
    Resolver.url("lolhens-ivy", url("http://artifactory.lolhens.de/artifactory/ivy-public/"))(Resolver.ivyStylePatterns)
  ),

  scalacOptions ++= Seq("-Xmax-classfile-name", "127")
))

lazy val root = project.in(file("."))
  .settings(publishArtifact := false)
  .aggregate(
    ifoptionJVM_2_12
    //ifoptionJVM_2_13,
    //ifoptionJS_2_11,
    //ifoptionJS_2_12,
    //ifoptionNative_2_11
  )

lazy val ifoption = crossProject(JVMPlatform).crossType(CrossType.Pure)
  .settings(name := (name in ThisBuild).value)
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "1.1.0"
    )
  )

//lazy val ifoptionJVM_2_11 = ifoption.jvm.cross("2.11.12").settings(name := (name in ThisBuild).value)
lazy val ifoptionJVM_2_12 = ifoption.jvm.settings(name := (name in ThisBuild).value)
//lazy val ifoptionJVM_2_13 = ifoption.jvm.cross("2.13.0-M2").settings(name := (name in ThisBuild).value)
//lazy val ifoptionJS_2_11 = ifoption.js.cross("2.11.12").settings(name := (name in ThisBuild).value)
//lazy val ifoptionJS_2_12 = ifoption.js.cross("2.12.4").settings(name := (name in ThisBuild).value)
//lazy val ifoptionNative_2_11 = ifoption.native.cross("2.11.11").settings(name := (name in ThisBuild).value)
