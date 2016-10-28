package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Towards extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());        
        double angle=Math.sin((actor.getY().get()-myCommandProcess.executeList(myCommandStorage,args.get(0)))/(actor.getX().get()-myCommandProcess.executeList(myCommandStorage,args.get(0))));
        actor.setAngle(setValErrorCheck(angle,actor.getAngle()));
        if(angle==Double.NaN){
            return angle;
        }
        return actor.getAngleMoved();
    }

}
