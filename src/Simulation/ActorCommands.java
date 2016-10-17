package Simulation;

import Actors.Actor;

public class ActorCommands {
    public int forward(Actor actor, int pixels){
        int x=(int) Math.round(Math.cos(actor.getAngle())*pixels);
        int y=(int) Math.round(Math.sin(actor.getAngle())*pixels);
        actor.setPos(actor.getX()+x,actor.getY()+y);
        return pixels;
    }
    
    public int backward(Actor actor, int pixels){
        int x=(int) Math.round(Math.cos(actor.getAngle())*pixels);
        int y=(int) Math.round(Math.sin(actor.getAngle())*pixels);
        actor.setPos(actor.getX()-x,actor.getY()-y);
        return pixels;
    }
    
    public double left(Actor actor, double angle){
        actor.setAngle(actor.getAngle()+angle);
        return angle;
    }
    
    public double right(Actor actor, double angle){
        actor.setAngle(actor.getAngle()-angle);
        return angle;
    }
    
    public double setHeading(Actor actor, double angle){
        actor.setAngle(angle);
        return actor.getAngleMoved();
    }
    
    public double setTowards(Actor actor, int x, int y){
        double angle=Math.sin(((double)(actor.getY()-y))/((double)(actor.getX()-x)));
        actor.setAngle(angle);
        return actor.getAngleMoved();
    } 
    
    public double setPosition(Actor actor, int x, int y){
        actor.setPos(x,y);
        return actor.getDistance();
    }     
    
    public boolean penDown(Actor actor){
        actor.setPenStatus(true);
        return actor.getPenStatus();
    }
    
    public boolean penUp(Actor actor){
        actor.setPenStatus(false);
        return actor.getPenStatus();
    }
    
    public boolean show(Actor actor){
        actor.setVisibility(true);
        return actor.getVisibility();
    }
    
    public boolean hide(Actor actor){
        actor.setVisibility(false);
        return actor.getVisibility();
    }
    
    public double home(Actor actor){
        actor.setPos(0,0);
        return actor.getDistance();
    }
    
    public double clearScreen(Actor actor){
        return home(actor);
    }
}
