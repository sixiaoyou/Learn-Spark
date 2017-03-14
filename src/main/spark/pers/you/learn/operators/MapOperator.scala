package pers.you.learn.operators

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by You on 2017/2/28.
  */
object MapOperator {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("MapOperator").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val numbers = Array(1, 2, 3, 4, 5)
    val numbersRDD = sc.parallelize(numbers)
    val results = numbersRDD.map(x => x * 10)
    results.foreach(println(_))
  }
}
