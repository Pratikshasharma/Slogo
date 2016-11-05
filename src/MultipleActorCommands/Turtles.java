package MultipleActorCommands;

import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns the number of turtles that have been created.
 * 
 * @author Vincent
 *
 */
public class Turtles extends MultipleActorCommand{
    /* (non-Javadoc)
     * @see Command.MultipleActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        return myCommandStorage.getActorMap().size();
    }

}