package hello

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

case class Hello(msg: String)

class HelloActor extends Actor {
    def receive = {
        case Hello(s) => {
            println(s"you said '$s'")
            println(s"$s back at you!\n")
        }
        case _ => println("huh?")
    }
}

object AkkaHelloWorld extends App {

    // an actor needs an ActorSystem
    val system = ActorSystem("HelloSystem")

    // create and start the actor
    val helloActor = system.actorOf(Props[HelloActor], name = "helloActor")

    // send the actor two known messages
    helloActor ! Hello("hello")
    helloActor ! Hello("buenos dias")

    // send it an unknown message
    helloActor ! "hi!"

    // shut down the system
    system.terminate()

}


