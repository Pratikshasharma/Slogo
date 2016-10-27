package BooleanOperations;

import java.util.List;
import Command.BooleanOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Or extends BooleanOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double or=0;
        for(InfoNode parameter:args){
            or=(or!=0 || myCommandProcess.executeList(myCommandStorage, parameter)!=0)?1:0;
        }
        return or;    
    }
}
