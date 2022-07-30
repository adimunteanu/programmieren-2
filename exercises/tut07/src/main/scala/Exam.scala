import java.util.Date

class Exam(val title: String, val datum: Date) {
  var registrations: Array[Student] = Array.empty[Student]

  def when(): String = datum.toString

  def register(s: Student): Unit = {
    registrations = s +: registrations
  }

  //  def results: () => Array[(Int, Double)] = () => {
  //    var array= Array[(Int, Double)]()
  //    for (s <- registrations) {
  //      array = (s.id, 4.0d) +: array;
  //    }
  //    array;
  //  }

  var results: Array[(Int, Double)] = Array[(Int, Double)]()

  def indexOfRegisteredStudent(s: Student): Int = {
    var i = -1
    for ((student, index) <- registrations.zipWithIndex) {
      if (s.id == student.id) {
        i = index
      }
    }
    i
  }

  def indexOfStudentResult(s: Student): Int = {
    var i = -1
    for ((result, index) <- results.zipWithIndex) {
      if (s.id == result._1) {
        i = index
      }
    }
    i
  }

  def deregister(s: Student): Unit = {
    var index = indexOfRegisteredStudent(s);
    if (index > 0) {
      registrations = Array.concat(registrations.slice(0, index), registrations.slice(index + 1, registrations.length))
    }
  }

  def enterResult(s: Student, grade: Double): Unit = {
    var indexRegister = indexOfRegisteredStudent(s)
    var indexResult = indexOfStudentResult(s)

    if (indexRegister >= 0) {
      if (indexResult < 0) {
        results = (s.id, grade) +: results
      } else {
        results(indexResult) = (s.id, grade)
      }
    } else {
      println("Wurde nicht registriert");
    }
  }

  def getResultsOf(s: Student): (String, Double) = {
    var index = indexOfRegisteredStudent(s)

    if (index >= 0) {
      (title, results(index)._2)
    } else {
      (title, 0.0)
    }
  }

  def publishResults(): Unit = {
    for (r <- results) {
      println(r)
    }
  }
}
