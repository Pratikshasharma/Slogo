package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class ShowTurtle extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));
        actor.setVisibility(true);
        return 1;
    }

}
