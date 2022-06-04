package impl

import scala.io.Source

object ExamRegistrations {

  /**
   * Aufgabe 3.2.1: Lesen Sie die Anmeldungen aus der gegebenen Datei (registrations.csv) ein.
   *
   * @param fileName Name of the resource file containing the list of registrations.
   * @return List of tuples where each entry represents one line of the given CSV file.
   */
  def readRegistrationsFromFile(fileName: String): List[(String, Int, Int)] = {
    val lines = Source.fromResource(fileName).getLines()

    (for (line <- lines) yield (line.split(",")(0), line.split(",")(1).toInt, line.split(",")(2).toInt)).toList
  }

  /**
   * Aufgabe 3.2.2: Lesen Sie die Ergebnisse aus der gegebenen Datei (results.csv) ein.
   *
   * @param fileName Name of the resource file containing the list of results.
   * @return List of tuples where each entry represents one line of the given CSV file.
   */
  def readResultsFromFile(fileName: String): List[(Int, Double)] = {
    val lines = Source.fromResource(fileName).getLines()

    (for (line <- lines) yield (line.split(",")(0).toInt, line.split(",")(1).toDouble)).toList
  }

  /**
   * Aufgabe 3.2.3: Ermitteln Sie den Namen der Student*in mit der besten Note.
   *
   * @param registrations List of tuples representing registrations
   * @param results       List of tuples representing results
   * @return Name of the best student (i.e. the student with the best grade)
   */
  def findBestStudent(registrations: List[(String, Int, Int)], results: List[(Int, Double)]): String = {
    registrations.find(_._2 == results.minBy(_._2)._1).get._1
  }

  /**
   * Aufgabe 3.2.4: Ermitteln Sie alle Student*innen, die im 2. Versuch eine 3.0 oder besser geschrieben haben.
   *
   * @param registrations List of tuples representing registrations
   * @param results       List of tuples representing results
   * @return List of matriculation numbers of relevant students
   */
  def findAdequateStudents(registrations: List[(String, Int, Int)], results: List[(Int, Double)]): List[Int] = {
    for (registration <- registrations if registration._3 == 2; result <- results if result._2 <= 3 && registration._2 == result._1)
      yield result._1
  }

  /**
   * Aufgabe 3.2.5: Ermitteln Sie die Matrikelnummern aller Studenten, die sich mehrfach registriert haben,
   * d.h. die mehrmals in der Liste der Registrierungen auftauchen.
   *
   * @param registrations List of tuples representing registrations
   * @return List of matriculation numbers of relevant students
   */
  def findDuplicateRegistrations(registrations: List[(String, Int, Int)]): Array[Int] = {
    registrations.map(p => p._2).diff(registrations.map(p => p._2).distinct).distinct.sortWith(_ < _).toArray
  }

  /**
   * Aufgabe 3.2.6: Berechnen Sie die Durchschnittsnote der Student*innen, die die Prüfung zum zweiten Mal schreiben.
   *
   * @param registrations List of tuples representing registrations
   * @param results       Average grade of relevant students
   * @return
   */
  def findAvgGradeOfRepeatExams(registrations: List[(String, Int, Int)], results: List[(Int, Double)]): Double = {
    var studentGrades: List[Double] = List()

    for (registration <- registrations if registration._3 == 2; result <- results if registration._2 == result._1) {
      studentGrades = studentGrades :+ result._2
    }

    studentGrades.sum / studentGrades.length
  }
}
