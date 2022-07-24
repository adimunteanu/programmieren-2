import Tests.executeSetup
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import util.GymMetrics.Actions
import util.{AbstractAthlete, AbstractWeight, AvailabilityNotification, DeathWatch, DoneExercising, DoneStretching, Gym, GymMetrics, PickUpRequest, PickUpResponse, PutDownRequest, PutDownResponse, StartTraining}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, FiniteDuration, MILLISECONDS, SECONDS}

class Tests extends AnyFlatSpec with should.Matchers with BeforeAndAfter  {

    after {
        GymMetrics.reset()
    }

    ////////////////////////////
    // Message Handling Tests //
    ////////////////////////////

    "An Athlete" should "correctly handle StartTraining (1P)" in {
        val (system, _, _, athlete) = setupTestAthlete()
        athlete ! StartTraining
        Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.DoneStretching) shouldBe 1
    }

    it should "correctly handle PickUpResponse(true) by picking up the other weight (1P)" in {
        val (system, dummy, _, athlete) = setupTestAthlete()
        dummy ! SetupDummy(athlete)
        dummy ! "PickUpResponse(true)"
        Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.PickUpAttempt) shouldBe 1
    }

    it should "correctly handle PickUpResponse(false) by not trying again immediately (1P)" in {
        val (system, dummy, _, athlete) = setupTestAthlete()
        dummy ! SetupDummy(athlete)
        dummy ! "PickUpResponse(false)"
        try {
            Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        } catch {
            case _: Exception =>
                system.terminate()
                Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        }
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.PickUpAttempt) shouldBe 0
    }

    it should "correctly handle AvailabilityNotification by picking up the weight (1P)" in {
        val (system, dummy, _, athlete) = setupTestAthlete()
        dummy ! SetupDummy(athlete)
        dummy ! "AvailabilityNotification"
        Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.PickUpAttempt) shouldBe 1
    }

    it should "correctly handle DoneStretching by picking up the first weight (1P)" in {
        val (system, _, _, athlete) = setupTestAthlete()
        athlete ! DoneStretching
        Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.PickUpAttempt) shouldBe 1
    }

    it should "correctly handle DoneExercising by putting down its weights (1P)" in {
        val (system, _, _, athlete) = setupTestAthlete()
        athlete ! DoneExercising
        Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.PutDownAttempt) should be > 0
    }

    it should "correctly handle PutDownResponse(true) by starting the next cycle if no more weights are held (1P)" in {
        {
            val (system, dummy0, _, athlete) = setupTestAthlete(2, 2)
            dummy0 ! SetupDummy(athlete)
            dummy0 ! "PutDownResponse(true)"
            try {
                Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            } catch {
                case _: Exception =>
                    system.terminate()
                    Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            }
            val actions = GymMetrics.getActions
            actions.count(a => a._3 == Actions.DoneStretching) shouldBe 0
            GymMetrics.reset()
        }
        {
            val (system, dummy0, _, athlete) = setupTestAthlete(1, 2)
            dummy0 ! SetupDummy(athlete)
            dummy0 ! "PutDownResponse(true)"
            try {
                Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            } catch {
                case _: Exception =>
                    system.terminate()
                    Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            }
            val actions = GymMetrics.getActions
            println(actions)
            actions.count(a => a._3 == Actions.DoneStretching) shouldBe 1
        }
    }

    it should "correctly handle PutDownResponse(false) by asking the sender and its held weights to forget it and taking a poison pill (1P)" in {
        val (system, dummy, _, athlete) = setupTestAthlete(2)
        dummy ! SetupDummy(athlete)
        dummy ! "PutDownResponse(false)"
        Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
        val actions = GymMetrics.getActions
        actions.count(a => a._3 == Actions.PutDownFailureResponse) shouldBe 2
    }

    "A Weight" should "correctly handle PickUpRequest (1P)" in {
        {
            val (system, dummy, _, weight) = setupTestWeight()
            dummy ! SetupDummy(weight)
            dummy ! "PickUpRequest"
            Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            val actions = GymMetrics.getActions
            actions.count(a => a._3 == Actions.PickUpSuccess) shouldBe 1
            GymMetrics.reset()
        }
        {
            val (system, dummy0, dummy1, weight) = setupTestWeight()
            dummy0 ! SetupDummy(weight)
            dummy1 ! SetupDummy(weight)
            dummy0 ! "PickUpRequest"
            dummy1 ! "PickUpRequest"
            Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            val actions = GymMetrics.getActions
            actions.count(a => a._3 == Actions.PickUpSuccess) shouldBe 1
            actions.count(a => a._3 == Actions.PickUpFailure) shouldBe 1
            GymMetrics.reset()
        }
    }

    it should "correctly handle PutDownRequest (1P)" in {
        {
            val (system, dummy, _, weight) = setupTestWeight()
            dummy ! SetupDummy(weight)
            dummy ! "PickUpRequest"
            dummy ! "PutDownRequest"
            Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            val actions = GymMetrics.getActions
            actions.count(a => a._3 == Actions.PutDownSuccess) shouldBe 1
            actions.count(a => a._3 == Actions.PutDownFailure) shouldBe 0
            GymMetrics.reset()
        }
        {
            val (system, dummy, _, weight) = setupTestWeight()
            dummy ! SetupDummy(weight)
            dummy ! "PutDownRequest"
            Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            val actions = GymMetrics.getActions
            actions.count(a => a._3 == Actions.PutDownSuccess) shouldBe 0
            actions.count(a => a._3 == Actions.PutDownFailure) shouldBe 1
            GymMetrics.reset()
        }
        {
            val (system, dummy0, dummy1, weight) = setupTestWeight()
            dummy0 ! SetupDummy(weight)
            dummy1 ! SetupDummy(weight)
            dummy0 ! "PickUpRequest"
            dummy1 ! "PutDownRequest"
            Await.result(system.whenTerminated, Duration(1000, MILLISECONDS))
            val actions = GymMetrics.getActions
            actions.count(a => a._3 == Actions.PutDownSuccess) shouldBe 0
            actions.count(a => a._3 == Actions.PutDownFailure) shouldBe 1
        }
    }

    def setupTestAthlete(numHeld: Int = 0, cycles: Int = 1): (ActorSystem, ActorRef, ActorRef, ActorRef) = {
        val system = ActorSystem("GymSystem")
        val dummy0 = system.actorOf(Props(new DummyActor), "dummy0")
        val dummy1 = system.actorOf(Props(new DummyActor), "dummy1")
        val athlete = numHeld match {
            case 0 => system.actorOf(AbstractAthlete.props(0, cycles, dummy0, dummy1), "athlete")
            case 1 => system.actorOf(AbstractAthlete.props(0, cycles, dummy0, dummy1, ListBuffer(dummy0)), "athlete")
            case _ => system.actorOf(AbstractAthlete.props(0, cycles, dummy0, dummy1, ListBuffer(dummy0, dummy1)), "athlete")
        }
        (system, dummy0, dummy1, athlete)
    }

    def setupTestWeight(): (ActorSystem, ActorRef, ActorRef, ActorRef) = {
        val system = ActorSystem("GymSystem")
        val dummy0 = system.actorOf(Props(new DummyActor), "dummy0")
        val dummy1 = system.actorOf(Props(new DummyActor), "dummy1")
        val weight = system.actorOf(AbstractWeight.props, "weight")
        (system, dummy0, dummy1, weight)
    }

    private case class SetupDummy(a: ActorRef)

    private class DummyActor extends Actor {
        implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

        var testSubject: ActorRef = _
        override def receive: Receive = {
            case SetupDummy(t) => testSubject = t

            case "StartTraining" => testSubject ! StartTraining
            case "PickUpResponse(true)" => testSubject ! PickUpResponse(true)
            case "PickUpResponse(false)" => testSubject ! PickUpResponse(false)
            case "AvailabilityNotification" => testSubject ! AvailabilityNotification
            case "DoneStretching" => testSubject ! DoneStretching
            case "DoneExercising" => testSubject ! DoneExercising
            case "PutDownResponse(true)" => testSubject ! PutDownResponse(true)
            case "PutDownResponse(false)" => testSubject ! PutDownResponse(false)

            case "PickUpRequest" => testSubject ! PickUpRequest
            case "PutDownRequest" => testSubject ! PutDownRequest

            case "TimeToDie" => context.system.terminate()

            case msg =>
                println(msg.getClass.getSimpleName)
                GymMetrics.registerMessage(sender().path.name, self.path.name, msg)
                delayedTermination()
        }

        def delayedTermination(): Unit = {
            val delay = FiniteDuration.apply((Math.random() * 500).toLong, MILLISECONDS)
            context.system.scheduler.scheduleOnce(delay, self, "TimeToDie")
        }
    }

    ////////////////////
    // Sequence Tests //
    ////////////////////

    "A single Athlete" should "have the correct sequence and terminate after 1 cycle (2P)" in {
        executeSetup(1, 1, 5)
        val actions = GymMetrics.getActions
        for(action <- actions) {
            println(action)
        }

        actions.size shouldBe 11

        val expectedAthleteSequence = List(Actions.StartTraining, Actions.DoneStretching, Actions.PickUpSuccess, Actions.PickUpSuccess, Actions.DoneExercising, Actions.PutDownSuccess, Actions.PutDownSuccess)
        val expectedWeightSequence = List(Actions.DoneStretching, Actions.PickUpAttempt, Actions.PickUpAttempt, Actions.DoneExercising, Actions.PutDownAttempt, Actions.PutDownAttempt)

        actions.filter(a => a._2 == "athlete0").map(a => a._3) shouldBe expectedAthleteSequence
        actions.filter(a => a._1 == "athlete0").map(a => a._3) shouldBe expectedWeightSequence
    }

    it should "have the correct sequence and terminate after 3 cycles (2P)" in {
        executeSetup(1, 3, 10)
        val actions = GymMetrics.getActions
        for(action <- actions) {
            println(action)
        }

        actions.size shouldBe 31

        val expectedAthleteSingleSequence = List(Actions.DoneStretching, Actions.PickUpSuccess, Actions.PickUpSuccess, Actions.DoneExercising, Actions.PutDownSuccess, Actions.PutDownSuccess)
        val expectedWeightSingleSequence = List(Actions.DoneStretching, Actions.PickUpAttempt, Actions.PickUpAttempt, Actions.DoneExercising, Actions.PutDownAttempt, Actions.PutDownAttempt)

        val expectedAthleteSequence = List.concat(List(Actions.StartTraining), expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence)
        val expectedWeightSequence = List.concat(expectedWeightSingleSequence, expectedWeightSingleSequence, expectedWeightSingleSequence)

        actions.filter(a => a._2 == "athlete0").map(a => a._3) shouldBe expectedAthleteSequence
        actions.filter(a => a._1 == "athlete0").map(a => a._3) shouldBe expectedWeightSequence
    }

    "Many Athletes" should "have the correct sequence and terminate after 1 cycle (3P)" in {
        executeSetup(20, 1, 15)
        val actions = GymMetrics.getActions
        val expectedAthleteSequence = List(Actions.StartTraining, Actions.DoneStretching, Actions.PickUpSuccess, Actions.PickUpSuccess, Actions.DoneExercising, Actions.PutDownSuccess, Actions.PutDownSuccess)
        testMultipleAthletes(20, 220, 340, 40, 80, actions, expectedAthleteSequence)
        // 10 actions per athlete per cycle, plus 1 starting action per athlete
        // per cycle, an actor may fail no more than twice
        // each failure may be recovered by exactly one additional attempt, giving us a max of 4 attempts per athlete per cycle
    }

    it should "have the correct sequence and terminate after 10 cycles (3P)" in {
        executeSetup(20, 10, 30)
        val actions = GymMetrics.getActions
        val expectedAthleteSingleSequence = List(Actions.DoneStretching, Actions.PickUpSuccess, Actions.PickUpSuccess, Actions.DoneExercising, Actions.PutDownSuccess, Actions.PutDownSuccess)
        val expectedAthleteSequence = List.concat(List(Actions.StartTraining),
            expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence,
            expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence, expectedAthleteSingleSequence) // this hurts my eyes
        testMultipleAthletes(20, 2020, 3220, 400, 800, actions, expectedAthleteSequence)
    }

    def testMultipleAthletes(count: Int, minActions: Int, maxActions: Int, maxFailures: Int, maxAttempts: Int, actions: List[(String, String, Actions.Action)], expectedAthleteSequence: List[Actions.Action]): Unit = {
        actions.size should be >= minActions
        actions.size should be <= maxActions

        for(i <- 0 until count) {
            val athleteRcv = actions.filter(a => a._2 == "athlete" + i).map(a => a._3)
            val athleteSnd = actions.filter(a => a._1 == "athlete" + i).map(a => a._3)

            athleteRcv.count(a => a == Actions.PickUpFailure) should be <= maxFailures
            athleteSnd.count(a => a == Actions.PickUpAttempt) should be <= maxAttempts

            athleteRcv.count(a => a == Actions.PickUpFailure) shouldBe athleteRcv.count(a => a == Actions.AvailabilityNotification)
            actions.filter(a => a._2 == "athlete" + i).map(a => a._3).filter(a => a != Actions.PickUpFailure && a != Actions.AvailabilityNotification) shouldBe expectedAthleteSequence
        }
    }
}

object Tests {
    def executeSetup(numAthletes: Int, cycles: Int, timeout: Int): Unit = {
        val system = ActorSystem("GymSystem")
        val athletes = Gym.setup(system, numAthletes, cycles)

        DeathWatch.start(system, athletes)
        Gym.startAthletes(athletes)
        Await.result(system.whenTerminated, Duration(timeout, SECONDS))
    }
}
