package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Repeat extends ComplexCommand{
    private static final String LOOP_VARIABLE=":repcount";
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        Map<String,Double> originalvariables= new HashMap<String,Double>(myCommandStorage.getVariableMap());
        //store to keep original map-dealing with temporary variables
        int limit = (int) myCommandProcess.executeList(myCommandStorage, args.get(0));
        for(int i=1;i<=limit;i++){
            myCommandStorage.addVariable(LOOP_VARIABLE, (double) i);
            result=myCommandProcess.executeList(myCommandStorage, args.get(1));
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
