package ActorCommands;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

public class Forward extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        double x=0,y=0,pixels=0,leftoverpixels=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedpixels=myCommandProcess.executeList(myCommandStorage, parameter);
            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle()/180*Math.PI)*segmentedpixels;
            y+=Math.sin(actor.getAngle()/180*Math.PI)*segmentedpixels;
            
            if(x<-MainGUI.TURTLE_PANE_WIDTH){
                x=0;
                y=Math.tan(actor.getAngle())*(x-actor.getX().get());
                actor.setAngle(-actor.getAngle());
            }
            if(x>MainGUI.TURTLE_PANE_WIDTH){
                x=MainGUI.TURTLE_PANE_WIDTH;
                y=Math.tan(actor.getAngle())*(x-actor.getX().get());
                actor.setAngle(-actor.getAngle());
            }
            if(y<-MainGUI.TURTLE_PANE_HEIGHT){
                y=0;
                x=Math.tan((y-actor.getY().get())/actor.getAngle());
                actor.setAngle(-actor.getAngle());
            }
            if(y>MainGUI.TURTLE_PANE_HEIGHT){
                y=MainGUI.TURTLE_PANE_HEIGHT;
                x=Math.tan((y-actor.getY().get())/actor.getAngle());
                actor.setAngle(-actor.getAngle());
            }
            
            actor.setPos(setValErrorCheck(actor.getX().get()+x,actor.getX().get()),setValErrorCheck(actor.getY().get()+y,actor.getY().get()));
            if(segmentedpixels-actor.getDistance()>0){
                List<InfoNode> leftoverargs=new ArrayList<InfoNode>();
                leftoverargs.add(new InfoNode(leftoverargs.toString(),"Constant"));
                this.execute(myCommandStorage, leftoverargs);
            }
            
        }
        return pixels;
    }

}
