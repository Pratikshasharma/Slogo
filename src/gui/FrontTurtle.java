package gui;

import java.util.ArrayList;
import java.util.List;

import commandreference.Coordinates;
import commandreference.Turtleable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class FrontTurtle {

    private Coordinates myCoordinates;
    private DoubleProperty myAngle;
    private boolean isPenUp;
    private boolean isVisible;
    private int penColorIndex;
    private int penSizeIndex;
    private List<Line> myLines;
    private ImageView myTurtleImageView;
    private int myID;

    public FrontTurtle(int id, Turtleable turtle){
        initializeCoordinates(turtle.getX(), turtle.getY());
        bindAngle(turtle);
        intializePenProperties(turtle);
        myLines = new ArrayList<Line>();
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
        myAngle = new SimpleDoubleProperty();
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

    public List<Line> getLine(){
        return myLines;
    }
    
    public void addLine(Line l){
    	myLines.add(l);
    }

    public ImageView getImageView(){
        return myTurtleImageView;
    }
}
