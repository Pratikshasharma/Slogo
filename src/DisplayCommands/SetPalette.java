package DisplayCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Maps a color index to a color rgb values in the palette.
 * Returns the index.
 * 
 * @author Vincent
 *
 */
public class SetPalette extends DisplayCommandBoundsCheck{
    private static final double RGB_UPPER_BOUND=255;
    /* (non-Javadoc)
     * @see Command.DisplayCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
         int[] palette=new int[]{(int)myCommandProcess.executeList(myCommandStorage,args.get(1)),(int)myCommandProcess.executeList(myCommandStorage,args.get(2)),(int)myCommandProcess.executeList(myCommandStorage,args.get(3))};
         if(!boundsErrorCheck(palette[0],0,RGB_UPPER_BOUND)||!boundsErrorCheck(palette[1],0,RGB_UPPER_BOUND)||!boundsErrorCheck(palette[2],0,RGB_UPPER_BOUND)||index<=0){ 
             return Double.NaN;
         }
         myCommandStorage.setPaletteVal((int)index,palette);
         return index;
    }

}
