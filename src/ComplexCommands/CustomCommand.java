package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Executes a previously defined user function.
 * Returns value of last command in the custom command execution.
 * 
 * @author Vincent
 *
 */
public class CustomCommand extends ComplexCommandScope{
    /* (non-Javadoc)
     * @see Command.ComplexCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        String commandName=args.get(0).getName();
        args.remove(0);
        List<String> functionvariables=myCommandStorage.getFunctionVariables(commandName);         
        for(int index=0;index<args.size();index++){
            InfoNode argNode=args.get(index);
            myCommandStorage.addVariable(functionvariables.get(index), myCommandProcess.executeList(myCommandStorage,argNode));
        }
        InfoNode commands=myCommandStorage.getFunction(commandName);
        return myCommandProcess.executeList(myCommandStorage,commands);
    }
}
