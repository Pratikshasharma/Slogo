package MultipleActorCommands;

import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Turtles extends MultipleActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        return myCommandStorage.getActorMap().size();
    }

}