object Functions {
  // A)
  def multiply: (Int, Int) => Int = (a, b) => {
    a * b
  }

  // B)
  def divide: (Double, Double) => Double = (a, b) => if (b == 0) 0 else a / b

  // C)
  def printSum: (Int, Int) => Unit = (x, y) => {
    println(x + " + " + y + " = " + (x + y))
  }

  def main(args: Array[String]): Unit = {
    println("multiply 2 and 3")
    println(multiply(2, 3))

    println("divide 10 by 5")
    println(divide(10, 5))

    println("printsum of 3 and 4")
    printSum(3, 4)
  }
}
