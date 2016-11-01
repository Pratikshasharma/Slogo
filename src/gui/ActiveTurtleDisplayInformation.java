package gui;

import java.text.DecimalFormat;

import commandreference.Turtleable;
import javafx.scene.control.Label;

public class ActiveTurtleDisplayInformation {
	
	private Label idLabel;
	private Label currentOrientation;
	private Label penStatusLabel;
	
	public ActiveTurtleDisplayInformation(){
		idLabel = new Label("id: ");
		currentOrientation = new Label("orientation: ");
		penStatusLabel = new Label("pen: ");
	}
	
	public Label getIDLabel(){
		return idLabel;
	}
	
//	public void updateStatus(int id, FrontTurtle turtle){
//		setID(id);
//		setOrientation(turtle.getAngle().get());
//		setPenStatus(turtle.isPenUp());
//	}
	
	public void updateStatus(int id, Turtleable turtle){
		setID(id);
		setOrientation(turtle.getAngleProp().get());
		setPenStatus(turtle.getPenStatus().get());
	}
	
	public Label getCurrentOrienation(){
		return currentOrientation;
	}
	
	public Label getPenStatus(){
		return penStatusLabel;
	}
	
	private void setID(int id){
		idLabel.setText("id: "+ String.valueOf(id));
	}
	
	private void setOrientation(double angle){
        DecimalFormat df = new DecimalFormat("#.#");
		currentOrientation.setText("orientation: " + String.valueOf(df.format(angle)));
	}
	
	private void setPenStatus(boolean penStatus){
		penStatusLabel.setText("pen: " + String.valueOf(penStatus));
	}
}
