package ActorCommands;

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
        double x=0,y=0,pixels=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedpixels=myCommandProcess.executeList(myCommandStorage, parameter);
            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle()/180*Math.PI)*segmentedpixels;
            y+=Math.sin(actor.getAngle()/180*Math.PI)*segmentedpixels;
            if(x<-MainGUI.TURTLE_PANE_WIDTH){
                
            }
            
            actor.setPos(setValErrorCheck(actor.getX().get()+x,actor.getX().get()),setValErrorCheck(actor.getY().get()+y,actor.getY().get()));
        }
        return pixels;
    }

}
