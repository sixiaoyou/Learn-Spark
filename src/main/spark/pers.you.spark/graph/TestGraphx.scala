package pers.you.spark.graph

import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/2/23.
  */
class TestGraphx {

}
object TestGraphx {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("testGraphx").setMaster("local")
    val sc = new SparkContext(conf)
    val users = sc.parallelize(Array(
      (1L,("I","son of family")),
      (2L,("dad","dad of family")),
      (3L,("mum","mum of family")),
      (4L,("daughter-in-law","daughter-in-law of family"))
    ))
    val relations =sc.parallelize(Array(
      Edge(1L,2L,"son"),
      Edge(1L,3L,"son"),
      Edge(1L,4L,"marriage"),
      Edge(2L,3L,"marriage"),
      Edge(4L,2L,"daughter-in-law"),
      Edge(4L,3L,"daughter-in-law")
    ))
    val default=("I","son of family")
    val graph:Graph[(String,String),String]=Graph(users,relations,default)
    graph.vertices.filter{case(id,(role,comment))=>comment=="son of family"}.collect().foreach(println(_))
    graph.triplets.map(node=>{node.srcAttr._1 + " is the " + node.attr + " of " +node.dstAttr._1}).collect().foreach(println(_))

  }
}
