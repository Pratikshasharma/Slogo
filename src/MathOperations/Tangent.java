package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return Tangent in degrees
 * 
 * @author Vincent
 *
 */
public class Tangent extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
         return Math.tan(myCommandProcess.executeList(myCommandStorage, args.get(0)));
    }

}
