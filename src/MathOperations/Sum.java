package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Sum extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        double sum=0;
        for(InfoNode parameter:args){
            sum+=myCommandProcess.executeList(myCommandStorage, ActorsChanged, parameter);
        }
        return sum;
    }

}
