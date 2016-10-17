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
    
    public int penDown(Actor actor){
        actor.setPenStatus(true);
        return 1;
    }
    
    public int penUp(Actor actor){
        actor.setPenStatus(false);
        return 0;
    }
    
    public int show(Actor actor){
        actor.setVisibility(false);
        return 1;
    }
    
    public int hide(Actor actor){
        actor.setVisibility(false);
        return 0;
    }
    
    public double home(Actor actor){
        actor.setPos(0,0);
        return actor.getDistance();
    }
    
    public double clearScreen(Actor actor){
        return home(actor);
    }
    
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
