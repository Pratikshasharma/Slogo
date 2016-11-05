package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return the negative value of a number
 * 
 * @author Vincent
 *
 */
public class Minus extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return -myCommandProcess.executeList(myCommandStorage, args.get(0));
    }

}
