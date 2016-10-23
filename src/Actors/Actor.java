package Actors;

import commandreference.Coordinates;

public abstract class Actor {

	private final static double DEGREES_CIRCLE=360.0;

	//    protected int xPosition, yPosition;
	protected Coordinates coordinates;
	protected double distanceTraveled, degreesMoved, myAngle;
	protected boolean penDown, visible;

	public Actor () {
		init(0,0);
	}

	public Actor (double x, double y) {
		init(x,y);
	}

	public void setPos(double x, double y){
//		distanceTraveled=Math.sqrt((xPosition-x)^2+(yPosition-y)^2);
//		xPosition=x;
//		yPosition=y;
	        distanceTraveled=Math.sqrt(Math.pow((coordinates.getX().get()-x),2)+Math.pow((coordinates.getY().get()-y),2));
		coordinates.setX(x);
		coordinates.setY(y);
	}

	public double getX(){
//		return xPosition;
		return coordinates.getX().get();
	}

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

	public double getAngle(){
		return myAngle;
	}

	public void setPenStatus(boolean pen){
		penDown=pen;
	}

	public boolean getPenStatus(){
		return penDown;
	}

	public void setVisibility(boolean vis){
		visible=vis;
	}

	public boolean getVisibility(){
		return visible;
	}    

	private void init(double x, double y){
		coordinates = new Coordinates(x, y);
		distanceTraveled=0;
		myAngle=0;
		penDown=true;
		visible=true;       
	}
	
	public Coordinates getCoordinates(){
		return coordinates;
	}
}
