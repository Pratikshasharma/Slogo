package commandreference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TurtleManager {
	
	Map<Integer, Turtleable> myTurtles = new HashMap<Integer, Turtleable>();
	
	private Turtleable myActiveTurtle;
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
	
	public void setActiveTurtle(int id, Turtleable turtle){
		myActiveTurtle = turtle;
		myActiveID = id;
	}
	
	public Turtleable getActiveTurtle(){
		return myActiveTurtle;
	}
	
	public int getActiveID(){
		return myActiveID;
	}
}
