package util

import akka.actor.ActorSystem

object Main extends App {
    // feel free to modify these values to test your system
    val numAthletes = 20
    val cycles = 30

    // but do not modify anything below this comment!
    val system = ActorSystem("GymSystem")
    val athletes = Gym.setup(system, numAthletes, cycles)

    DeathWatch.start(system, athletes)
    Gym.startAthletes(athletes)
}
