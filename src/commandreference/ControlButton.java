package commandreference;

import gui.ButtonTemplate;
import gui.GUIController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class ControlButton {
	
	private Button myButton;
	
	public ControlButton(String property){
		myButton = setButton(property,GUIController.SCENE_WIDTH, GUIController.SCENE_HEIGHT);
	}
	
	private Button setButton(String property, double xPosition, double yPosition){
		ButtonTemplate buttonCreator = new ButtonTemplate(property);
		buttonCreator.changeButtonSettings(xPosition, yPosition);
		return buttonCreator.getButton();
	}
	
	public Button getButton(){
		return myButton;
	}
	
	public void setOnMouseClicked(EventHandler<? super MouseEvent> handler){
		myButton.setOnMouseClicked(handler);
	}
}
