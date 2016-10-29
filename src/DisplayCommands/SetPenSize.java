package DisplayCommands;

import java.util.List;
import Command.DisplayCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetPenSize extends DisplayCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
      /*   if(!indexErrorCheck(index,myCommandStorage.getPenSizeMap().keySet())){
             return Double.NaN;
         }*/
         myCommandStorage.getActor(myCommandStorage.getActive()).setPenSizeIndex((int) index);
         return index;
    }

}
