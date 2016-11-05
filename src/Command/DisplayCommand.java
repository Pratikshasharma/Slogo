package Command;

import java.util.List;
import java.util.Set;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for displacy commands.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * Deals with error checking for bounds and index values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class DisplayCommand implements Command{
    public CommandProcess myCommandProcess;
    
    /* (non-Javadoc)
     * @see Command.Command#call(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }

    /**
     * Error checking for bounds values.
     * 
     * @param val
     * @param lower
     * @param upper
     * @return
     */
    public boolean boundsErrorCheck(double val,double lower,double upper){
        return(val<=upper && val>=lower);
    }

    /**
     * Error checking for index values.
     * 
     * @param val
     * @param indexvals
     * @return
     */
    public boolean indexErrorCheck(double val,Set<Integer> indexvals){
        return(indexvals.contains((int)val));
    }
    
    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<InfoNode> args);

}
