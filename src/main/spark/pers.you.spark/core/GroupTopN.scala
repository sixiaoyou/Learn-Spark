package pers.you.spark.core

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/3/1.
  */
object GroupTopN {
  def main(args: Array[String]): Unit ={
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("ReduceByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("test.txt")
    val groupedLines = lines.map(x =>(x.split(" ")(0),x.split(" ")(1))).groupByKey()

    val topList = groupedLines.map(x=>{
      var t = List[Int]()
      for(temp <- x._2){
        t = temp.toInt :: t
      }
      (x._1,t.sortBy(x=> -x).take(2))
    })
    topList.foreach(println(_))
  }

}
