package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public abstract class MathOperation implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }

    public double mathValErrorCheck(double val, double exception){
        if(val==exception){
            return Double.NaN;
        }
        return val;
    }
    
    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
