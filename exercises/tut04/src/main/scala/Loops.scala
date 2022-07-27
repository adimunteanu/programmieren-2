object Loops {
  def main(args: Array[String]): Unit = {
    // A)
    println(">>> A <<<")
    for (i <- 0 until 10) {
      println(i)
    }
    // B)
    println(">>> B <<<")
    for (i <- 0 until 10 by 2) {
      println(i)
    }
    // C)
    println(">>> C <<<")
    for (i <- 0 until 10; j <- 0 until 10) {
      println(i, j)
    }
  }
}
