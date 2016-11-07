package DisplayCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Sets a shape index for an actor.
 * Returns the index.
 * 
 * @author Vincent
 *
 */
public class SetShape extends DisplayCommand{
    /* (non-Javadoc)
     * @see Command.DisplayCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
         myCommandStorage.getActor(myCommandStorage.getActive()).setShapeIndex((int) index);
         return index;
    }

}
