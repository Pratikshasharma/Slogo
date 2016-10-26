package ActorCommands;

import java.util.List;
import Actors.Actor;
import Commands.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Towards extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(ActorsChanged.get(0));        
        double angle=Math.sin((actor.getY()-myCommandProcess.executeList(myCommandStorage,ActorsChanged,args.get(0)))/(actor.getX()-myCommandProcess.executeList(myCommandStorage,ActorsChanged,args.get(0))));
        actor.setAngle(angle);
        return actor.getAngleMoved();
    }

}
