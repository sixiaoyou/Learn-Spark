package pers.you.learn.operators

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/28.
  */
object ReduceByKey {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("ReduceByKey").setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2("xuruyun", 150), Tuple2("liangyongqi", 120), Tuple2("wangfei", 80), Tuple2("wangfei", 100),Tuple2("wangfei", 100))

    val scores = sc.parallelize(scoreList)
    scores.reduceByKey(_ - _).foreach(println(_))

  }
}