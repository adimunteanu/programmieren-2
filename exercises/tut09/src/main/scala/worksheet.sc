abstract class FamilyMember {
  val name: String
  val birthdate: String
  val placeOfBirth: String
}

trait Parents extends FamilyMember {
  val children: Array[FamilyMember] = Array[FamilyMember]()

  def printChildren(): Unit = {
    for (c <- children) {
      print(c.name)
    }
  }

  val isParent: Boolean = true
}

trait GrandParents extends Parents {
  def printGrandChildren(): Unit = {
    for(c <- children) {
      if (c.isInstanceOf[Parents]) {
        for(gc <- c.asInstanceOf[Parents].children) {
          print(gc.name)
        }
      }
    }
  }
}

class Mother(val name: String, val birthdate: String, val placeOfBirth: String) extends Parents with GrandParents {
}

class Father(val name: String, val birthdate: String, val placeOfBirth: String) extends Parents {
}

class Sister(val name: String, val birthdate: String, val placeOfBirth: String) extends FamilyMember with Parents {

}