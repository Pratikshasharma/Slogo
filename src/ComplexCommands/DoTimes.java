package ComplexCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Execute a set of commands a certain number of times using a temporary variable.
 * Return value of last command executed.
 * 
 * @author Vincent
 *
 */
public class DoTimes extends ComplexCommandScope{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        String variable=args.get(0).getName();
        int limit =(int) myCommandProcess.executeList(myCommandStorage, args.get(1));
        for(int i=1;i<=limit;i++){
            myCommandStorage.addVariable(variable, (double) i);
            result=myCommandProcess.executeList(myCommandStorage, args.get(2));
        }
        return result;    
    }
}
