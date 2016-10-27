package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class And extends BooleanOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,                           
                           List<InfoNode> args) {       
        double and=1;
        for(InfoNode parameter:args){
            and=(and!=0 && myCommandProcess.executeList(myCommandStorage, parameter)!=0)?1:0;
        }
        return and;    
    }
}
