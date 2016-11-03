package Command;

import java.util.List;
import Simulation.CommandProcess;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public abstract class BooleanOperation implements Command{
    public CommandProcess myCommandProcess;
    @Override
    public double call (CommandStorage myCommandStorage,  List<InfoNode> args) {
        myCommandProcess=new CommandProcess();
        return execute(myCommandStorage, args);
    }
    
    public boolean boolErrorCheck(double result){
        if(result==Double.NaN){
            return true;
        }
        return false;
    }

    @Override
    public abstract double execute (CommandStorage myCommandStorage,  List<InfoNode> args);

}
