package util

import akka.actor.{ActorRef, ActorSystem}

import scala.collection.mutable.ListBuffer

object Gym {

    /**
     * Sets up a gym by populating the given ActorSystem with Athletes and Weights
     * @param system ActorSystem to be populated.
     * @param numAthletes Number of Athlete actors to be generated.
     * @param cycles Number of training cycles each Athlete should perform.
     * @return List of ActorRefs referencing all Athlete actors in the system.
     */
    def setup(system: ActorSystem, numAthletes: Int, cycles: Int): List[ActorRef] = {

        val correctedNumAthletes: Int = if (numAthletes < 1) 1 else numAthletes
        val correctedCycles: Int = if (cycles < 1) 1 else cycles
        val correctedNumWeights: Int = if (numAthletes < 2) 2 else numAthletes

        val weightList = new ListBuffer[ActorRef]()
        for (i <- 0 until correctedNumWeights) {
            weightList.addOne(system.actorOf(AbstractWeight.props, "weight" + i))
        }

        val athleteList = new ListBuffer[ActorRef]
        for (i <- 0 until correctedNumAthletes) {
            val athlete = system.actorOf(AbstractAthlete.props(i, correctedCycles, weightList(i), weightList((i + 1) % correctedNumWeights)), "athlete" + i)
            athleteList.addOne(athlete)
        }

        athleteList.toList
    }

    /**
     * Triggers the training sequence of the given actors by sending them a StartTraining message.
     * @param athleteList List of Athlete actors to be started.
     */
    def startAthletes(athleteList: List[ActorRef]): Unit = {
        for (athlete <- athleteList) {
            athlete ! StartTraining
        }
    }
}
