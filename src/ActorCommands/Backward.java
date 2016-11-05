package ActorCommands;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

/**
 * Moves actor backwards a certain number of pixels.
 * Returns pixels.
 * 
 * @author Vincent
 *
 */
public class Backward extends ActorCommand{
    
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        double x=0,y=0,pixels=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedpixels=myCommandProcess.executeList(myCommandStorage, parameter);
            System.out.println(segmentedpixels);

            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle()*Math.PI/180)*segmentedpixels;
            y+=Math.sin(actor.getAngle()*Math.PI/180)*segmentedpixels;
            System.out.println(x);
            System.out.println(y);

            if(actor.getX().get()-x<0){
                x=actor.getX().get();
                y=Math.tan(actor.getAngle()*Math.PI/180)*(x);
            }
            if(actor.getX().get()-x>MainGUI.TURTLE_PANE_WIDTH){
                x=actor.getX().get()-MainGUI.TURTLE_PANE_WIDTH;
                y=Math.tan((actor.getAngle()+180)*Math.PI/180)*(x);
            }
            if(actor.getY().get()-y<0){
                y=actor.getY().get();
                x=y/Math.tan((actor.getAngle()+180)*Math.PI/180);
            }
            if(actor.getY().get()-y>MainGUI.TURTLE_PANE_HEIGHT){
                y=actor.getY().get()-MainGUI.TURTLE_PANE_HEIGHT;
                x=y/Math.tan((actor.getAngle()+180)*Math.PI/180);
            }
            if(x==actor.getX().get()-MainGUI.TURTLE_PANE_WIDTH || x==actor.getX().get()){
                actor.setAngle(180-actor.getAngle());
            }
            if(y==actor.getY().get()-MainGUI.TURTLE_PANE_HEIGHT|| y==actor.getY().get()){
                actor.setAngle(360-actor.getAngle());
            }
            
            actor.setPos(setValErrorCheck(actor.getX().get()-x,actor.getX().get()),setValErrorCheck(actor.getY().get()-y,actor.getY().get()));

            //deal with fact that wont  get exact 0
            if(segmentedpixels-actor.getDistance()>1){
                Backward leftoverback=new Backward();
                double leftover=segmentedpixels-actor.getDistance();
                List<InfoNode> leftoverargs=new ArrayList<InfoNode>();
                leftoverargs.add(new InfoNode(Double.toString(leftover),"Constant"));
                leftoverback.call(myCommandStorage, leftoverargs);
            }
        }
        return pixels;
    }

}