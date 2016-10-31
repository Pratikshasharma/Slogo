package ComplexCommands;

import java.util.ArrayList;
import java.util.List;
import Command.ComplexCommand;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class MakeUserInstruction extends ComplexCommand{
    @Override
    public double execute (CommandStorage myCommandStorage,
                           List<InfoNode> args) {       
        String commandName=args.get(0).getName();       
        List<String> myFunctionVariables=new ArrayList<String>();
        //change command for no parameters and change amount
        for(int i=1;i<args.size()-1;i++){
            myFunctionVariables.add(args.get(i).getName());
        }
        InfoNode commands=args.get(args.size()-1);
        myCommandStorage.addFunction(commandName, myFunctionVariables, commands);
        return 1;    
    }
}
