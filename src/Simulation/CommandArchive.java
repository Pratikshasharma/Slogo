package Simulation;

import java.lang.reflect.Method;
import java.util.List;
import Actors.Actor;

public class CommandArchive {
    private ActorCommands changeActor;
    private ActorQueries askActor;
    private BooleanOperations booleans;
    private MathOperations math;    
    private List<InfoNode> myCommands;
    private Method myMethod;
    /*
     * one parameter: left
     * two parameters: left, middle
     * three parameters: left, middle, right
     */
    
    public CommandArchive () {
        changeActor = new ActorCommands();
        askActor=new ActorQueries();
        booleans=new BooleanOperations();
        math=new MathOperations();
        myCommands = new ArrayList<InfoNode>();
    }
    
    public double execute(List<InfoNode>){
        myMethod = Method.forName(myCommands.getName());
    }
    
    public double Forward(){
        
    }
    
    public double Backward(){
        
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
    
    public double SetTowards(Actor actor, int x, int y){
        double angle=Math.sin(((double)(actor.getY()-y))/((double)(actor.getX()-x)));
        actor.setAngle(angle);
        return actor.getAngleMoved();
    } 
    
    public double SetPosition(Actor actor, int x, int y){
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
