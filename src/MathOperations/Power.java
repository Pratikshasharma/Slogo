package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Power extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.pow(myCommandProcess.executeList(myCommandStorage, args.get(0)),
                         myCommandProcess.executeList(myCommandStorage, args.get(1)));
    }

}
