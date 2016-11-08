/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the second level of my hierarchy and provides the call for commands that use helpers.
 * See CommandInterface for more complete description.
 * 
 */
package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for commands that use helper.
 * 
 * @author Vincent
 *
 */
public abstract class ExecuteHelperCommnd implements CommandInterface{
    public CommandProcess myCommandProcess;
    
    /* (non-Javadoc)
     * @see Command.Command#call(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return executeHelper(myCommandStorage, args);
    }
    
    /**
     * Helper for execution command to remove some of the repetition.
     * Used mainly for boolean operations since we don't see mass repetition elsewhere.
     * 
     * @param myCommandStorage
     * @param args
     * @return
     */
    protected abstract double executeHelper(CommandStorage myCommandStorage, List<InfoNode> args);


    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<InfoNode> args);

}
