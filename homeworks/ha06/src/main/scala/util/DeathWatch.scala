package util

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props, Terminated}

/**
 * Watches all Athlete actors and registers their termination.
 * Once all Athletes are terminated, DeathWatch terminates the whole ActorSystem.
 * @param athletes List of Athlete actors to be monitored.
 */
class DeathWatch(athletes: List[ActorRef]) extends Actor with ActorLogging {

    var actorCount: Int = athletes.size
    var terminatedActors = 0

    for(athlete <- athletes) {
        context.watch(athlete)
    }

    override def receive: Receive = {
        case Terminated(_) =>
            terminatedActors += 1
            if(terminatedActors == actorCount) {
                context.system.terminate()
            }
    }
}

object DeathWatch {
    def start(system: ActorSystem, athletes: List[ActorRef]): Unit = {
        system.actorOf(Props(new DeathWatch(athletes)), name = "DeathWatch")
    }
}
