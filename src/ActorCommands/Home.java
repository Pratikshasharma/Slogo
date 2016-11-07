package ActorCommands;

import java.util.List;
import Actors.Actor;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

/**
 * Sets actor to home position.
 * Returns distance traveled.
 * 
 * @author Vincent
 *
 */
public class Home extends ActorCommand{
    private static final double X_HOME=MainGUI.TURTLE_PANE_WIDTH / 2;
    private static final double Y_HOME=MainGUI.TURTLE_PANE_WIDTH / 2;
    
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setPos(X_HOME,Y_HOME);
        return actor.getDistance();
    }

}
