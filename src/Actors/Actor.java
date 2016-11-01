package Actors;

import commandreference.Coordinates;
import commandreference.Turtleable;
import gui.MainGUI;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public abstract class Actor implements Turtleable {
	private final static double DEGREES_CIRCLE=360.0;
	private ImageView myImage;
	protected Coordinates coordinates;
	protected double distanceTraveled, degreesMoved;
	protected DoubleProperty myAngle;
	protected boolean penDown, visible;
	private int penColorIndex;
	private int penSizeIndex;
	private int shapeIndex;
	private Line myLine;
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

	@Override
	public DoubleProperty getX(){
		return coordinates.getX();
	}

	@Override
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
		penDown=pen;
	}

	@Override
	public boolean getPenStatus(){
		return penDown;
	}

	public void setVisibility(boolean vis){
		visible=vis;
	}


	public boolean getVisibility(){
		return visible;
	}    

	public void setPenColorIndex(int index) {
		penColorIndex=index;
	}

	@Override
	public int getPenColorIndex() {
		return penColorIndex;
	}

	public void setPenSizeIndex(int index) {
		penSizeIndex=index;
	}

	@Override
	public int getPenSizeIndex() {
		return penSizeIndex;
	}

	public void setShapeIndex(int index) {
		shapeIndex=index;
	}       

	@Override
	public int getShapeIndex() {
		return shapeIndex;
	}	


	@Override
	public ImageView getImageView(){
		return myImage;
	}

	@Override
	public Line getLine(){
		return myLine;
	}

	
	public boolean isReadyForChange(){
		return coordinates.isReadyForChange();
	}
	
	public void setReadyForChange(boolean b){
		coordinates.setReadForChange(b);
	}
	
	public void setReset(){
	    reset.set(reset.get()+1);
	}	

        private void init(double x, double y, String imageFilePath){
                coordinates = new Coordinates(x, y);
                myAngle = new SimpleDoubleProperty(0);
                myImage = new ImageView(imageFilePath);
                myImage.setFitWidth(40);
                myImage.setFitHeight(40);
                myLine = new Line();
                distanceTraveled=0;
                penDown=true;
                visible=true;       
                reset.set(0);
        }
}
