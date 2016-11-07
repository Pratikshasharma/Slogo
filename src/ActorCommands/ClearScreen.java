package ActorCommands;

import java.util.List;
import Actors.Actor;
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
    private static final double X_HOME=MainGUI.TURTLE_PANE_WIDTH / 2;
    private static final double Y_HOME=MainGUI.TURTLE_PANE_WIDTH / 2;

    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setPos(X_HOME, Y_HOME);
        actor.setReset();
        return actor.getDistance();
    }

}
