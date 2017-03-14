package pers.you.spark.operators

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by You on 2017/2/28.
  */
object FlatMapOperator {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("FlatMapOperator").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val lineArray = Array("hello xuruyun", "hello xuruyun", "hello wangfei")
    val lineArrayRDD = sc.parallelize(lineArray)

    val words = lineArrayRDD.flatMap(_.split(" ")).foreach(println(_))
  }
}