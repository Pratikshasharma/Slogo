package BooleanOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns integer value of boolean operation and of several values.
 * 
 * @author Vincent
 *
 */
public class And extends BooleanOperation{
    /* (non-Javadoc)
     * @see Command.BooleanOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,                           
                           List<InfoNode> args) {       
        double and=1;
        for(InfoNode parameter:args){
            double result=myCommandProcess.executeList(myCommandStorage, parameter);
            if(boolErrorCheck(result)){
                return Double.NaN;
            }
            and=(and!=0 && result!=0)?1:0;
        }
        return and;    
    }
}
