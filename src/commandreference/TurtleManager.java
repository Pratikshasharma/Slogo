package commandreference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import gui.FrontTurtle;

public class TurtleManager {
	
//	Map<Integer, FrontTurtle> myTurtles;
	Map<Integer, Turtleable> myNewTurtles;
	private Turtleable myActiveTurtle;
	private int myActiveID;
	
	public TurtleManager(){
//		myTurtles = new HashMap<Integer, FrontTurtle>();
		myNewTurtles = new HashMap<Integer, Turtleable>();
	}
	
	public void addTurtle(int id, Turtleable turtle){
//		myTurtles.put(id, new FrontTurtle(id, turtle));
		myNewTurtles.put(id, turtle);
	}
	
//	public Collection<FrontTurtle> getTurtles(){
//		return myTurtles.values();
//	}
	
	public Collection<Turtleable> getNewTurtles(){
		return myNewTurtles.values();
	}
	
//	public FrontTurtle getTurtleAtIndex(int index){
//		return myTurtles.get(index);
//	}
	
	public Turtleable getTurtleAtIndex(int index){
		return myNewTurtles.get(index);
	}
	
	public void setActiveTurtle(int id){
//		myActiveTurtle = myTurtles.get(id);
		myActiveTurtle = myNewTurtles.get(id);
		myActiveID = id;
	}
	
//	public FrontTurtle getActiveTurtle(){
//		return myActiveTurtle;
//	}
	
	public Turtleable getActiveTurtle(){
		return myActiveTurtle;
	}
	
	public int getActiveID(){
		return myActiveID;
	}
}
