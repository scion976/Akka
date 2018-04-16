package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.DeadLetter;
import akka.actor.Props;

public class DivinePostman extends AbstractActor {
    static public Props props(ActorRef printerActor) {
        return Props.create(DivinePostman.class, () -> new DivinePostman(printerActor));
    }

    private ActorRef printerActor;
    DivinePostman(ActorRef printerActor) {
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(DeadLetter.class, m -> {
            printerActor.tell(new Printer.Greeting("THE DIVINE POSTMAN COMETH and blesses " + m.message()), self());
        }).build();
    }
}
