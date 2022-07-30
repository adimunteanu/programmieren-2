val f1: (Int => Int) = (x: Int) => x + 1;
val f2: (Int => (Int => Int)) = (x: Int) => {
  val addByX: (Int => Int) = (y: Int) => x + y;
  addByX
}

f1(5)
f2(5)(7)


val f3: (Int, (Int => Int), (Int => String)) => String =
  (x: Int, f: (Int => Int), s: (Int => String)) => {
    s(f(x))
  }

f3(5, f1, Integer.toString)