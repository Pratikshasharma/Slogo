package Simulation;

import Actors.Actor;

public class ActorCommands {
    public void move(Actor actor,int x, int y){
        actor.setPos(x+actor.getX(),y+actor.getY());
    }
}
