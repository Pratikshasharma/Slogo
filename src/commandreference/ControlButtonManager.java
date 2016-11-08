package commandreference;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControlButtonManager {
	
	private ControlButton runButton;
	private ControlButton clearButton;
	private ControlButton togglePenStatus;
	private ControlButton saveButton;
	private ControlButton loadButton;
	
	public ControlButtonManager(){
		runButton = new RunButton("RunButtonCommand");
		clearButton = new ClearButton("ClearButtonCommand");
		togglePenStatus = new TogglePenButton("TogglePen");
		saveButton = new SaveButton("SaveButton");
		loadButton = new LoadButton("LoadButton");
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
	
	public void setOnSave(EventHandler<? super MouseEvent> handler){
		saveButton.setOnMouseClicked(handler);
	}
	
	public void setOnLoad(EventHandler<? super MouseEvent> handler){
		loadButton.setOnMouseClicked(handler);
	}
	
	/*
	 * The following getters are used to add the button nodes to the main interface in MainGUI
	 * 
	 */
	public Button getRunButtonNode(){
		return runButton.getButton();
	}
	
	public Button getClearButtonNode(){
		return clearButton.getButton();
	}
	
	public Button getTogglePenButtonNode(){
		return togglePenStatus.getButton();
	}
	
	public Button getSaveButtonNode(){
		return saveButton.getButton();
	}
	
	public Button getLoadButtonNode(){
		return loadButton.getButton();
	}
}
