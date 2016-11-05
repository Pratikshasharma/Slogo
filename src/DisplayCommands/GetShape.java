package DisplayCommands;

import java.util.List;
import Command.DisplayCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return shape index of actor.
 * 
 * @author Vincent
 *
 */
public class GetShape extends DisplayCommand{
    /* (non-Javadoc)
     * @see Command.DisplayCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         return myCommandStorage.getActor(myCommandStorage.getActive()).getShapeIndex();
    }

}
