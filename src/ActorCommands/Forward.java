package ActorCommands;


import java.util.List;
import java.util.Map;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Forward extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        double x=0,y=0,pixels=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedpixels=myCommandProcess.executeList(myCommandStorage, parameter);
            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle())*segmentedpixels;
            y+=Math.sin(actor.getAngle())*segmentedpixels;
            actor.setPos(actor.getX().get()+x,actor.getY().get()+y);
        }
        return pixels;
    }

}
