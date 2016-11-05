package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Turn the actor left a certain nmber of degrees.
 * Returns the angle moved.
 * 
 * @author Vincent
 *
 */
public class Left extends ActorCommand{
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double angle=0;
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        for(InfoNode parameter:args){
            double segmentedangle=myCommandProcess.executeList(myCommandStorage, parameter);
            angle+=segmentedangle;
            actor.setAngle(setValErrorCheck(actor.getAngle()-segmentedangle,actor.getAngle()));

        }
        return angle;
    }

}
