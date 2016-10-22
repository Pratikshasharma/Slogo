package commandreference;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Coordinates {

	private DoubleProperty myX;
	private DoubleProperty myY;

	public Coordinates(){
		this(0, 0);
	}

	public Coordinates(double x, double y){
		myX = new SimpleDoubleProperty(x);
		myY = new SimpleDoubleProperty(y);
	}
	
	public void setX(double x){
		myX.set(x);
	}
	
	public void setY(double y){
		myY.set(y);
	}
	
	public DoubleProperty getX(){
		return myX;
	}
	
	public DoubleProperty getY(){
		return myY;
	}
}
