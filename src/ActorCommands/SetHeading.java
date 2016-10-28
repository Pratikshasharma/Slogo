package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetHeading extends ActorCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        //doesn't make sense to have multiple arguments from the parser
        double newangle=myCommandProcess.executeList(myCommandStorage,args.get(0));
        actor.setAngle(setValErrorCheck(newangle,actor.getAngle()));
        if(newangle==Double.NaN){
            return newangle;
        }
        return actor.getAngleMoved();
    }

}
