package ComplexCommands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class Make extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        double result=0;
        String variable=args.get(0).getName();
        result=myCommandProcess.executeList(myCommandStorage, args.get(1));
        myCommandStorage.addVariable(variable, result);
        return result;    
    }
}
