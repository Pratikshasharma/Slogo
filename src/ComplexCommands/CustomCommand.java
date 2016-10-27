package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class CustomCommand extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        String commandName=args.get(0).getName();
        args.remove(0);
        List<String> functionvariables=myCommandStorage.getFunctionVariables(commandName);
        Map<String,Double> originalvariables= new HashMap<String,Double>(myCommandStorage.getVariableMap());
         
        for(int index=0;index<args.size();index++){
            InfoNode argNode=args.get(index);
            myCommandStorage.addVariable(functionvariables.get(index), myCommandProcess.executeList(myCommandStorage,argNode));
        }
        InfoNode commands=myCommandStorage.getFunction(commandName);
        double result = myCommandProcess.executeList(myCommandStorage,commands);
        myCommandStorage.setVariableMap(originalvariables);
        return result;
    }
}
