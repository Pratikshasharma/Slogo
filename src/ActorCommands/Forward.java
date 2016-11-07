package ActorCommands;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

/**
 * Move actor forwards a certain number of pixels.
 * Returns pixels.
 * 
 * @author Vincent
 *
 */
public class Forward extends ActorCommandSetValueErrorCheck{    
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
            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle()*TO_RADIANS)*segmentedpixels;
            y+=Math.sin(actor.getAngle()*TO_RADIANS)*segmentedpixels;


            if(x+actor.getX().get()<0){
                x=-actor.getX().get();
                y=Math.tan(actor.getAngle()*TO_RADIANS)*(x);
            }
            if(x+actor.getX().get()>MainGUI.TURTLE_PANE_WIDTH){
                x=MainGUI.TURTLE_PANE_WIDTH-actor.getX().get();
                y=Math.tan(actor.getAngle()*TO_RADIANS)*(x);
            }
            if(y+actor.getY().get()<0){
                y=-actor.getY().get();
                x=y/Math.tan(actor.getAngle()*TO_RADIANS);
            }
            if(y+actor.getY().get()>MainGUI.TURTLE_PANE_HEIGHT){
                y=MainGUI.TURTLE_PANE_HEIGHT-actor.getY().get();
                x=y/Math.tan(actor.getAngle()*TO_RADIANS);
            }
            if(x==MainGUI.TURTLE_PANE_WIDTH-actor.getX().get()|| x==-actor.getX().get()){
                actor.setAngle(HALF_CIRCLE_DEGREES-actor.getAngle());
            }
            if(y==MainGUI.TURTLE_PANE_HEIGHT-actor.getY().get() || y==-actor.getY().get()){
                actor.setAngle(FULL_CIRCLE_DEGREES-actor.getAngle());
            }
            
            actor.setPos(setValErrorCheck(actor.getX().get()+x,actor.getX().get()),setValErrorCheck(actor.getY().get()+y,actor.getY().get()));
            //deal with fact that wont  get exact 0
            if(segmentedpixels-actor.getDistance()>1){
                Forward leftoverforward=new Forward();
                double leftover=segmentedpixels-actor.getDistance();
                List<InfoNode> leftoverargs=new ArrayList<InfoNode>();
                leftoverargs.add(new InfoNode(Double.toString(leftover),LEFTOVER_TYPE));
                leftoverforward.call(myCommandStorage, leftoverargs);
            }
            
        }
        return pixels;
    }

}
