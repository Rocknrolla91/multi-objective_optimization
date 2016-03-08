package MultiObjectiveOptimizaton

import akka.actor.{Props, Actor, ActorLogging}
import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

/**
  * Created by alifar on 2/11/16.
  */

case class Read(filename: String)
case class FileHandlerResult(result: mutable.Set[List[Double]])

object FileHandler{
  def props(): Props = Props(new FileHandler())
}

class FileHandler extends Actor with ActorLogging{
  override def  receive = {
    case Read(file) =>
      log.info("Reading file")
      val fileLines = Source.fromFile(file).getLines().toList
      //log.info(s"file: $fileLines")
      var intFileLine = ListBuffer.empty[Double]
      var intFileLines = mutable.Set[List[Double]]()
      for (line <- fileLines){
        val str = line.split(" ")
        for(token <- str){
          intFileLine += token.toDouble
        }
        //log.info(s"intFileLine after line processing: $intFileLine")
        intFileLines += intFileLine.toList
        //log.info(s"intFileLines after append: $intFileLines")
        intFileLine --= intFileLine
        //log.info(s"intFileLine after clean up: $intFileLine")
      }
      sender() ! FileHandlerResult(intFileLines)
  }
}
