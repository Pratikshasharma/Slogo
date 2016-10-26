package ActorCommands;

import java.util.List;
import Actors.Actor;
import Commands.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Backward extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {
        double x=0,y=0,pixels=0;
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));
        for(InfoNode parameter:args){
            double segmentedpixels=myCommandProcess.executeList(myCommandStorage, ActorsChanged, parameter);
            pixels+=segmentedpixels;
            x+=Math.cos(actor.getAngle())*segmentedpixels;
            y+=Math.sin(actor.getAngle())*segmentedpixels;
            actor.setPos(actor.getX()-x,actor.getY()-y);
        }
        return pixels;
    }

}