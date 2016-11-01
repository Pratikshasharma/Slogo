package MultipleActorCommands;

import java.util.ArrayList;
import java.util.List;
import Command.MultipleActorCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class AskWith extends MultipleActorCommand{
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