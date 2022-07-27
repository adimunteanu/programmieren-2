5 + 10
"5" + "10"
Math.E + 5L
5.shortValue

def divide(x: Double, y: Double) : Any = {
  if (y == 0) {
    "Division mit 0 nicht möglich"
  } else {
    x / y
  }
}

divide(10,0)

//divide(10, divide(10,5))
def toType(i: Any): Double =
  i.toString.toDoubleOption match {
    case Some(i) => i.asInstanceOf[Double]
    case None => 0.asInstanceOf[Double]
  }

divide(10, toType(divide(10,5)))