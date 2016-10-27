package ComplexCommands;

import java.util.List;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class IfElse extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        if(myCommandProcess.executeList(myCommandStorage, args.get(0))!=0){
            result=myCommandProcess.executeList(myCommandStorage, args.get(1));
        }
        else{
            result=myCommandProcess.executeList(myCommandStorage, args.get(2 ));
        }
        return result;    
    }
}
