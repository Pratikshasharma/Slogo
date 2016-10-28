package commandreference;

import gui.ButtonTemplate;
import gui.GUIController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControlButtons {
	
	private Button runButton;
	private Button clearButton;
	private Button togglePenStatus;
	
	public ControlButtons(){
		runButton = setButton("RunButtonCommand",0.8*GUIController.SCENE_WIDTH, 0.9*GUIController.SCENE_HEIGHT/9);
		clearButton = setButton("ClearButtonCommand",0.9*GUIController.SCENE_WIDTH/9, 0.9*GUIController.SCENE_HEIGHT);
		togglePenStatus = setButton("TogglePen", GUIController.SCENE_WIDTH, GUIController.SCENE_HEIGHT);
	}
	
	public void setOnRun(EventHandler<? super MouseEvent> handler){
		runButton.setOnMouseClicked(handler);
	}
	
	public void setOnClear(EventHandler<? super MouseEvent> handler){
		clearButton.setOnMouseClicked(handler);
	}
	
	public void setOnTogglePen(EventHandler<? super MouseEvent> handler){
		togglePenStatus.setOnMouseClicked(handler);
	}
	
	public Button getRunButton(){
		return runButton;
	}
	
	public Button getClearButton(){
		return clearButton;
	}
	
	public Button getTogglePenButton(){
		return togglePenStatus;
	}
	
	private Button setButton(String property, double xPosition, double yPosition){
		ButtonTemplate buttonCreator = new ButtonTemplate(property);
		buttonCreator.changeButtonSettings(xPosition, yPosition);
		return buttonCreator.getButton();
	}
}
