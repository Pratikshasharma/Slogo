package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Product extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        double product=1;
        for(InfoNode parameter:args){
            product*=myCommandProcess.executeList(myCommandStorage, ActorsChanged, parameter);
        }
        return product;
    }

}
