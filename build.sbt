organization  := "de.frosner"

version       := "1.0.0"

name          := "ddq-demo-spark"

scalaVersion  := "2.10.5"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.6.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-hive" % "1.6.0" % "provided"

libraryDependencies += "com.github.FRosner" % "drunken-data-quality" % "3.1.0"

fork := true

resolvers += "jitpack" at "https://jitpack.io"
