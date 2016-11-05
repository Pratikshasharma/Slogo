package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
    
/**
 * Abstract class for Actor commands.
 * Determines how those commands deal with multiple active actors (execute on all).
 * Deals with error checking for setting values (want to cancel if true).
 * 
 * @author Vincent
 *
 */
public abstract class ActorCommand implements Command{
    public CommandProcess myCommandProcess;
    /* (non-Javadoc)
     * @see Command.Command#call(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        double result = Double.NaN;
        List<Integer> activeList=myCommandStorage.getActiveList();

        myCommandProcess=new CommandProcess();
        for(int i:activeList){
            myCommandStorage.setActive(i);
            result=execute(myCommandStorage, args);
        }
        myCommandStorage.setActive(activeList);
        //return result of last command or else if result is used for other operations it wont make sense (generally used for the execution part of moving turtle)
        return result;
    }
    
    /**
     * Error checkign for setting values
     * 
     * @param result
     * @param original
     * @return
     */
    public double setValErrorCheck(double result,double original){
        if(result==Double.NaN){
            return original;
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see Command.Command#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
