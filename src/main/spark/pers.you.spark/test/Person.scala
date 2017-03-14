package pers.you.spark.test

import java.io.IOException

/**
  * Created by Administrator on 2017/3/6.
  */
class Person(val id: Long,var name: String) {

  var gender: String = _
  println(gender)

  //如果定义辅助构造器，辅助构造器第一行，必须先调用主构造器
  def this(id: Long, name: String ,gender: String){
        //调用主构造器
        this(id,name)
        this.gender = gender
  }
  def this(){
    this(0L,null)
  }
  try {
    println("读取文件")
    gender.toString
  } catch {
    case e: NullPointerException => println("打印异常Exception: " + e)
    case e: IOException => println("打印异常Exception：" + e)
  }finally {
    println("执行finally部分")
  }

  def sayHi(): Unit ={
    println("hi ~")
  }
}

object Person{
  def main(args: Array[String]): Unit ={
//    val p = new Person(10L,"tomcat")
//    val p = new Person(18L,"jerry","male")
      val p = new Person

    p.sayHi()
    println(p.id)
    println(p.name)
    p.name="abc"
    println(p.name)
  }
}
