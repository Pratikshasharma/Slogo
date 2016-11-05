package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return a raised to the power of b
 * 
 * @author Vincent
 *
 */
public class Power extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.pow(myCommandProcess.executeList(myCommandStorage, args.get(0)),
                         myCommandProcess.executeList(myCommandStorage, args.get(1)));
    }

}
