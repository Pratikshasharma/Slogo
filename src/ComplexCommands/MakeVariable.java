package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Makes variable and returns value of variable.
 * 
 * @author Vincent
 *
 */
public class MakeVariable extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        String variable=args.get(0).getName();
        result=myCommandProcess.executeList(myCommandStorage, args.get(1));
        myCommandStorage.addVariable(variable, result);
        return result;    
    }
}
