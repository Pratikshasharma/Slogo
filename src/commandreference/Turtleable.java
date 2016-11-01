package commandreference;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public interface Turtleable {
	
	public DoubleProperty getAngleProp();
	
	public DoubleProperty getX();
	
	public DoubleProperty getY();
	
	public Coordinates getCoordinates();
	
	public DoubleProperty getPenColorIndex();
	
	public DoubleProperty getPenSizeIndex();
	
	public DoubleProperty getShapeIndex();
	
	public BooleanProperty getPenStatus();
	
	
	public ImageView getImageView();
	
	public Line getLine();
	
	public DoubleProperty getReset();
}
