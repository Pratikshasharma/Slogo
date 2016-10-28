package commandreference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gui.FrontTurtle;

public class TurtleManager {
	
	Map<Integer, FrontTurtle> myTurtles = new HashMap<Integer, FrontTurtle>();
	private FrontTurtle myActiveTurtle;
	private int myActiveID;
	
	public TurtleManager(){
		myTurtles = new HashMap<Integer, FrontTurtle>();
	}
	
	public void addTurtle(int id, Turtleable turtle){
		myTurtles.put(id, new FrontTurtle(id, turtle));
	}
	
	public Collection<FrontTurtle> getTurtles(){
		return myTurtles.values();
	}
	
	public FrontTurtle getTurtleAtIndex(int index){
		return myTurtles.get(index);
	}
	
	public void setActiveTurtle(int id){
		myActiveTurtle = myTurtles.get(id);
		myActiveID = id;
	}
	
	public FrontTurtle getActiveTurtle(){
		return myActiveTurtle;
	}
	
	public int getActiveID(){
		return myActiveID;
	}
}
