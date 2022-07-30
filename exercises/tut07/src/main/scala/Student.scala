import scala.+:

class Student private(val name: String, val id: Int) {

}

object Student {
  var allStudents: Array[Student] = Array.empty[Student]
  private var previousMatrikelnummer = 0
  private def generateNewMatrikelnummer() = {
    previousMatrikelnummer += previousMatrikelnummer
    previousMatrikelnummer
  }

  def apply(name: String) = {
    val student = new Student(name, generateNewMatrikelnummer())
    allStudents = student +: allStudents
    student
  }
}
