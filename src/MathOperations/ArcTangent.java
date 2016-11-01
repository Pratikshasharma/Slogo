package MathOperations;

import java.util.List;
import Command.MathOperation;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class ArcTangent extends MathOperation{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
         return Math.atan(myCommandProcess.executeList(myCommandStorage, args.get(0)))*180*Math.PI;
    }

}
