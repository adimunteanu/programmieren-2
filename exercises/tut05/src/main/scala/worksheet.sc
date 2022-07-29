/* TODO: implement anonymous functions */

val add = (x: Int, y: Int) => x + y

add(2, 3)

val divide = (x: Double, y: Double) => if (y == 0) 0.0 else x / y
divide(4, 2)
divide(5, 0)
divide(5, 2)

val decrement = (x: Int) => if (x == 0) 0 else x - 1
decrement(2)
decrement(0)

val checkPalindrome: String => Boolean = (s) => s == s.reverse

checkPalindrome("abba");
checkPalindrome("abac")