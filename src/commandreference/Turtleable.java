package commandreference;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public interface Turtleable {
	
	public DoubleProperty getAngleProp();
	
//	public DoubleProperty getX();
//	
//	public DoubleProperty getY();
	
	public Coordinates getCoordinates();
	
	public BooleanProperty getPenStatus();
	
	public ImageView getImageView();
	
	public List<Line> getLine();
	
	public DoubleProperty getReset();
	
	public Line drawLine(double x, double y, double x1, double y1);

	public void clearLines();
	
	public double[] getColorArray();
	
}
