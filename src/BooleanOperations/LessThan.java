package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class LessThan extends BooleanOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
         return (myCommandProcess.executeList(myCommandStorage, ActorsChanged, args.get(0))<myCommandProcess.executeList(myCommandStorage, ActorsChanged, args.get(1)))?1:0;
    }

}
