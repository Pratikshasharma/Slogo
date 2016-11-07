package MathOperations;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Return difference between a and several values.
 * 
 * @author Vincent
 *
 */
public class Difference extends MathOperation{
    /* (non-Javadoc)
     * @see Command.MathOperation#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double difference=myCommandProcess.executeList(myCommandStorage, args.get(0));
        for(int index=1;index<args.size();index++){
            difference-=myCommandProcess.executeList(myCommandStorage, args.get(index));
        }
        return difference;
    }

}
