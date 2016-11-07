package BooleanOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns integer value of boolean operation a not equal b.
 * 
 * @author Vincent
 *
 */
public class NotEqual extends BooleanOperationCompare{
    /* (non-Javadoc)
     * @see Command.BooleanOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {     
        return (myCommandProcess.executeList(myCommandStorage,args.get(0))!=myCommandProcess.executeList(myCommandStorage, args.get(1)))?1:0;
    }

}
