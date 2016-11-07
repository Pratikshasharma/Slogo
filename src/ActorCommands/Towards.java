package ActorCommands;

import java.util.List;
import Actors.Actor;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Sets the heading of the actor towards a specific position.
 * Returns angle moved.
 * 
 * @author Vincent
 *
 */
public class Towards extends ActorCommandSetValueErrorCheck{
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
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
