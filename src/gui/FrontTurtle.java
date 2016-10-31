package gui;

import java.util.ArrayList;
import java.util.List;
import commandreference.Coordinates;
import commandreference.Turtleable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    private Double lineWidth = 1.0;
    private Paint lineColor = Color.BLACK;
    

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
    
    public Line drawLine(double x, double y, double x1, double y1){
    	Line newLine = new Line();
    	newLine.setStartX(x);
    	newLine.setEndX(x1);
    	newLine.setStartY(y);
    	newLine.setEndY(y1);
    	newLine.setFill(lineColor);
    	newLine.setStrokeWidth(lineWidth);
    	myLines.add(newLine);
    	return newLine;
    }
    
    public void setLineColor(Paint colorValue ){
        lineColor = colorValue;
    }
    
    public void setLineWidth(Double widthValue){
        lineWidth = widthValue;
    }
}
