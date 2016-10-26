package ActorCommands;

import java.util.List;
import Actors.Actor;
import Commands.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class PenUp extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));
        actor.setPenStatus(false);
        return 0;
    }

}
