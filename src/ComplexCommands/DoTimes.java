package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Execute a set of commands a certain number of times using a temporary variable.
 * Return value of last command executed.
 * 
 * @author Vincent
 *
 */
public class DoTimes extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        Map<String,Double> originalvariables= new HashMap<String,Double>(myCommandStorage.getVariableMap());
        //store to keep original map-dealing with temporary variables
        String variable=args.get(0).getName();
        int limit =(int) myCommandProcess.executeList(myCommandStorage, args.get(1));
        for(int i=1;i<=limit;i++){
            myCommandStorage.addVariable(variable, (double) i);
            result=myCommandProcess.executeList(myCommandStorage, args.get(2));
        }
        Map<String,Double> tempmap= new HashMap<String,Double>(myCommandStorage.getVariableMap());
        Map<String,Double> updatedvariables= new HashMap<String,Double>();
        for(String var:originalvariables.keySet()){
            updatedvariables.put(var,tempmap.get(var));
        }
        myCommandStorage.setVariableMap(updatedvariables);

        return result;    
    }
}
