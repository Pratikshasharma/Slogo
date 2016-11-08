package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return Sine in degrees
 * 
 * @author Vincent
 *
 */
public class Sine extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.sin(myCommandProcess.executeList(myCommandStorage, args.get(0))/180*Math.PI);
    }

}
