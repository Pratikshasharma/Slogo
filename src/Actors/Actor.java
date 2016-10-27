package Actors;

import commandreference.Coordinates;
import commandreference.Turtleable;
import gui.MainGUI;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public abstract class Actor implements Turtleable {

	private final static double DEGREES_CIRCLE=360.0;
	private ImageView myImage;
	protected Coordinates coordinates;
	protected double distanceTraveled, degreesMoved, myAngle;
	protected boolean penDown, visible;
	private int penColorIndex;
	private int penSizeIndex;
	private int shapeIndex;
	private Line myLine;
	
	public Actor (String imageFilePath) {
		init(MainGUI.TURTLE_PANE_WIDTH / 2, MainGUI.TURTLE_PANE_HEIGHT / 2, imageFilePath);
	}

	public Actor (double x, double y, String imageFilePath) {
		init(x,y, imageFilePath);
	}

	public void setPos(double x, double y){
		distanceTraveled=Math.sqrt(Math.pow((coordinates.getX().get()-x),2)+Math.pow((coordinates.getY().get()-y),2));
		coordinates.setX(x);
		coordinates.setY(y);
	}
	
	public Coordinates getCoordinates(){
		return coordinates;
	}

	@Override
	public double getX(){
		return coordinates.getX().get();
	}

	@Override
	public double getY(){
		return coordinates.getY().get();
	}

	public double getDistance(){
		return distanceTraveled;
	}

	public void setAngle(double angle){
		degreesMoved=angle-myAngle;
		myAngle=angle%DEGREES_CIRCLE;
	}

	public double getAngleMoved(){
		return degreesMoved;
	}

	@Override
	public double getAngle(){
		return myAngle;
	}

	public void setPenStatus(boolean pen){
		penDown=pen;
	}

	public void setVisibility(boolean vis){
		visible=vis;
	}

	public boolean getVisibility(){
		return visible;
	}    

	private void init(double x, double y, String imageFilePath){
		coordinates = new Coordinates(x, y);
		myImage = new ImageView(imageFilePath);
		myImage.setFitWidth(40);
		myImage.setFitHeight(40);
		myLine = new Line();
		distanceTraveled=0;
		myAngle=0;
		penDown=true;
		visible=true;       
	}

	@Override
	public int getPenColorIndex() {
		return penColorIndex;
	}

	@Override
	public int getPenSizeIndex() {
		return penSizeIndex;
	}

	@Override
	public int getShapeIndex() {
		return shapeIndex;
	}
	
	@Override
	public boolean getPenStatus(){
		return penDown;
	}
	
	@Override
	public ImageView getImageView(){
		return myImage;
	}
	
	@Override
	public Line getLine(){
		return myLine;
	}
}
