package ActorQueries;

import java.util.List;
import Actors.Actor;
import Command.ActorQuery;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class IsPenDown extends ActorQuery{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        return (actor.getPenStatus())?1:0;
    }

}
