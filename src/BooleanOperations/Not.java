package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns integer value of boolean operation not a.
 * 
 * @author Vincent
 *
 */
public class Not extends BooleanOperation{
    /* (non-Javadoc)
     * @see Command.BooleanOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=myCommandProcess.executeList(myCommandStorage, args.get(0));
        if(boolErrorCheck(result)){
            return Double.NaN;
        }
         return (result==0)?1:0;
    }

}
