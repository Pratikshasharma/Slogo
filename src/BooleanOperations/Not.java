package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Not extends BooleanOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=myCommandProcess.executeList(myCommandStorage, args.get(0));
        if(boolErrorCheck(result)){
            return Double.NaN;
        }
         return (result==0)?1:0;
    }

}
