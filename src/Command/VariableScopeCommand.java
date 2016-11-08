/*
 * This entire file is part of my code masterpiece.
 * Vincent Zhang
 * This is part of the second level of my hierarchy and provides the call for commands that use local variables.
 * See CommandInterface for more complete description.
 * 
 */

package Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.BasicCommand;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Abstract class for commands that needs to store previous variable names to deal with scope/local variables.
 * 
 * @author Vincent
 *
 */
public abstract class VariableScopeCommand implements CommandInterface{
    public CommandProcess myCommandProcess;

    /* (non-Javadoc)
     * @see Command.CommandInterface#call(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        Map<String,Double> originalvariables= new HashMap<String,Double>(myCommandStorage.getVariableMap());
        double result= execute(myCommandStorage, args);
        Map<String,Double> updatedvariables= new HashMap<String,Double>();
        for(String var:originalvariables.keySet()){
            updatedvariables.put(var,myCommandStorage.getVariable(var));
        }
        myCommandStorage.setVariableMap(updatedvariables);
        return result;
    }
    
    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage, List<InfoNode> args);
}
