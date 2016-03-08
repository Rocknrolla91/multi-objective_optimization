package MultiObjectiveOptimizaton

import akka.actor.{Props, Actor, ActorLogging}

/**
  * Created by alifar on 2/11/16.
  */

object Distributor{
  def props(msg: Read): Props = Props(new Distributor(msg))

  case object Start
}

class Distributor(fileHandlerMsg :Read) extends Actor with ActorLogging{
  import Distributor._

  val fileHandler = context.actorOf(FileHandler.props(), "fileHandler")

  val minion = context.actorOf(MinionActor.props(), "minion")



//  def prepare: Set[ListBuffer] = {
//
//  }

  override def receive = {
    case FileHandlerResult(set) =>
      log.info(s"Set to be optimized: $set")
      val optimizeMsg = Optimize(set)
      minion ! optimizeMsg
    case Start =>
      log.info("Starting algorithm!")
      fileHandler ! fileHandlerMsg
    case Optimize(optimized) =>
      log.info(s"Optimization successful: $optimized")
    case _ =>
      log.info("Unknown message received")
  }
}
