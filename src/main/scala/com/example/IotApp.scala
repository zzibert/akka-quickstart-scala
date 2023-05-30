package com.example

import akka.actor.typed.ActorSystem

object IotApp {

  def main(args: Array[String]): Unit = {
    val system: ActorSystem[HelloWorldMain.SayHello] = ActorSystem(HelloWorldMain(), "hello")

    system ! HelloWorldMain.SayHello("World")
    system ! HelloWorldMain.SayHello("Akka")
  }

}
