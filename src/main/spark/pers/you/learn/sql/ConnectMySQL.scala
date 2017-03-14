package pers.you.learn.sql

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by You on 2017/3/10.
  */
class ConnectMySQL {
  val conf = new SparkConf().setAppName("ConnectMySQL").setMaster("local")
  val sc = new SparkContext(conf)
  val sqlContext = new SQLContext(sc)
  def show(): Unit ={
          val url = "jdbc:mysql://192.168.136.135:3306/test"
          val table = "you"
          val reader = sqlContext.read.format("jdbc")
          reader.option("url", url)
          reader.option("dbtable", table)
          reader.option("driver", "com.mysql.jdbc.Driver")
          reader.option("user", "root")
          reader.option("password", "you")
          val df = reader.load()
          df.show()
  }

  def select(): Unit ={
          val dataframe_mysql = sqlContext.read.format("jdbc").option("url","jdbc:mysql://192.168.136.135" +
            ":3306/test").option("driver","com.mysql.jdbc.Driver").option("dbtable","you").option("user","root"
          ).option("password","you").load()

          dataframe_mysql.registerTempTable("names")
          dataframe_mysql.sqlContext.sql("select * from names").collect.foreach(println)
  }

  def oldCreateTable(): Unit = {
    val data = sc.parallelize(List((1, "Tomi"), (2, "Enn"), (3, "You"))).map(item => Row.apply(item._1, item._2))
    val schema = StructType(StructField("id", IntegerType) :: StructField("name", StringType) :: Nil)
    val df = sqlContext.createDataFrame(data, schema)
    val url = "jdbc:mysql://192.168.136.135:3306/test?user=root&password=you"
    df.createJDBCTable(url, "hello", false) //创建表并插入数据
  }

  def newCreateTable(): Unit = {
      val url = "jdbc:mysql://192.168.136.135/test"
      val prop = new java.util.Properties
      val data = sc.parallelize(List((1, "Tomi"), (2, "Enn"), (3, "You"))).map(item => Row.apply(item._1, item._2))
      val schema = StructType(StructField("id", IntegerType) :: StructField("name", StringType) :: Nil)
      val df = sqlContext.createDataFrame(data, schema)
      prop.setProperty("user","root")
      prop.setProperty("password","you")
      df.write.jdbc(url,"new",prop)
  }

  def oldInsertTable(): Unit = {
//    val data = sc.parallelize(List((4, "A"), (5, "B"), (6, "C"))).map(item => Row.apply(item._1, item._2))
    val data = sc.parallelize(List((4, "D")).map(item => Row.apply(item._1, item._2)))
    val schema = StructType(StructField("id", IntegerType) :: StructField("name", StringType) :: Nil)
    val df = sqlContext.createDataFrame(data, schema)
    val url = "jdbc:mysql://192.168.136.135:3306/test?user=root&password=you"
    df.insertIntoJDBC(url,"hello",false)
  }
}

object ConnectMySQL {
    def main(args: Array[String]): Unit = {
          val c = new ConnectMySQL()
//          c.show()
//          c.select()
//            c.oldCreateTable()
//            c.oldInsertTable()
//      c.newCreateTable()

//

    }
  }
