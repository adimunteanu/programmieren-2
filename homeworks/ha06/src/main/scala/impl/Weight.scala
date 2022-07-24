package impl

import akka.actor.ActorRef
import util.{AbstractWeight, AvailabilityNotification, ForgetMeForIHaveFailed, PickUpRequest, PickUpResponse, PutDownRequest, PutDownResponse}

import scala.collection.mutable.ListBuffer


class Weight extends AbstractWeight {
  var isHeld: Boolean = false
  var holdingAthlete: String = ""
  var waitingAthlete = ListBuffer.empty[ActorRef]

  /////////////////////////////////////////////////////////////////////////////////
  // Feel free to add fields and methods for state management to this class, but //
  //                  DO NOT MODIFY THE CONSTRUCTOR SIGNATURE!                   //
  /////////////////////////////////////////////////////////////////////////////////

  override def receiveImpl: Receive = {
    case PickUpRequest =>
      if (!isHeld) {
        holdingAthlete = sender.path.name
        isHeld = true
        sender ! PickUpResponse(true)
      } else {
        waitingAthlete.addOne(sender)
        sender ! PickUpResponse(false)
      }
    case PutDownRequest =>
      if (sender.path.name == holdingAthlete) {
        isHeld = false
        sender ! PutDownResponse(true)
        if (!waitingAthlete.isEmpty) {
          waitingAthlete.head ! AvailabilityNotification
          waitingAthlete.remove(0)
        }
      } else {
        sender ! PutDownResponse(false)
      }
    case ForgetMeForIHaveFailed =>
      isHeld = false
      holdingAthlete = ""

      if (!waitingAthlete.isEmpty) {
        waitingAthlete.head ! AvailabilityNotification
        waitingAthlete.remove(0)
      }
  }
}
