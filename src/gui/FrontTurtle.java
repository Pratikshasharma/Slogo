package gui;

import commandreference.Coordinates;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;


public class FrontTurtle {

	private Coordinates myCoordinates;
	private double myAngle;
	private boolean isPenUp;
	private boolean isVisible;
	private int penColorIndex;
	private int penSizeIndex;
    private Line myLine;
    private ImageView myTurtleImageView;
    private int myID;
    
    public FrontTurtle(DoubleProperty x, DoubleProperty y, double angle, int id, ImageView imageView){
    	initializeCoordinates(x, y);
    	myAngle = angle;
    	intializePenProperties();
    	myLine = new Line();
    	myTurtleImageView = imageView;
    	myID = id;
    }

	private void intializePenProperties() {
		isPenUp = false;
    	isVisible = true;
    	penColorIndex = 1;
    	penSizeIndex = 1;
	}

	private void initializeCoordinates(DoubleProperty x, DoubleProperty y) {
		myCoordinates = new Coordinates(x.get(), y.get());
    	myCoordinates.getX().bind(x);
    	myCoordinates.getY().bind(y);
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
    
    public double getAngle(){
    	return myAngle;
    }
    
    public boolean getPenStatus(){
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
