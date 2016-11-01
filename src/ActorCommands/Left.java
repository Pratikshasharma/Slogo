package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Left extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double angle=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedangle=myCommandProcess.executeList(myCommandStorage, parameter);
            angle+=segmentedangle;
            actor.setAngle(setValErrorCheck(actor.getAngle()-segmentedangle,actor.getAngle()));

        }
        return angle;
    }

}
