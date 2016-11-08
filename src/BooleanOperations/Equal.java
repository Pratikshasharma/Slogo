package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns integer value of boolean operation a equal b.
 * 
 * @author Vincent
 *
 */
public class Equal extends BooleanOperation{
    /* (non-Javadoc)
     * @see Command.BooleanOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {   
        double a=myCommandProcess.executeList(myCommandStorage,args.get(0));
        double b=myCommandProcess.executeList(myCommandStorage, args.get(1));
        if(boolErrorCheck(a) && boolErrorCheck(b)){
            return Double.NaN;
        }
         return (a==b)?1:0;
    }

}
