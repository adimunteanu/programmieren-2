package util

import scala.collection.mutable.ListBuffer

/**
 * Used by the tests to monitor the system state and sequence of messages.
 */
object GymMetrics {

    object Actions extends Enumeration {
        type Action = Value
        val StartTraining,
        PickUpAttempt,
        PickUpSuccess,
        PickUpFailure,
        PutDownAttempt,
        PutDownSuccess,
        PutDownFailure,
        DoneStretching,
        DoneExercising,
        AvailabilityNotification,
        PutDownFailureResponse,
        InvalidMessageType,
        InvalidActorType = Value
    }

    private val athletes: ListBuffer[(String, String)] = new ListBuffer
    private val weights: ListBuffer[(String, String)] = new ListBuffer
    private val actions: ListBuffer[(String, String, Actions.Action)] = new ListBuffer

    def registerAthlete(className: String, actorName: String): Unit = {
        this.synchronized {
            athletes.addOne((className, actorName))
        }
    }

    def registerWeight(className: String, actorName: String): Unit = {
        this.synchronized {
            weights.addOne((className, actorName))
        }
    }

    def registerMessage(senderName: String, recipientName: String, message: Any): Unit = {
        this.synchronized {
            message match {
                case StartTraining => actions.addOne((senderName, recipientName, Actions.StartTraining))

                case PickUpResponse(success) =>
                    if(success)
                        actions.addOne((senderName, recipientName, Actions.PickUpSuccess))
                    else
                        actions.addOne((senderName, recipientName, Actions.PickUpFailure))

                case AvailabilityNotification => actions.addOne((senderName, recipientName, Actions.AvailabilityNotification))

                case DoneStretching => actions.addOne((senderName, recipientName, Actions.DoneStretching))

                case DoneExercising => actions.addOne((senderName, recipientName, Actions.DoneExercising))

                case PutDownResponse(success) =>
                    if(success)
                        actions.addOne((senderName, recipientName, Actions.PutDownSuccess))
                    else
                        actions.addOne((senderName, recipientName, Actions.PutDownFailure))

                case PickUpRequest => actions.addOne((senderName, recipientName, Actions.PickUpAttempt))

                case PutDownRequest => actions.addOne((senderName, recipientName, Actions.PutDownAttempt))

                case ForgetMeForIHaveFailed => actions.addOne((senderName, recipientName, Actions.PutDownFailureResponse))

                case _ => actions.addOne((senderName, recipientName, Actions.InvalidMessageType))
            }
        }
    }

    def getAthletes: List[(String, String)] = {
        athletes.toList
    }

    def getWeights: List[(String, String)] = {
        weights.toList
    }

    def getActions: List[(String, String, Actions.Action)] = {
        actions.toList
    }

    def reset(): Unit = {
        athletes.clear()
        weights.clear()
        actions.clear()
    }
}
