package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Sine extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.sin(myCommandProcess.executeList(myCommandStorage, args.get(0)));
    }

}
