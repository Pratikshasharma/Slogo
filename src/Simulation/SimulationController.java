package Simulation;

import java.util.ArrayList;
import java.util.List;
import Simulation.Node.InfoNode;
import Simulation.parse.*;
import commandreference.Coordinates;

public class SimulationController {
	
    private Parser simParser;
    private CommandStorage myCommandStorage;
    private CommandProcess myCommandProcess;
    
    public SimulationController () {
    	myCommandStorage=new CommandStorage();
    	myCommandProcess=new CommandProcess();
    }
    
    public void setLanguage(String language) {
    	simParser = new Parser(language);
    }
    
    public Coordinates getActorCoordinates(){
    	return myCommandStorage.getActor(1).getCoordinates();
    }
    
    public void receive(String command) {
    	String[] commandArray = command.trim().split("\\s+");
    	InfoNode test=simParser.parseText(commandArray);
    	
    	//test.printTree();
    	double result=myCommandProcess.executeList(myCommandStorage,test);
    	System.out.println(result);
    }
    
    public CommandStorage getStorage(){
        return myCommandStorage;
    }
}