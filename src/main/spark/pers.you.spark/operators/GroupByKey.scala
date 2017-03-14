package pers.you.spark.operators

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/28.
  */
object GroupByKey {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("LinesCount").setMaster("local")
    val sc = new SparkContext(conf)

    val scoreList = Array(Tuple2("xuruyun", 150), Tuple2("liangyongqi", 120), Tuple2("wangfei", 80), Tuple2("wangfei", 200))
    val scoreListRDD = sc.parallelize(scoreList)
    val groupScores = scoreListRDD.groupByKey()
    groupScores.foreach(score => {
      println(score._1)
      score._2.foreach(everyScore => println(everyScore))
      println("=============================================")
    })
  }
}
