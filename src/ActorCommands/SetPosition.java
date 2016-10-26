package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetPosition extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));
        actor.setPos(myCommandProcess.executeList(myCommandStorage,ActorsChanged,args.get(0)),myCommandProcess.executeList(myCommandStorage,ActorsChanged,args.get(1)));
        return actor.getDistance();
    }

}
