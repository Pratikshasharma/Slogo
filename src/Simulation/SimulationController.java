package Simulation;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Actors.Turtle;
import Simulation.Node.InfoNode;
import Simulation.parse.*;
import commandreference.Coordinates;

public class SimulationController {
	
//    private List<Actor> actorList;
    private Actor myActor;
    private Parser simParser;
    private CommandExecute myCommandExecute;
    
    public SimulationController () {
//        actorList=new ArrayList<Actor>();
//      createActor("Turtle");
    	myActor = new Turtle();
    	myCommandExecute=new CommandExecute(myActor);
    }
    
    public void setLanguage(String language) {
    	simParser = new Parser(language);
    }

//    public void createActor(String name){
//        switch(name){
//            case "Turtle":
//                createTurtle();
//                break;
//            default:
//                break;
//        }
//    }
    
    public Coordinates getActorCoordinates(){
    	return myActor.getCoordinates();
    }
    
    public void receive(String command) {
    	String[] commandArray = command.trim().split("\\s+");
    	InfoNode test=simParser.parseText(commandArray);

    	List<Double> results=myCommandExecute.executeList(test);
    	for(double r1:results){
            System.out.println(r1);
    	}
    }

//    private void createTurtle(){
//        actorList.add(new Turtle());
//    }
}
