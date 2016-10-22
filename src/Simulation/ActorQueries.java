package Simulation;

import Actors.Actor;

public class ActorQueries {
    public double xCoordinate(Actor actor){
        return (double) actor.getX();
    }
    
    public double yCoordinate(Actor actor){
        return (double) actor.getY();
    }
    
    public double heading(Actor actor){
        return actor.getAngle();
    }
    
    public double isPenDown(Actor actor){
        return (actor.getPenStatus())?1:0;
    }
    
    public double isShowing(Actor actor){
        return (actor.getVisibility())?1:0;
    }
}
