package MultiObjectiveOptimizaton

import akka.actor.ActorSystem

/**
  * Created by alifar on 2/7/16.
  */

// TODO: method to create set of Lists - done
// TODO: method in minionActor that can optimize a small set of points - done
// TODO: upgrade method in minionActor to work with different dimensions of points - multi-dimensional comparator
// TODO: Master actor that can allocate actors per partial in set
// TODO: method in Master Actor that can analyze sub-optimal results
// TODO: method that can divide set of points between children Actors
// TODO: tests for using more then 2 objectives
// TODO: optimize input data
// TODO: Visualization of results.. maybe web

object Test extends App {
  val system = ActorSystem("test")

  val fileHandlerMessage = Read("/Users/alifar/IdeaProjects/scala_tutorial/src/MultiObjectiveOptimizaton/test.txt")

  val distributor = system.actorOf(Distributor.props(fileHandlerMessage), "distributor")

  distributor ! Distributor.Start
}