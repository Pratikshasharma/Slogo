package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for Math Operations.
 * Determines how those commands deal with multiple active actors (should only execute once- doesnt involve actors).
 * Deals with error checking for setting values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class MathOperation implements Command{
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
     * Error checking for math values.
     * 
     * @param val
     * @param exception
     * @return
     */
    public double mathValErrorCheck(double val, double exception){
        if(val==exception){
            return Double.NaN;
        }
        return val;
    }
    
    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
