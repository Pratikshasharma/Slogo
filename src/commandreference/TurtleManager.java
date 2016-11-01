package commandreference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TurtleManager {
	
	Map<Integer, Turtleable> myTurtles;
	private int myActiveID;
	
	public TurtleManager(){
		myTurtles = new HashMap<Integer, Turtleable>();
	}
	
	public void addTurtle(int id, Turtleable turtle){
		myTurtles.put(id, turtle);
	}
	
	public Collection<Turtleable> getTurtles(){
		return myTurtles.values();
	}
	
	public Turtleable getTurtleAtIndex(int index){
		return myTurtles.get(index);
	}
	
	public void setActiveTurtle(int id){
		myActiveID = id;
	}

	public Turtleable getActiveTurtle(){
		return myTurtles.get(myActiveID);
	}
	
	public int getActiveID(){
		return myActiveID;
	}
}
