package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for Complex Commands.
 * Determines how those commands deal with multiple active actors (outsource elsewhere).
 * 
 * @author Vincent
 *
 */
public abstract class ComplexCommand implements Command{
    public CommandProcess myCommandProcess;
    
    /* (non-Javadoc)
     * @see Command.Command#call(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }

    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
