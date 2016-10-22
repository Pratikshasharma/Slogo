package Simulation;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Actors.Turtle;
import Simulation.parse.*;

public class SimulationController {
    private List<Actor> actorList;
    private ActorCommands changeActor;
    private Parser simParser;
    
    public SimulationController () {
        actorList=new ArrayList<Actor>();
        changeActor=new ActorCommands();
        createActor("Turtle");
    }
    
    public void setLanguage(String language) {
    	simParser = new Parser(language);
    }

    public void createActor(String name){
        switch(name){
            case "Turtle":
                createTurtle();
                break;
            default:
                break;
        }
    }
    
    private void createTurtle(){
        actorList.add(new Turtle());
    }
    
    public void receive(String command) {
    	String[] commandArray = command.trim().split("\\s+");
    	simParser.parseText(commandArray);
    }
}
