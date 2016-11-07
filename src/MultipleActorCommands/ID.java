package MultipleActorCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns the ID of the active actor (assumed only one actor or doesnt make sense).
 * 
 * @author Vincent
 *
 */
public class ID extends MultipleActorCommand{
    /* (non-Javadoc)
     * @see Command.MultipleActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        return myCommandStorage.getActive();
    }

}