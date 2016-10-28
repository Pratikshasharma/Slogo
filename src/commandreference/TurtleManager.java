package commandreference;

import java.util.ArrayList;
import java.util.Collection;

import gui.FrontTurtle;

public class TurtleManager {

	Collection<FrontTurtle> myTurtles;
	
	public TurtleManager(){
		myTurtles = new ArrayList<FrontTurtle>();
	}
	
	public void addTurtle(int id, Turtleable turtle){
		FrontTurtle newTurtle = new FrontTurtle(turtle.getX(), turtle.getY(), turtle.getAngle(), id, turtle.getImageView());
		myTurtles.add(newTurtle);
	}
	
	public Collection<FrontTurtle> getTurtles(){
		return myTurtles;
	}
}
