package MathOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns the product of several values.
 * 
 * @author Vincent
 *
 */
public class Product extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double product=1;
        for(InfoNode parameter:args){
            product*=myCommandProcess.executeList(myCommandStorage, parameter);
        }
        return product;
    }

}
