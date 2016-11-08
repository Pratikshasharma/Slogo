package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for Boolean operations.
 * Determines how those commands deal with multiple active actors (should only need to execute once- doesn't involve actors).
 * Deals with error checking for checking values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class BooleanOperation implements Command{
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
     * Error checking for boolean values
     * 
     * 
     * @param result
     * @return
     */
    public boolean boolErrorCheck(double result){
        if(result==Double.NaN){
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
