package Simulation;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Actors.Turtle;
import Simulation.parse.*;
import commandreference.Coordinates;

public class SimulationController {
	
//    private List<Actor> actorList;
	private Actor myActor;
    private ActorCommands changeActor;
    private Parser simParser;
    
    public SimulationController () {
//        actorList=new ArrayList<Actor>();
    	myActor = new Turtle();
        changeActor=new ActorCommands();
        createActor("Turtle");
    }
    
    public void setLanguage(String language) {
    	simParser = new Parser(language);
    }

    public void createActor(String name){
        switch(name){
            case "Turtle":
//                createTurtle();
                break;
            default:
                break;
        }
    }
    
    public Coordinates getActorCoordinates(){
    	return myActor.getCoordinates();
    }
    
    public void receive(String command) {
    	String[] commandArray = command.trim().split("\\s+");
    	simParser.parseText(commandArray);
    }

//    private void createTurtle(){
//        actorList.add(new Turtle());
//    }
}
