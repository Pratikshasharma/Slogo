package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return the natural log
 * 
 * @author Vincent
 *
 */
public class NaturalLog extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {      
        //can't take natural log of less than 0 (0 will return - inf, less than will return NaN so need to check for 0)
         return Math.log(mathValErrorCheck(myCommandProcess.executeList(myCommandStorage, args.get(0)),0));
    }

}
