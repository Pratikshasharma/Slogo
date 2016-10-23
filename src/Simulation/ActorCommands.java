package Simulation;

import Actors.Actor;

public class ActorCommands {
    public double Forward(Actor actor, double pixels){
        double x=Math.cos(actor.getAngle())*pixels;
        double y=Math.sin(actor.getAngle())*pixels;
        actor.setPos(actor.getX()+x,actor.getY()+y);
        return pixels;
    }
    
    public double Backward(Actor actor, double pixels){
        double x=Math.cos(actor.getAngle())*pixels;
        double y=Math.sin(actor.getAngle())*pixels;
        actor.setPos(actor.getX()-x,actor.getY()-y);
        return pixels;
    }
    
    public double Left(Actor actor, double angle){
        actor.setAngle(actor.getAngle()+angle);
        return angle;
    }
    
    public double Right(Actor actor, double angle){
        actor.setAngle(actor.getAngle()-angle);
        return angle;
    }
    
    public double SetHeading(Actor actor, double angle){
        actor.setAngle(angle);
        return actor.getAngleMoved();
    }
    
    public double SetTowards(Actor actor, double x, double y){
        double angle=Math.sin((actor.getY()-y)/(actor.getX()-x));
        actor.setAngle(angle);
        return actor.getAngleMoved();
    } 
    
    public double SetPosition(Actor actor, double x, double y){
        actor.setPos(x,y);
        return actor.getDistance();
    }     
    
    public double PenDown(Actor actor){
        actor.setPenStatus(true);
        return 1;
    }
    
    public double PenUp(Actor actor){
        actor.setPenStatus(false);
        return 0;
    }
    
    public double ShowTurtle(Actor actor){
        actor.setVisibility(true);
        return 1;
    }
    
    public double HideTurtle(Actor actor){
        actor.setVisibility(false);
        return 0;
    }
    
    public double Home(Actor actor){
        actor.setPos(0,0);
        return actor.getDistance();
    }
    
    public double ClearScreen(Actor actor){
        //set to clear screen in front end
        return Home(actor);
    }
}
