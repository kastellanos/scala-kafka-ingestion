name := "ingestion-webapp"

version := "0.1"

scalaVersion := "2.11.8"

organization := "kastellanos"


libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.8"


libraryDependencies += "org.apache.kafka" %% "kafka" % "2.1.0"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.0"
libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.0"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.1.0"


libraryDependencies += "net.liftweb" %% "lift-json" % "3.3.0"
enablePlugins(DockerPlugin)

dockerAutoPackageJavaApplication()
