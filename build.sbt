name := "IfOption"

lazy val settings = Seq(
  version := "0.0.0",

  scalaOrganization := "org.typelevel",
  scalaVersion := "2.11.8",

  resolvers := Seq("Artifactory" at "http://lolhens.no-ip.org/artifactory/libs-release/"),

  classpathTypes += "maven-plugin",

  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats" % "0.8.1"
  ),

  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3"),
  //addCompilerPlugin("com.milessabin" % "si2712fix-plugin_2.11.8" % "1.2.0"),

  dependencyUpdatesExclusions := moduleFilter(organization = "org.scala-lang"),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254")

)

lazy val root = project.in(file("."))
  .settings(settings: _*)
