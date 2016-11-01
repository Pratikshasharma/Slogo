package DisplayCommands;

import java.util.List;
import Command.DisplayCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetPenSize extends DisplayCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double size= myCommandProcess.executeList(myCommandStorage,args.get(0));
         if(size<=0){
             return Double.NaN;
         }
         myCommandStorage.getActor(myCommandStorage.getActive()).setPenSizeIndex((int) size);
         return size;
    }

}
