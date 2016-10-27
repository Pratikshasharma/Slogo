package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetPosition extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        actor.setPos(myCommandProcess.executeList(myCommandStorage,args.get(0)),myCommandProcess.executeList(myCommandStorage,args.get(1)));
        return actor.getDistance();
    }

}
