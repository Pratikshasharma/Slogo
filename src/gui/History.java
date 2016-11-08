// This entire file is part of my masterpiece.
// Ted Marchildon

/*
 * Meant to show the creation of the lambda expressions for the masterpiece
 */
package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class History {
	
    private VBox myHistoryBox;
    private ListView<String> commandsList;
    private ListView<String> functionsList;
    private ListView<String> variableList;
    
    public History(){
        createWindows();
    }
 
    private void createWindows(){
        myHistoryBox = new VBox(20);
        myHistoryBox.setPadding(new Insets(20));
        initializeHistoryBoxes();
        myHistoryBox.getChildren().add(createLabel("Command History"));
        setList(commandsList, 8);
        myHistoryBox.getChildren().add(createLabel("Function History"));
        setList(functionsList, 16);
        myHistoryBox.getChildren().add(createLabel("Variable History"));
        setList(variableList, 24);
    }
    
    private Label createLabel(String message){
    	return new Label(message);
    }

	private void initializeHistoryBoxes() {
		commandsList = new ListView<String>();
        functionsList = new ListView<String>();
        variableList = new ListView<String>();
	}

	private void setList(ListView<String> list, int offset) {
        list.setLayoutX(GUIController.SCENE_HEIGHT/offset);
        list.setLayoutY(GUIController.SCENE_HEIGHT/offset);
        myHistoryBox.getChildren().add(list);
	}
	
	public HistoryClickable getHistoryClickable(){
		HistoryClickable hc = (console, list) -> {
			if(list.getSelectionModel().getSelectedItem() != null){
				String selectedCommand = list.getSelectionModel().getSelectedItem().toString();
				console.getTextField().setText(selectedCommand);
			}
		};
		return hc;
	}
    
    public VBox getMyHistoryVBox(){
        return myHistoryBox;
    }
    
    public void addToCommandHistory(String command){
        if(command.length()!=0){
    	commandsList.getItems().add(command);
        };
    }
    
    public void addToFunctionsHistory(String function){
    	functionsList.getItems().add(function);
    }
    
    public void addToVariableList(String variable){
    	variableList.getItems().add(variable);
    }
    
    public ListView<String> getCommandsList(){
    	return commandsList;
    }
    
    public ListView<String> getFunctionsList(){
    	return functionsList;
    }
    
    public ListView<String> getVariableList(){
    	return variableList;
    }
}
