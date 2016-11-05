package ComplexCommands;

import java.util.List;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Execute an if-else statement that runs set of commands if true and another set of commands if false.
 * Returns value of last command.
 * 
 * @author Vincent
 *
 */
public class IfElse extends ComplexCommand{
    /* (non-Javadoc)
     * @see Command.ComplexCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        if(myCommandProcess.executeList(myCommandStorage, args.get(0))!=0){
            result=myCommandProcess.executeList(myCommandStorage, args.get(1));
        }
        else{
            result=myCommandProcess.executeList(myCommandStorage, args.get(2 ));
        }
        return result;    
    }
}
