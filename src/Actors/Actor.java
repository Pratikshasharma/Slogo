package Actors;

import java.util.ArrayList;
import java.util.List;

import commandreference.Coordinates;
import commandreference.Turtleable;
import gui.MainGUI;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class Actor implements Turtleable {
	private final static double DEGREES_CIRCLE=360.0;
	private ImageView myImage;
	protected Coordinates coordinates;
	protected double distanceTraveled, degreesMoved;
	protected DoubleProperty myAngle;
	protected BooleanProperty penDown; 
	protected boolean visible;
	private int penColorIndex;
	private double[] penColorArray;
	private int penSizeIndex;
	private int shapeIndex;
	private List<Line> myLines;
	private DoubleProperty reset;

	public Actor (String imageFilePath) {
		init(MainGUI.TURTLE_PANE_WIDTH / 2, MainGUI.TURTLE_PANE_HEIGHT / 2, imageFilePath);
	}

	public Actor (double x, double y, String imageFilePath) {
		init(x,y, imageFilePath);
	}

	public void setPos(double x, double y){
		distanceTraveled=Math.sqrt(Math.pow((coordinates.getX().get()-x),2)+Math.pow((coordinates.getY().get()-y),2));
		coordinates.setCoordinates(x, y);
	}

	public Coordinates getCoordinates(){
		return coordinates;
	}

	public DoubleProperty getX(){
		return coordinates.getX();
	}

	public DoubleProperty getY(){
		return coordinates.getY();
	}

	public double getDistance(){
		return distanceTraveled;
	}

	public void setAngle(double angle){
		degreesMoved=angle-myAngle.get();
		myAngle.set(angle%DEGREES_CIRCLE);
	}

	public double getAngle(){
		return myAngle.get();
	}

	public double getAngleMoved(){
		return degreesMoved;
	}

	@Override
	public DoubleProperty getAngleProp(){
		return myAngle;
	}

	public void setPenStatus(boolean pen){
		penDown.set(pen);
	}

	@Override
	public BooleanProperty getPenStatus(){
		return penDown;
	}

	public void setVisibility(boolean vis){
		visible=vis;
	}

	public boolean getVisibility(){
		return visible;
	}    

	public void setPenColorIndex(int index) {
		penColorIndex = index;
	}     
	
	public int getPenColorIndex(){
		return penColorIndex;
	}
	
	public void setPenSizeIndex(int size){
		penSizeIndex = size;
	}
	
	public void setShapeIndex(int dex){
		shapeIndex = dex;
	}
	
	public int getShapeIndex(){
		return shapeIndex;
	}

	@Override
	public ImageView getImageView(){
		return myImage;
	}

	@Override
	public List<Line> getLine(){
		return myLines;
	}
	
	@Override
    public Line drawLine(double x, double y, double x1, double y1){
    	Line newLine = new Line();
    	newLine.setStartX(x);
    	newLine.setEndX(x1);
    	newLine.setStartY(y);
    	newLine.setEndY(y1);
    	newLine.setFill(Color.rgb(((int)penColorArray[0]), ((int)penColorArray[1]), ((int) penColorArray[2])));
    	newLine.setStrokeWidth(penSizeIndex);
    	myLines.add(newLine);
    	return newLine;
    }
	
	@Override
	public void clearLines(){
		myLines.clear();
	}
	
	public void setReset(){
		reset.set(reset.get()+1);
	}	
	
	public DoubleProperty getReset(){
		return reset;
	}

	private void init(double x, double y, String imageFilePath){
		coordinates = new Coordinates(x, y);
		myAngle = new SimpleDoubleProperty(0);
		myImage = new ImageView(imageFilePath);
		myImage.setFitWidth(40);
		myImage.setFitHeight(40);
		myLines = new ArrayList<Line>();
		distanceTraveled=0;
		penDown = new SimpleBooleanProperty(true);
		penSizeIndex = 1;
		visible=true;   
		reset = new SimpleDoubleProperty(0);
		penColorArray = new double[3];
		penColorArray[0] = 255/255;
		penColorArray[1] = 255/255;
		penColorArray[2] = 255/255;
	}

	public void setPenColor(int[] paletteVal) {
		penColorArray[0] = paletteVal[0];
		penColorArray[1] = paletteVal[1];
		penColorArray[2] = paletteVal[2];
	}
	
	public double[] getColorArray(){
		return penColorArray;
	}
}