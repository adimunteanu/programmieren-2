package util

import akka.actor.{Actor, ActorLogging, Props}
import impl.Weight

abstract class AbstractWeight extends Actor with ActorLogging {

    GymMetrics.registerWeight(getClass.getSimpleName, self.path.name)

    /**
     * To be implemented by you in Weight!
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
}

object AbstractWeight {
    def props : Props = {
        Props[Weight]()
    }
}
