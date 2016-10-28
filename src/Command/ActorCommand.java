package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public abstract class ActorCommand implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        double result = Double.NaN;
        List<Integer> activeList=myCommandStorage.getActiveList();

        myCommandProcess=new CommandProcess();
        for(int i:activeList){
            myCommandStorage.setActive(i);
            result=execute(myCommandStorage, args);
        }
        myCommandStorage.setActive(activeList);
        //return result of last command or else if result is used for other operations it wont make sense (generally used for the execution part of moving turtle)
        return result;
    }

    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
