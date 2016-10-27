package Command;

import java.util.ArrayList;
import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public abstract class MathOperation implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }

    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
