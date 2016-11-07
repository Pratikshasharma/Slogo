package MathOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return sum of several values
 * 
 * @author Vincent
 *
 */
public class Sum extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double sum=0;
        for(InfoNode parameter:args){
            sum+=myCommandProcess.executeList(myCommandStorage, parameter);
        }
        return sum;
    }

}
