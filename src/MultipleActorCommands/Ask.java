package MultipleActorCommands;

import java.util.ArrayList;
import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Ask extends MultipleActorCommand{
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