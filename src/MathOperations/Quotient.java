package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Quotient extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {  
        //can't divide by 0
         return myCommandProcess.executeList(myCommandStorage, args.get(0))/mathValErrorCheck(myCommandProcess.executeList(myCommandStorage, args.get(1)),0);
    }

}
