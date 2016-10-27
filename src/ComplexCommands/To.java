package ComplexCommands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class To extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        String commandName=args.get(0).getName();
        InfoNode myNode=args.get(1);
        List<String> myFunctionVariables=new ArrayList<String>();
        List<Double> testParameters=new ArrayList<Double>();
        //change command
        while(myNode!=null){
            testParameters.add((double) 0);
            myFunctionVariables.add(myNode.getName());
            myNode=myNode.next();
        }
        InfoNode commands=args.get(2);
        myCommandStorage.addFunction(commandName, myFunctionVariables, commands);
        return 1;    
    }
}
