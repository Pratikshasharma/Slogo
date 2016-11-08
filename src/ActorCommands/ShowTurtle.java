package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Set the actor to be visible
 * Returns 1.
 * 
 * @author Vincent
 *
 */
public class ShowTurtle extends ActorCommand{
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setVisibility(true);
        return 1;
    }

}
