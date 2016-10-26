package Commands;

import java.util.ArrayList;
import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

//shouldn't need to repeat a bunch for actorqueries
public abstract class ActorQuery implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage, List<Integer> ActorsChanged, List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        List<Integer> tempList=new ArrayList<Integer>();
        tempList.add(ActorsChanged.get(0));
        return execute(myCommandStorage, tempList, args);
    }

    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<Integer> ActorsChanged, List<InfoNode> args);

}
