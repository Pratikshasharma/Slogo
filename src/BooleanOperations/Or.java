package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Returns integer value of boolean operation or for several values.
 * 
 * @author Vincent
 *
 */
public class Or extends BooleanOperation{
    /* (non-Javadoc)
     * @see Command.BooleanOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double or=0;
        for(InfoNode parameter:args){
            double result=myCommandProcess.executeList(myCommandStorage, parameter);
            if(boolErrorCheck(result)){
                return Double.NaN;
            }
            or=(or!=0 || result!=0)?1:0;
        }
        return or;    
    }
}
