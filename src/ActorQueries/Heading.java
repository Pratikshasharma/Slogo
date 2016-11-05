package ActorQueries;

import java.util.List;
import Actors.Actor;
import Command.ActorQuery;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns the heading of the actor.
 * 
 * @author Vincent
 *
 */
public class Heading extends ActorQuery{
    /* (non-Javadoc)
     * @see Command.ActorQuery#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        return actor.getAngle();
    }

}
