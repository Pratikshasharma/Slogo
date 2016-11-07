package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Execute a set of commands a certain number of times.
 * Return value of last command executed.
 * 
 * @author Vincent
 *
 */
public class Repeat extends ComplexCommandScope{
    private static final String LOOP_VARIABLE=":repcount";
    /* (non-Javadoc)
     * @see Command.ComplexCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        //store to keep original map-dealing with temporary variables
        int limit = (int) myCommandProcess.executeList(myCommandStorage, args.get(0));
        for(int i=1;i<=limit;i++){
            myCommandStorage.addVariable(LOOP_VARIABLE, (double) i);
            result=myCommandProcess.executeList(myCommandStorage, args.get(1));
        }
        return result;    
    }
}
