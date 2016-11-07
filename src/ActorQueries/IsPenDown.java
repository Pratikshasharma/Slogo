package ActorQueries;

import java.util.List;
import Actors.Actor;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * REturns pen status of the actor.
 * 
 * @author Vincent
 *
 */
public class IsPenDown extends ActorQuery{
    /* (non-Javadoc)
     * @see Command.ActorQuery#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        return (actor.getPenStatus().get())?1:0;
    }

}
