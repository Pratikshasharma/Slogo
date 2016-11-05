package Simulation;

import Simulation.Node.InfoNode;
import Simulation.parse.*;
import commandreference.Coordinates;
import gui.MainGUI;

/**
 * The controller of the back end. Communicates with AppController as the point of communication for the backend.
 * This class makes the calls to allow parsing and execution when commands are received.
 * 
 * Calls CommandStorage, CommandProcess, Parser.
 * Called by AppController, front end processes that want to access CommandStorage.
 * 
 * @author Vincent
 *
 */
public class SimulationController {	
    private Parser simParser;
    private CommandStorage myCommandStorage;
    private CommandProcess myCommandProcess;
    
    /**
     * Constructor
     */
    public SimulationController () {
    	myCommandStorage=new CommandStorage();
    	myCommandProcess=new CommandProcess();
    }
    
    /**
     * Initiate the parser based on what language is used.
     * 
     * @param language
     */
    public void setLanguage(String language) {
    	simParser = new Parser(language);
    }
    
    /**
     * Get coordinates of 1st actor (added by front end).
     * 
     * @return
     */
    public Coordinates getActorCoordinates(){
    	return myCommandStorage.getActor(1).getCoordinates();
    }
    
    /**
     * Send in a command in the form of a string and let it be parsed and executed.
     * Returns the value of the last command executed.
     * 
     * @param command
     * @return
     */
    public double receive(String command) {
    	String[] lines = command.split("\\r?\\n+"); //THIS ONE splits lines

    	myCommandStorage.setKillCommands(false);
    	InfoNode test=simParser.parseText(lines, myCommandStorage);
    	
    	simParser.printTree(test);
    	double result=myCommandProcess.executeList(myCommandStorage,test);
    	return result;
    }
    
    /**
     * Return the CommandStorage holding all the data.
     * 
     * @return
     */
    public CommandStorage getStorage(){
        return myCommandStorage;
    }
}