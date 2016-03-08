package AkkaTutorial

import akka.actor.ActorSystem
import akka.actor.Props
/**
  * Created by alifar on 2/7/16.
  */

object DriverApp extends App{
  val system = ActorSystem("StudentTeacherSystem")

  val teacherRef = system.actorOf(Props[TeacherActor], "teacherRef")

  val studentRef = system.actorOf(StudentActor.props(teacherRef), "studentRef")

  studentRef ! InitSignal

  Thread.sleep(2000)

  system.terminate()
}
