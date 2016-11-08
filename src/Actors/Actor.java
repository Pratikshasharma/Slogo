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

/**
 * Actor object that contains all the infromation an individual actor needs to store.
 * 
 * Used in backend to store the values that need to be acted upon. 
 * Values are received in front end to allow for display.
 * 
 * @author Vincent, Ted
 *
 */
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

	/**
	 * Constructor using image path.
	 * 
	 * @param imageFilePath
	 */
	public Actor (String imageFilePath) {
		init(MainGUI.TURTLE_PANE_WIDTH / 2, MainGUI.TURTLE_PANE_HEIGHT / 2, imageFilePath);
	}

	/**
	 * Constructor using coordinates and image path.
	 * 
	 * @param x
	 * @param y
	 * @param imageFilePath
	 */
	public Actor (double x, double y, String imageFilePath) {
		init(x,y, imageFilePath);
	}

	/**
	 * Set coordinates of the actor.
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(double x, double y){
		distanceTraveled=Math.sqrt(Math.pow((coordinates.getX().get()-x),2)+Math.pow((coordinates.getY().get()-y),2));
		coordinates.setCoordinates(x, y);
	}

	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getCoordinates()
	 */
	public Coordinates getCoordinates(){
		return coordinates;
	}

	/**
	 * Return x coordinate of actor.
	 * 
	 * @return
	 */
	public DoubleProperty getX(){
		return coordinates.getX();
	}

	/**
	 * Return y coordinate of actor.
	 * 
	 * @return
	 */
	public DoubleProperty getY(){
		return coordinates.getY();
	}

	/**
	 * Return last distance traveled from setting the position of the actor.
	 * 
	 * @return
	 */
	public double getDistance(){
		return distanceTraveled;
	}

	/**
	 * Set heading of the actor.
	 * 
	 * @param angle
	 */
	public void setAngle(double angle){
		degreesMoved=angle-myAngle.get();
		double newangle=angle%DEGREES_CIRCLE;
		if(newangle<0){
		    newangle+=360;
		}
		myAngle.set(newangle);
	}

	/**
	 * Return heading of the actor.
	 * 
	 * @return
	 */
	public double getAngle(){
		return myAngle.get();
	}

	/**
	 * Return last number of degrees turned from setting the angle.
	 * 
	 * @return
	 */
	public double getAngleMoved(){
		return degreesMoved;
	}

	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getAngleProp()
	 */
	@Override
	public DoubleProperty getAngleProp(){
		return myAngle;
	}

	/**
	 * Set the pen status of the actor.
	 * 
	 * @param pen
	 */
	public void setPenStatus(boolean pen){
		penDown.set(pen);
	}

	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getPenStatus()
	 */
	@Override
	public BooleanProperty getPenStatus(){
		return penDown;
	}

	/**
	 * Set Visbility of the actor.
	 * 
	 * @param vis
	 */
	public void setVisibility(boolean vis){
		visible=vis;
	}

	/**
	 * Return visibility of the actor.
	 * 
	 * @return
	 */
	public boolean getVisibility(){
		return visible;
	}    

	/**
	 * Set index for color of trail of the actor.
	 * 
	 * @param index
	 */
	public void setPenColorIndex(int index) {
		penColorIndex = index;
	}     
	
	/**
	 * Return index of color of the trail for the actor.
	 * 
	 * @return
	 */
	public int getPenColorIndex(){
		return penColorIndex;
	}
	
	/**
	 * Set Pen width of trail.
	 * 
	 * @param size
	 */
	public void setPenSizeIndex(int size){
		penSizeIndex = size;
	}
	
	/**
	 * Change shape index of actor.
	 * 
	 * @param dex
	 */
	public void setShapeIndex(int dex){
		shapeIndex = dex;
	}
	
	/**
	 * Return shape index of actor
	 *
	 * @return
	 */
	public int getShapeIndex(){
		return shapeIndex;
	}

	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getImageView()
	 */
	@Override
	public ImageView getImageView(){
		return myImage;
	}

	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getLine()
	 */
	@Override
	public List<Line> getLine(){
		return myLines;
	}
	
	/* (non-Javadoc)
	 * @see commandreference.Turtleable#drawLine(double, double, double, double)
	 */
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
	
	/* (non-Javadoc)
	 * @see commandreference.Turtleable#clearLines()
	 */
	@Override
	public void clearLines(){
		myLines.clear();
	}
	
	/**
	 * Signal a reset in actor position and trails.
	 */
	public void setReset(){
		reset.set(reset.get()+1);
	}	
	
	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getReset()
	 */
	public DoubleProperty getReset(){
		return reset;
	}

	/**
	 * Set default values of actor
	 * 
	 * @param x
	 * @param y
	 * @param imageFilePath
	 */
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

	/**
	 * Set default pen color values of actors.
	 * 
	 * @param paletteVal
	 */
	public void setPenColor(int[] paletteVal) {
		penColorArray[0] = paletteVal[0];
		penColorArray[1] = paletteVal[1];
		penColorArray[2] = paletteVal[2];
	}
	
	/* (non-Javadoc)
	 * @see commandreference.Turtleable#getColorArray()
	 */
	public double[] getColorArray(){
		return penColorArray;
	}
}