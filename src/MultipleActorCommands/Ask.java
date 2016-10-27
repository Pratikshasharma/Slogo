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
        double activeactorindex=0;
        List<Integer> myActiveList=new ArrayList<Integer>();
        for(InfoNode argNode:args){
            activeactorindex= myCommandProcess.executeList(myCommandStorage, argNode);
            myActiveList.add((int) activeactorindex);
            myCommandStorage.addNewActors((int) activeactorindex);
        }
        myCommandStorage.setActive(myActiveList);
        return activeactorindex;
    }

}