package util

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import impl.Athlete

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.{FiniteDuration, MILLISECONDS}

abstract class AbstractAthlete(id: Int, cycles: Int, leftWeight: ActorRef, rightWeight: ActorRef, heldWeights: ListBuffer[ActorRef]) extends Actor with ActorLogging {
    implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

    GymMetrics.registerAthlete(getClass.getSimpleName, self.path.name)

    /**
     * To be implemented by you in Athlete!
     * @return Partial function defining how incoming messages are handled.
     */
    def receiveImpl: Receive

    /**
     * Handles incoming messages by calling receiveImpl.
     * @return Partial function implemented in receiveImpl.
     */
    override def receive: Receive = {
        case msg =>
            GymMetrics.registerMessage(sender().path.name, self.path.name, msg)
            receiveImpl(msg)
    }

    /**
     * Simulates the stretching phase of the workout by scheduling a DoneStretching message to be send to this athlete after a random duration.
     */
    def stretch(): Unit = {
        val delay = FiniteDuration.apply((Math.random() * 100).toLong, MILLISECONDS)
        context.system.scheduler.scheduleOnce(delay, self, DoneStretching)
    }

    /**
     * Simulates the exercising phase of the workout by scheduling a DoneExercising message to be send to this athlete after a random duration.
     */
    def exercise(): Unit = {
        val delay = FiniteDuration.apply((Math.random() * 100).toLong, MILLISECONDS)
        context.system.scheduler.scheduleOnce(delay, self, DoneExercising)
    }
}

object AbstractAthlete {
    def props(id: Int, cycles: Int, leftWeight: ActorRef, rightWeight: ActorRef, heldWeights: ListBuffer[ActorRef] = new ListBuffer[ActorRef]) : Props = {
        Props(new Athlete(id, cycles, leftWeight, rightWeight, heldWeights))
    }
}
