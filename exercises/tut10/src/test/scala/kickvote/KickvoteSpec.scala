package kickvote

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.util.matching.Regex

class KickvoteSpec extends AnyFlatSpec with should.Matchers with GermanyVsMexico {
  import kickvote.Kickvote._

  def mkGerVsMex: FootballMatch = {
    new FootballMatch(
      Team("Germany", germanyPlayers),
      Team("Mexico", mexicoPlayers)
    )
  }

  """The Fixtures""" should """contain the right amount of players""" in {
    germanyPlayers should have size 11
    mexicoPlayers should have size 11
  }

  """Kickvote""" should """compute the right final result""" in {
    val gerVsMex = mkGerVsMex
    germanyVsMexicoEvents.foreach(gerVsMex.handleEvent)
    gerVsMex.score1 shouldBe 0
    gerVsMex.score2 shouldBe 1
  }

  it should """implement toString""" in {
    val gerVsMex = mkGerVsMex
    val outPattern: Regex = """(\w+)\s+(\d+)\s+\:\s+(\d+)\s+(\w+)""".r

    germanyVsMexicoEvents.foreach(gerVsMex.handleEvent)
    gerVsMex.toString shouldEqual "Germany 0 : 1 Mexico"
  }

  it should """compute isRunning""" in {
    val gerVsMex = mkGerVsMex
    gerVsMex.isRunning shouldBe false
    val (firstHalfEvents, secondHalfEvents) = germanyVsMexicoEvents.span {
      case SecondHalf => false; case _ => true
    }
    val (kickoff :: Nil, restOfFirstHalf) = firstHalfEvents.splitAt(1)
    gerVsMex.handleEvent(kickoff)
    gerVsMex.isRunning shouldBe true
    for(event <- restOfFirstHalf) gerVsMex.handleEvent(event)
    gerVsMex.isRunning shouldBe false
    val (secondHalfStart :: Nil, restOfSecondHalf) = secondHalfEvents.splitAt(1)
    gerVsMex.handleEvent(secondHalfStart)
    gerVsMex.isRunning shouldBe true
    for(event <- restOfSecondHalf) gerVsMex.handleEvent(event)
    gerVsMex.isRunning shouldBe false
  }

  it should "compute the total running time" in {
    val gerVsMex = mkGerVsMex
    germanyVsMexicoEvents.foreach(gerVsMex.handleEvent)
    gerVsMex.totalTime shouldBe 95
  }

  it should "unapply properly" in {
    val gerVsMex = FootballMatch(
      Team("Germany", germanyPlayers),
      Team("Mexico", mexicoPlayers)
    )
    for(event <- germanyVsMexicoEvents) gerVsMex.handleEvent(event)
    val FootballMatch(team1, score1, team2, score2) = gerVsMex
    team1 shouldBe "Germany"
    score1 shouldBe 0
    team2 shouldBe "Mexico"
    score2 shouldBe 1
  }

  it should "evaluate mexico victories properly" in {
    import FootballMatch.didMexicoWin
    didMexicoWin(("something", 0, "Mexico", 1)) shouldBe true
    didMexicoWin(("Mexico", 1, "else", 0)) shouldBe true
    didMexicoWin(("Mexico", 0, "else", 1)) shouldBe false
    didMexicoWin(("Something", 2, "else", 1)) shouldBe false
  }
}
