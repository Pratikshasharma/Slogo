package DisplayCommands;

import java.util.List;
import Command.DisplayCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetShape extends DisplayCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
         myCommandStorage.getActor(myCommandStorage.getActive()).setShapeIndex((int) index);
         return index;
    }

}
