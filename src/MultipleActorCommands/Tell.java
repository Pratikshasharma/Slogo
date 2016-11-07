package MultipleActorCommands;

import java.util.ArrayList;
import java.util.List;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

/**
 * Sets set of actors to be active.
 * Returns index of last actor set active.
 * 
 * @author Vincent
 *
 */
public class Tell extends MultipleActorCommand{
    /* (non-Javadoc)
     * @see Command.MultipleActorCommand#execute(Simulation.CommandStorage, java.util.List)
     */
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