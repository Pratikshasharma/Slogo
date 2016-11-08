package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;


/**
 * Abstract class for Actor commands.
 * Determines how those commands deal with multiple active actors (shouldn't need to repeat a bunch for actorqueries-want to return only one number still).
 * 
 * 
 * @author Vincent
 *
 */
public abstract class ActorQuery implements Command{
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
    public abstract double execute (CommandStorage myCommandStorage, List<InfoNode> args);

}
