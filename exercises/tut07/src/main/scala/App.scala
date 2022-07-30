import java.util.Date

object App extends App {
  // 7.2.1 - 7.2.2
  /*
  var e = new Exam("PROG2 - 1. Termin", new Date())
  println(e.title, e.when)

   var s = new Student("Gideon", 401259)
   var s1 = Student("Anselma", 476749)
   var s2 = Student("Inessa", 307055)
   var s3 = Student("Sisile", 389343)

   e.register(s)
   e.register(s1)
   e.register(s2)
   e.register(s3)

   for (r <- e.results()) {
     println(r)
   }

   for (a <- Student.allStudents) {
     println(a.name)
   }


   // 7.2.3
   e.register(s)
   e.register(s1)
   e.register(s2)
   e.register(s3)

   e.deregister(s2)

   for (a <- e.registrations) {
     println(a.name)
   }
   println()
   e.enterResult(s, 4.0)
   e.enterResult(s1, 5.0)
   e.enterResult(s2, 1.0) // expected output: Student not registered
   e.enterResult(s3, 2.0)

   println()
   println(e.getResultOf(s3))
   println()
   e.publishResults()
   // expected output:
   // (389343,2.0)
   // (476749,5.0)
   // (401259,4.0)

  // 7.2.4
  var e = new Exam("PROG2 - 1. Termin", new Date(), 6)
  var e1 = new Exam("Prog1 - 1.Termin", new Date(), 6)
  var winf = new CourseOfStudy("W-Inf", Array(e,e1))

  var s = new Student("Gideon", 401259, winf)
  var s1 = new Student("Anselma", 476749, winf)
  var s2 = new Student("Inessa", 307055, winf)
  var s3 = new Student("Sisile", 389343, winf)
  e.register(s)
  e1.register(s)

  e.enterResult(s, 4.0)
  e1.enterResult(s, 3.3)
  println(s.gradeOverview())
  // expected output:
  // Gideon
  // PROG2 - 1. Termin: 4.0
  println(s.name + " - " + s.courseProgress() + " / 180 ECTS")
  // expected output: Gideon - 12 / 180 ECTS
  */
}
