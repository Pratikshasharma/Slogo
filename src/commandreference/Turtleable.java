package commandreference;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public interface Turtleable {
	
	public double getAngle();
	
	public DoubleProperty getX();
	
	public DoubleProperty getY();
	
	public int getPenColorIndex();
	
	public int getPenSizeIndex();
	
	public int getShapeIndex();
	
	public boolean getPenStatus();
	
	public ImageView getImageView();
	
	public Line getLine();
	
}
