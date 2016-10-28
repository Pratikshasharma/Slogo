package commandreference;

import java.util.ArrayList;
import java.util.Collection;

public class TurtleManager {
	
	Collection<Turtleable> myTurtles;
	
	public TurtleManager(){
		myTurtles = new ArrayList<Turtleable>();
	}
	
	public void addTurtle(int id, Turtleable turtle){
		myTurtles.add(turtle);
	}
	
	public Collection<Turtleable> getTurtles(){
		return myTurtles;
	}
}
