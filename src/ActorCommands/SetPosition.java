package ActorCommands;

import java.util.List;
import Actors.Actor;
import Command.ActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import gui.MainGUI;

public class SetPosition extends ActorCommand{
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
