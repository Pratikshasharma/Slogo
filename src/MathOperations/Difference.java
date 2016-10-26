package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Difference extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<Integer> ActorsChanged,
                           List<InfoNode> args) {       
        double difference=myCommandProcess.executeList(myCommandStorage, ActorsChanged, args.get(0));
        for(int index=1;index<args.size();index++){
            difference-=myCommandProcess.executeList(myCommandStorage, ActorsChanged, args.get(index));
        }
        return difference;
    }

}
