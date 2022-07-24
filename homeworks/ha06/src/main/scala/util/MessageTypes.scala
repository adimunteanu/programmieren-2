package util

// sent by Gym.startAthletes() to each athlete
case class StartTraining(cycles: Int)

// sent by AbstractAthlete to itself to signal the end of a workout phase
case object DoneStretching
case object DoneExercising

// sent by athletes to weights
case object PickUpRequest
case object PutDownRequest
case object ForgetMeForIHaveFailed

// sent by weights to athletes
case object AvailabilityNotification
case class PickUpResponse(success: Boolean)
case class PutDownResponse(success: Boolean)
