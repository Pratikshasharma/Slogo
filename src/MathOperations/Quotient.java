package MathOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return a divided by b.
 * 
 * @author Vincent
 *
 */
public class Quotient extends MathOperationValueErrorCheck{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {  
        //can't divide by 0
         return myCommandProcess.executeList(myCommandStorage, args.get(0))/mathValErrorCheck(myCommandProcess.executeList(myCommandStorage, args.get(1)),0);
    }

}
