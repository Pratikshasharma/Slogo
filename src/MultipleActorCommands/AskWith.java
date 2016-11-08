package MultipleActorCommands;

import java.util.ArrayList;
import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Ask a set of actors meeting certain conditions to execute a set of commands.
 * Returns value of last command.
 * 
 * @author Vincent
 *
 */
public class AskWith extends MultipleActorCommand{
    /* (non-Javadoc)
     * @see Command.MultipleActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        List<Integer> originalActive=new ArrayList<Integer>(myCommandStorage.getActiveList());
        List<Integer> askActive=new ArrayList<Integer>();
        for(int i: myCommandStorage.getActiveList()){
            myCommandStorage.setActive(i);
            if(myCommandProcess.executeList(myCommandStorage,args.get(0))!=0){
                askActive.add(i);
            }
        }
        myCommandStorage.setActive(askActive);        
        double result=myCommandProcess.executeList(myCommandStorage,args.get(1));
        myCommandStorage.setActive(originalActive);
        return result;
    }

}