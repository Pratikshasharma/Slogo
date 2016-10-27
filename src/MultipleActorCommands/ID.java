package MultipleActorCommands;

import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class ID extends MultipleActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        return myCommandStorage.getActive();
    }

}