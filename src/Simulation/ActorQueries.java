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
        return booleantodouble(actor.getPenStatus());
    }
    
    public double isShowing(Actor actor){
        return booleantodouble(actor.getVisibility());
    }
    
    private double booleantodouble(boolean bool){
        if(bool){
            return 1;
        }
        return 0;
    }
}
