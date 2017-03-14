package pers.you.spark.operators


import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by You on 2017/2/28.
  */
object LinesCount {
  def main(args: Array[String]): Unit ={
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val conf = new SparkConf().setAppName("LinesCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("You.txt")
    val pairs = lines.map(x => (x,1))
    val results = pairs.reduceByKey(_ + _)
    results.foreach(println(_))
  }
}
