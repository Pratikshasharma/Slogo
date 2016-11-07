package DisplayCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return pen color index of actor.
 * 
 * @author Vincent
 *
 */
public class GetPenColor extends DisplayCommand{
    /* (non-Javadoc)
     * @see Command.DisplayCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         return myCommandStorage.getActor(myCommandStorage.getActive()).getPenColorIndex();
    }

}
