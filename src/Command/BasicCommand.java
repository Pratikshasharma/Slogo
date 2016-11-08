/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the second level of my hierarchy and provides the basic call for a command.
 * See CommandInterface for more complete description.
 * 
 */

package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Most basic form of command running abstract class.
 * 
 * @author Vincent
 *
 */
public abstract class BasicCommand implements CommandInterface{
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
