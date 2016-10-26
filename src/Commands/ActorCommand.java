package Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Actors.Actor;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public abstract class ActorCommand implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage, List<Integer> ActorsChanged, List<InfoNode> args) {
        double result = Double.NaN;
        myCommandProcess=new CommandProcess();
        for(int i:ActorsChanged){
            List<Integer> tempList=new ArrayList<Integer>();
            tempList.add(i);
            result=execute(myCommandStorage, tempList, args);
        }
        //return result of last command or else if result is used for other operations it wont make sense (generally used for the execution part of moving turtle)
        return result;
    }

    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<Integer> ActorsChanged, List<InfoNode> args);

}
