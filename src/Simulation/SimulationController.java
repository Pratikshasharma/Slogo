package Simulation;

import java.util.ArrayList;
import java.util.List;
import Actors.Actor;
import Actors.Turtle;
import commandreference.Coordinates;

public class SimulationController {
	
//    private List<Actor> actorList;
	private Actor myActor;
    private ActorCommands changeActor;
    
    public SimulationController () {
//        actorList=new ArrayList<Actor>();
    	myActor = new Turtle();
        changeActor=new ActorCommands();
        createActor("Turtle");
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
    
    
//    private void createTurtle(){
//        actorList.add(new Turtle());
//    }
}
