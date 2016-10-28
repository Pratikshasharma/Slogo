package gui;

import commandreference.Coordinates;
import commandreference.Turtleable;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;


public class FrontTurtle {

	private Coordinates myCoordinates;
	private DoubleProperty myAngle;
	private boolean isPenUp;
	private boolean isVisible;
	private int penColorIndex;
	private int penSizeIndex;
    private Line myLine;
    private ImageView myTurtleImageView;
    private int myID;
    
    public FrontTurtle(int id, Turtleable turtle){
    	initializeCoordinates(turtle.getX(), turtle.getY());
    	myAngle = turtle.getAngleProp();
    	intializePenProperties(turtle);
    	myLine = new Line();
    	myTurtleImageView = turtle.getImageView();
    	myID = id;
    }

	private void intializePenProperties(Turtleable turtle) {
		isPenUp = turtle.getPenStatus();
    	isVisible = true;
    	penColorIndex = turtle.getPenColorIndex();
    	penSizeIndex = turtle.getPenSizeIndex();
	}

	private void initializeCoordinates(DoubleProperty x, DoubleProperty y) {
		myCoordinates = new Coordinates(x.get(), y.get());
    	myCoordinates.getX().bind(x);
    	myCoordinates.getY().bind(y);
	}
	
	private void bindAngle(Turtleable turtle){
		myAngle.bind(turtle.getAngleProp());
	}
    
    public Coordinates getCoordinates(){
    	return myCoordinates;
    }
    
    public void setCoordinates(double x, double y){
    	myCoordinates.setX(x);
    	myCoordinates.setY(y);
    }
    
    public int getID(){
    	return myID;
    }
    
    public DoubleProperty getAngle(){
    	return myAngle;
    }
    
    public boolean isPenUp(){
    	return isPenUp;
    }
   
    public boolean getVisibility(){
    	return isVisible;
    }
    
    public int getPenColorIndex(){
    	return penColorIndex;
    }
    
    public int getPenSizeIndex(){
    	return penSizeIndex;
    }
    
    public Line getLine(){
    	return myLine;
    }
    
    public ImageView getImageView(){
    	return myTurtleImageView;
    }
}
