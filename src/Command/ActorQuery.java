package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

//shouldn't need to repeat a bunch for actorqueries-want to return only one number still
public abstract class ActorQuery implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }

    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<InfoNode> args);

}
