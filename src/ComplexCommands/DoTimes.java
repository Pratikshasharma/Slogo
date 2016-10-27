package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

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
        myCommandStorage.setVariableMap(originalvariables);
        return result;    
    }
}
