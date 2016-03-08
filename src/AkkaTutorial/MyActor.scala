package AkkaTutorial

import akka.actor.{Actor, Props}
import akka.actor.Actor.Receive
import akka.event.Logging
import akka.actor.ActorSystem

/**
  * Created by alifar on 2/6/16.
  */

class MyActor(num: Int) extends Actor{
  var log = Logging(context.system, this)
  override def receive = {
    case x: Int => sender ! "woot!"
//    case x: Int => log.info("myActor message")
  }
}

object MyActor{
  def props(num: Int): Props = Props(new MyActor(num))
}


class AnotherActor extends Actor{
  var log = Logging(context.system, this)
//  def test:Int = {46}
  override def receive = {
    case "test" =>
      log.info("test message")
      val myActor = context.actorOf(MyActor.props(42), "demo")
      myActor ! 4
    case _ => log.info("new message")
  }


}

object Main extends App {
  val system = ActorSystem("System")
  val anotherActor = system.actorOf(Props[AnotherActor], name="anotherActor")
  anotherActor ! "test"
//  val myActor = system.actorOf(Props(new MyActor(2)), name="myActor")
//  myActor ! 3
}