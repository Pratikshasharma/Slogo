package Simulation;

import Actors.Actor;

public class ActorQueries {
    public int xcor(Actor actor){
        return actor.getX();
    }
    
    public int ycor(Actor actor){
        return actor.getY();
    }
    
    public double heading(Actor actor){
        return actor.getAngle();
    }
    
    public boolean isPenDown(Actor actor){
        return actor.getPenStatus();
    }
    
    public boolean isShowing(Actor actor){
        return actor.getVisibility();
    }
}
