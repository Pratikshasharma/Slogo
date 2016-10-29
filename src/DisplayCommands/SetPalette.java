package DisplayCommands;

import java.util.List;
import Command.DisplayCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class SetPalette extends DisplayCommand{
    private static final double RGB_UPPER_BOUND=256;
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
         int[] palette=new int[]{(int)myCommandProcess.executeList(myCommandStorage,args.get(1)),(int)myCommandProcess.executeList(myCommandStorage,args.get(2)),(int)myCommandProcess.executeList(myCommandStorage,args.get(3))};
         if(!boundsErrorCheck(palette[0],0,RGB_UPPER_BOUND)||!boundsErrorCheck(palette[1],0,RGB_UPPER_BOUND)||!boundsErrorCheck(palette[2],0,RGB_UPPER_BOUND)){ 
             return Double.NaN;
         }
         myCommandStorage.setPaletteVal((int)index,palette);
         return index;
    }

}
