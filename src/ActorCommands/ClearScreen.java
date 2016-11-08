package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

/**
 * Clears the actors tracks and set actor to home
 * Returns distance traveled.
 * 
 * @author Vincent
 *
 */
public class ClearScreen extends ActorCommand{
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setPos(MainGUI.TURTLE_PANE_WIDTH / 2, MainGUI.TURTLE_PANE_HEIGHT / 2);
        actor.setReset();
        return actor.getDistance();
    }

}
