package pers.you.spark.test

/**
  * Created by You on 2017/3/2.
  */
class Student {
  val id = 100L
  var name = "tom"

 //只能在类中使用
  private[this] var pet = "旺财"
  //只能在类的内部和伴生对象中使用
  private var age: Int = 10
}

//静态对象，单例对象，如果这个对象与当前的类在同一个文件，并且与类的名字一致，叫伴生对象。
object Student {
  val CONSTANT = "123"
  def main(args: Array[String]) {
    val s = new Student
    println (s.id)
    println (s.name)
    s.name = "jerry"
    println (s.name)

    println(s.age)
    s.age=11
    println(s.age)
  }
}