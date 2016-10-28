package Command;

import java.util.List;
import java.util.Set;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

//shouldn't need to repeat a bunch for actorqueries-want to return only one number still
public abstract class DisplayCommand implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }

    public boolean boundsErrorCheck(double val,double lower,double upper){
        return(val<=upper && val>=lower);
    }

    public boolean indexErrorCheck(double val,Set<Integer> indexvals){
        return(indexvals.contains((int)val));
    }
    
    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<InfoNode> args);

}
