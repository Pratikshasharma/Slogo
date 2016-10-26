package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Quotient extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
         return myCommandProcess.executeList(myCommandStorage, ActorsChanged, args.get(0))/myCommandProcess.executeList(myCommandStorage, ActorsChanged, args.get(1));
    }

}
