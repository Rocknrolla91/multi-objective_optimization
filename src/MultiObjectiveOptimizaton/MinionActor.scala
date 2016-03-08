package MultiObjectiveOptimizaton

import akka.actor.{Actor, Props, ActorLogging}
import scala.collection.mutable
import scala.math.abs

/**
  * Created by alifar on 2/7/16.
  */

object Opt extends Enumeration{
  val Min, Max = Value
}

case class Optimize(points: mutable.Set[List[Double]])

object MinionActor{
  def props(): Props = {Props(new MinionActor())}
}

class MinionActor extends Actor with ActorLogging{

  def oneToOneCompare(point: List[Double], optPoint: List[Double]): (Int, Int) ={
    var valuableObjectives = point.length
    class IterPoint extends ListIterator(point)
    class IterOptPoint extends ListIterator(optPoint)
    val iter = new IterPoint
    val iter1 = new IterOptPoint
    var bool_result = 0
    while(iter.hasNext && iter1.hasNext){
      val n = iter.next
      val n1 = iter1.next
      if(n == n1){
        valuableObjectives -= 1
      } else if(n < n1){
        bool_result += 1
      } else {
        bool_result -= 1
      }
    }
    //log.info(s"$bool_result")
    (bool_result, valuableObjectives)
  }

  def pointCompare(resultSet: mutable.Set[List[Double]],
                   newPoint: List[Double],
                   optPoint: List[Double],
                   compareResult: Int, valuableObjectives: Int): mutable.Set[List[Double]] ={
    //val numberOfObjectives = newPoint.length
    val compareResultAbs = abs(compareResult)
   // println(s"new point: $newPoint && point from set: $optPoint")
    if(compareResult > 0 && compareResult == valuableObjectives){
      log.info(s"Point: $newPoint is more optimal then point: $optPoint")
      resultSet -= optPoint
      resultSet += newPoint
    } else if (compareResult < 0 && compareResultAbs == valuableObjectives){
      log.info(s"Point: $newPoint is not optimal :::")
    } else {
      log.info(s"Point: $newPoint is optimal")
      resultSet += newPoint
    }
    resultSet
  }

  def optimize(pointSet: mutable.Set[List[Double]]): mutable.Set[List[Double]]  = {
    var resultSet = mutable.Set[List[Double]]()
    var setForComp = resultSet
    for(point <- pointSet){
      if(resultSet == null || resultSet.isEmpty){
        resultSet += point
      } else {
        for(optPoint <- resultSet){
          log.info(s"LOOP: point from set: $point, point from resultSet: $optPoint")
          val (tempResult, valuableObjectives) = oneToOneCompare(point, optPoint)
          setForComp = pointCompare(setForComp, point, optPoint, tempResult, valuableObjectives)
//          log.info(s"OptPoint $optPoint")
//          if(x < optPoint.head  && y < optPoint(1)){
//            log.info(s"Point: $point is more optimal then $optPoint")
//            resultSet -= optPoint
//            resultSet += point
//          } else if ((x < optPoint.head && y > optPoint(1)) ||
//            (x > optPoint.head && y < optPoint(1))){
//            log.info(s"Point: $point is optimal")
//            resultSet += point
//          } else {
//            log.info(s"Point: $point is not optimal")
//          }
//          pointCompare(resultSet, point, optPoint, 5)
        }
        resultSet = setForComp
      }
      log.info(s"Optimized set is now: $resultSet")
    }
    resultSet
  }

  override def receive = {
    case Optimize(points) =>
      //log.info(s"cock: $points")
      val optimized = optimize(points)
      sender() ! Optimize(optimized)
  }
}
