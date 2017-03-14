package pers.you.spark.test

/**
  * Created by Administrator on 2017/3/6.
  */

//edu360 包访问权限
//如果在构造器中的变量，既没有用val修饰又没有用var修饰，相当于private[this]
class People  private[you] (val id: Long,var name: String,pet: String,var age: Int=18){
    def getPet(): String ={
      pet
    }
}

object People{
  def main(args: Array[String]): Unit ={
    val p1 = new People(0L,"asf","tom",20)
    println(p1.age)
    println(p1.getPet())
  }
}
