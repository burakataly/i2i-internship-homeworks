package org.example;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class App {
    public static void main(String[] args) {
        Config config = ConfigFactory.load("second_actor.conf");
        ActorSystem actorSystem = ActorSystem.create("SecondActorSystem", config);
        actorSystem.actorOf(Props.create(SecondActor.class), "second_actor");
    }
}

