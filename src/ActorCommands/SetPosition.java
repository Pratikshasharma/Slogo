package ActorCommands;

import java.util.List;
import Actors.Actor;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

/**
 * Sets the position of the actor
 * Returns distance traveled.
 * 
 * @author Vincent
 *
 */
public class SetPosition extends ActorCommand{
    /* (non-Javadoc)
     * @see Command.ActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        Actor actor=myCommandStorage.getActor(myCommandStorage.getActive());
        double newx=myCommandProcess.executeList(myCommandStorage,args.get(0));
        double newy=myCommandProcess.executeList(myCommandStorage,args.get(1));
        if(newx==Double.NaN || newy==Double.NaN || newx<-MainGUI.TURTLE_PANE_WIDTH/2 || newy <-MainGUI.TURTLE_PANE_WIDTH/2 || newx>MainGUI.TURTLE_PANE_WIDTH/2 ||newx>MainGUI.TURTLE_PANE_WIDTH/2){
            return Double.NaN;
        }
        actor.setPos(newx,newy);
        return actor.getDistance();
    }

}
