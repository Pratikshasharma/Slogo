package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Left extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        double x=0,y=0,angle=0;
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));
        for(InfoNode parameter:args){
            double segmentedangle=myCommandProcess.executeList(myCommandStorage, ActorsChanged, parameter);
            angle+=segmentedangle;
            actor.setAngle(actor.getAngle()+segmentedangle);

        }
        return angle;
    }

}
