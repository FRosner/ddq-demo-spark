package de.frosner.ddq.demo

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming._
import de.frosner.ddq.core._
import de.frosner.ddq.reporters.Log4jReporter
import org.apache.spark.sql._
import org.apache.spark.sql.types._

object StreamReader extends App {
  
  val sc = new SparkContext()
  val sqlContext = new SQLContext(sc)
  import sqlContext.implicits._

  val ssc = new StreamingContext(sc, Seconds(5))
  val lines = ssc.socketTextStream("datagenerator", 9999)
  lines.foreachRDD { rdd =>
    val df = rdd.map{ line =>
      val Array(number, string) = line.split(" ")
      (number, string)
    }.toDF("potentialNumber", "string").withColumn(
      "number",
      new Column("potentialNumber").cast(IntegerType)
    )

    val reporter = Log4jReporter()
    Check(
      dataFrame = df,
      cacheMethod = None,
      displayName = Some("numbers")
    ).satisfies(
      new Column("number") > 0
    ).hasUniqueKey(
      "string"
    ).satisfies(
      !new Column("string").startsWith("z")
    ).run(reporter)
  }

  ssc.start()
  ssc.awaitTermination()

}
