package echo

import akka.actor.{Actor, ActorSystem, Props}
import scala.io.StdIn

case class Message(msg: String)
case object Bye

class EchoActor extends Actor {
    def receive = {
        case Message(s) => println(s"you said $s\n")
        case Bye => println("bye!")
        case _ => println("huh?")
    }
}

object EchoMain extends App {

    // an actor needs an ActorSystem
    val system = ActorSystem("EchoSystem")

    // create and start the actor
    val echoActor = system.actorOf(Props[EchoActor], name = "echoActor")

    // prompt the user for input
    var input = ""
    while (input != "q") {
        print("type something (q to quit): ")
        input = StdIn.readLine()
        echoActor ! Message(input)
        // a brief pause so the actor can print its output
        // before this loop prints again
        Thread.sleep(200)
    }

    echoActor ! Bye

    // shut down the system
    system.terminate()

}
