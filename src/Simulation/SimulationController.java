package Simulation;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Actors.Turtle;

public class SimulationController {
    private List<Actor> actorList;
    private ActorCommands changeActor;
    
    public SimulationController () {
        actorList=new ArrayList<Actor>();
        changeActor=new ActorCommands();
        createActor("Turtle");
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
}
