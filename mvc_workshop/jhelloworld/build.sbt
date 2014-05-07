name := "jhelloworld"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "cr.prodigious" % "model" % "1.0"
)     

play.Project.playJavaSettings
