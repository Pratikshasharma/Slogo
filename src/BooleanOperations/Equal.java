package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Equal extends BooleanOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return (myCommandProcess.executeList(myCommandStorage,args.get(0))==myCommandProcess.executeList(myCommandStorage, args.get(1)))?1:0;
    }

}
