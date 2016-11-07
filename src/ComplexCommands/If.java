package ComplexCommands;

import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Execute an if statement that runs set of commands if true.
 * Returns value of last command or 0 otherwise.
 * 
 * @author Vincent
 *
 */
public class If extends ComplexCommand{
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
        return result;    
    }
}
