package ThingsToRemoveLater;

import Actors.Actor;

public class ActorQueries {
    public double XCoordinate(Actor actor){
        return actor.getX();
    }
    
    public double YCoordinate(Actor actor){
        return actor.getY();
    }
    
    public double Heading(Actor actor){
        return actor.getAngle();
    }
    
    public double IsPenDown(Actor actor){
        return (actor.getPenStatus())?1:0;
    }
    
    public double IsShowing(Actor actor){
        return (actor.getVisibility())?1:0;
    }
}
