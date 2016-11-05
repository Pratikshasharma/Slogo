package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Execute a for loop on a set of commands with defined temporary variable, start and end, and increment.
 * Returns value of last command executed.
 * 
 * @author Vincent
 *
 */
public class For extends ComplexCommand{
    /* (non-Javadoc)
     * @see Command.ComplexCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        Map<String,Double> originalvariables= new HashMap<String,Double>(myCommandStorage.getVariableMap());
        //store to keep original map-dealing with temporary variables
        String variable=args.get(0).getName();
        int start=(int) myCommandProcess.executeList(myCommandStorage, args.get(1));
        int end=(int) myCommandProcess.executeList(myCommandStorage, args.get(2));
        int increment=(int) myCommandProcess.executeList(myCommandStorage, args.get(3));
        for(int i=start;i<=end;i+=increment){
            myCommandStorage.addVariable(variable, (double) i);
            result=myCommandProcess.executeList(myCommandStorage, args.get(4));
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
