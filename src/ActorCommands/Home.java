package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

public class Home extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setPos(MainGUI.TURTLE_PANE_WIDTH / 2, MainGUI.TURTLE_PANE_HEIGHT / 2);
        return actor.getDistance();
    }

}
