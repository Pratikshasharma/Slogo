package ActorQueries;

import java.util.List;
import Actors.Actor;
import Command.ActorQuery;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class XCoordinate extends ActorQuery{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));
        return actor.getX();
    }

}
