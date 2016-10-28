package DisplayCommands;

import java.util.List;
import Command.DisplayCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetPalette extends DisplayCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
         int[] palette=new int[]{(int)myCommandProcess.executeList(myCommandStorage,args.get(1)),(int)myCommandProcess.executeList(myCommandStorage,args.get(2)),(int)myCommandProcess.executeList(myCommandStorage,args.get(3))};
         myCommandStorage.setPaletteVal((int)index,palette);
         return index;
    }

}
