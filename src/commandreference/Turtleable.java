package commandreference;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public interface Turtleable {
	
	public double getAngle();
	
	public double getX();
	
	public double getY();
	
	public int getPenColorIndex();
	
	public int getPenSizeIndex();
	
	public int getShapeIndex();
	
	public boolean getPenStatus();
	
	public ImageView getImageView();
	
	public Line getLine();
	
}
