package ActorQueries;

import java.util.List;
import Actors.Actor;
import Command.ActorQuery;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns visibility status of actor.
 * 
 * @author Vincent
 *
 */
public class IsShowing extends ActorQuery{
    /* (non-Javadoc)
     * @see Command.ActorQuery#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        return (actor.getVisibility())?1:0;
    }

}
