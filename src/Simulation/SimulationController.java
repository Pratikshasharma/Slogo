package Simulation;

import Simulation.Node.InfoNode;
import Simulation.parse.*;
import commandreference.Coordinates;
import gui.MainGUI;

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
    
    public double receive(String command) {
    	String[] lines = command.split("\\r?\\n+"); //THIS ONE splits lines

//    	System.out.println("printing within receive method");
//    	for (String line : lines) {
//    		System.out.println(line);
//    	}
    	InfoNode test=simParser.parseText(lines, myCommandStorage);
    	
    	//test.printTree();
    	myCommandStorage.setKillCommands(false);
    	double result=myCommandProcess.executeList(myCommandStorage,test);
//    	System.out.println(result);
    	return result;
    }
    
    public CommandStorage getStorage(){
        return myCommandStorage;
    }
}