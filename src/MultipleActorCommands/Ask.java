package MultipleActorCommands;

import java.util.ArrayList;
import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Ask a set of actors to execute a set of commands.
 * Returns value of last command.
 * 
 * @author Vincent
 *
 */
public class Ask extends MultipleActorCommand{
    /* (non-Javadoc)
     * @see Command.MultipleActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {
        List<Integer> originalActive=new ArrayList<Integer>(myCommandStorage.getActiveList());
        Tell tell=new Tell();
        List<InfoNode> tellArgs=new ArrayList<InfoNode>(args);//assume create if dont exist already
        tellArgs.remove(tellArgs.size()-1);
        tell.call(myCommandStorage, tellArgs);
        double result=myCommandProcess.executeList(myCommandStorage,args.get(args.size()-1));
        myCommandStorage.setActive(originalActive);
        return result;
    }

}