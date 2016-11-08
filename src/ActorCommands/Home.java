package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
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
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setPos(MainGUI.TURTLE_PANE_WIDTH / 2, MainGUI.TURTLE_PANE_HEIGHT / 2);
        return actor.getDistance();
    }

}
