package Actors;

public abstract class Actor {
    private final static double DEGREES_CIRCLE=360.0;
    
    protected int xPosition, yPosition;
    protected double distanceTraveled, degreesMoved, myAngle;
    protected boolean penDown, visible;
    
    public Actor () {
        init(0,0);
    }

    public Actor (int x, int y) {
        init(x,y);
    }

    public void setPos(int x,int y){
        distanceTraveled=Math.sqrt((xPosition-x)^2+(yPosition-y)^2);
        xPosition=x;
        yPosition=y;
    }
    
    public int getX(){
        return xPosition;
    }
    
    public int getY(){
        return yPosition;
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
    
    private void init(int x, int y){
        xPosition=x;
        yPosition=y;
        distanceTraveled=0;
        myAngle=0;
        penDown=true;
        visible=true;       
    }
}
