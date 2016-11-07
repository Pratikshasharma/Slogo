package MathOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return Cosine in degrees
 * 
 * @author Vincent
 *
 */
public class Cosine extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.cos(myCommandProcess.executeList(myCommandStorage, args.get(0))/TO_RADIANS);
    }

}
