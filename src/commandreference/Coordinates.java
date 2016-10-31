package commandreference;

import java.util.Observable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Coordinates extends Observable {

	private DoubleProperty myX;
	private DoubleProperty myY;
	private boolean isReadyForChange = false;;
	
	public Coordinates(){
		this(0, 0);
	}

	public Coordinates(double x, double y){
		myX = new SimpleDoubleProperty(x);
		myY = new SimpleDoubleProperty(y);
	}
	
//	public void setX(double x){
//		myX.set(x);
//	}
//	
//	public void setY(double y){
//		myY.set(y);
//	}
	
	public void setCoordinates(double x, double y){
		myY.set(y);
		myX.set(x);
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	public DoubleProperty getX(){
		return myX;
	}
	
	public DoubleProperty getY(){
		return myY;
	}

	public boolean isReadyForChange(){
		return isReadyForChange;
	}
	
	public void setReadForChange(boolean b){
		isReadyForChange = b;
	}
}
