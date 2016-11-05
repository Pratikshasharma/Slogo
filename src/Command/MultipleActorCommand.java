package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for Multiple Actor commands.
 * Determines how those commands deal with multiple active actors (execute once since will see how execute on all after).
 * 
 * @author Vincent
 *
 */
public abstract class MultipleActorCommand implements Command{
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
