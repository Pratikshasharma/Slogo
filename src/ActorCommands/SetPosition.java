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
        double newx=myCommandProcess.executeList(myCommandStorage,args.get(0));
        double newy=myCommandProcess.executeList(myCommandStorage,args.get(1));
        if(newx==Double.NaN || newy==Double.NaN){
            return Double.NaN;
        }
        actor.setPos(newx,newy);
        return actor.getDistance();
    }

}
