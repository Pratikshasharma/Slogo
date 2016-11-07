package MathOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;


/**
 * Returns Pi
 * 
 * @author Vincent
 *
 */
public class Pi extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.PI;
    }
}
