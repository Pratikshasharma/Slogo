/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the second level of my hierarchy and provides the call for commands that execute over multiple actors.
 * See CommandInterface for more complete description.
 * 
 */
package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
    
/**
 * Abstract class for commands that execute on multiple actors.
 * Determines how those commands deal with multiple active actors (execute on all).
 * 
 * @author Vincent
 *
 */
public abstract class ExecuteMultipleActorsCommand implements CommandInterface{
    public CommandProcess myCommandProcess;
    /* (non-Javadoc)
     * @see Command.Command#call(Simulation.CommandStorage, java.util.List)
     */
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
    
    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
