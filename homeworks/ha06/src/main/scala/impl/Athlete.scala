package impl

import akka.actor.{ActorRef, PoisonPill}
import util.{AbstractAthlete, AvailabilityNotification, DoneExercising, DoneStretching, ForgetMeForIHaveFailed, PickUpRequest, PickUpResponse, PutDownRequest, PutDownResponse, StartTraining}

import scala.collection.mutable.ListBuffer

class Athlete(id: Int, var cycles: Int, leftWeight: ActorRef, rightWeight: ActorRef, heldWeights: ListBuffer[ActorRef])
  extends AbstractAthlete(id: Int, cycles: Int, leftWeight: ActorRef, rightWeight: ActorRef, heldWeights: ListBuffer[ActorRef]) {

  ////////////////////////////////////////////////////////////////////////////
  //      DO NOT ADD ANY FIELDS, PARAMETERS OR METHODS TO THIS CLASS!       //
  //               DO NOT MODIFY THE CONSTRUCTOR SIGNATURE!                 //
  // ONLY IMPLEMENT receiceImpl, DO NOT MODIFY THIS CLASS IN ANY OTHER WAY! //
  ////////////////////////////////////////////////////////////////////////////

  override def receiveImpl: Receive = {

    case StartTraining => stretch()
    case DoneStretching =>
      leftWeight ! PickUpRequest
    case PickUpResponse(success) =>
      if (success) {
        heldWeights.addOne(sender)

        if (heldWeights.length == 1) {
          if (leftWeight == sender) {
            rightWeight ! PickUpRequest
          } else {
            leftWeight ! PickUpRequest
          }
        }

        if (heldWeights.length == 2) {
          exercise()
        }
      }
    case AvailabilityNotification =>
      sender ! PickUpRequest
    case DoneExercising =>
      leftWeight ! PutDownRequest
      rightWeight ! PutDownRequest
    case PutDownResponse(success) =>
      if (success) {
        val index: Int = heldWeights.indexOf(sender)
        heldWeights.remove(index)

        if (heldWeights.isEmpty) {
          cycles = cycles - 1

          if (cycles == 0) {
            self ! PoisonPill
          } else {
            stretch()
          }
        }
      } else {
        heldWeights.clear()
        leftWeight ! ForgetMeForIHaveFailed
        rightWeight ! ForgetMeForIHaveFailed
        self ! PoisonPill
      }
  }
}
