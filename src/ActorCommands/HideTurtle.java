package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Sets the turtle to be invisible.
 * Returns 0.
 * 
 * @author Vincent
 *
 */
public class HideTurtle extends ActorCommand{
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setVisibility(false);
        return 0;
    }

}
