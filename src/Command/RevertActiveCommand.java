/*
 * THIS IS PART OF MY CODE MASTERPIECE.
 * This is part of the second level of my hierarchy and provides the call for commands that revert the active actors after called.
 * See CommandInterface for more complete description.
 * 
 */
package Command;

import java.util.ArrayList;
import java.util.List;
import Command.BasicCommand;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for commands that reset the active actors.
 * Determines how those commands deal with multiple active actors (execute once since will see how execute on all after).
 * 
 * @author Vincent
 *
 */
public abstract class RevertActiveCommand extends BasicCommand{
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        List<Integer> originalActive=new ArrayList<Integer>(myCommandStorage.getActiveList());
        double result= execute(myCommandStorage, args);
        myCommandStorage.setActive(originalActive);
        return result;
    }
}
