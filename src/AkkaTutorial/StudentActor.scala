package AkkaTutorial

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorLogging
import akka.actor.Props

/**
  * Created by alifar on 2/7/16.
  */

case object InitSignal
case class QuoteResponse(quote: String)

object StudentActor{
  def props(teacherRef: ActorRef): Props = Props(new StudentActor(teacherRef))
}

class StudentActor(teacherRef: ActorRef) extends Actor with ActorLogging{
  override def receive = {
    case InitSignal =>
      teacherRef ! QuoteRequest
    case QuoteResponse(quoteString) =>
      log.info("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
  }
}
