package DisplayCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Sets a pen color index for an actor.
 * Returns the index.
 * 
 * @author Vincent
 *
 */
public class SetPenColor extends DisplayCommandIndexCheck{
    /* (non-Javadoc)
     * @see Command.DisplayCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) { 
         double index= myCommandProcess.executeList(myCommandStorage,args.get(0));
         if(!indexErrorCheck(index,myCommandStorage.getPalette().keySet())){
             return Double.NaN;
         }
         myCommandStorage.getActor(myCommandStorage.getActive()).setPenColorIndex((int) index);
         myCommandStorage.getActor(myCommandStorage.getActive()).setPenColor(myCommandStorage.getPaletteVal((int) index));
         return index;
    }

}
