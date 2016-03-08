package AkkaTutorial

import akka.actor.Actor
import akka.actor.ActorLogging
/**
  * Created by alifar on 2/7/16.
  */

case object QuoteRequest

class TeacherActor extends Actor with ActorLogging{
  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try"
  )
  override def receive = {
    case QuoteRequest =>
      import util.Random

      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))

      sender() ! quoteResponse
  }
}
