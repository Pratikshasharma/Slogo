package DisplayCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Set color index for background.
 * Return the index.
 * 
 * @author Vincent
 *
 */
public class SetBackground extends DisplayCommandIndexCheck{
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
         myCommandStorage.setBackgroundIndex((int)index);
         return index;
    }

}
