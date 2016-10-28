package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Equal extends BooleanOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {   
        double a=myCommandProcess.executeList(myCommandStorage,args.get(0));
        double b=myCommandProcess.executeList(myCommandStorage, args.get(1));
        if(boolErrorCheck(a) && boolErrorCheck(b)){
            return Double.NaN;
        }
         return (a==b)?1:0;
    }

}
