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
    private Console myConsole;
    
    public History(Console console){
    	myConsole = console;
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
        list.setOnMouseClicked(e -> handleSelection(list));
        myHistoryBox.getChildren().add(list);
	}
	
	private void handleSelection(ListView<String> list){
		if(list.getSelectionModel().getSelectedItem() != null){
			String selectedCommand = list.getSelectionModel().getSelectedItem().toString();
			myConsole.getTextField().setText(selectedCommand);
		}
	}
    
    public VBox getMyHistoryVBox(){
        return myHistoryBox;
    }
    
    public void addToCommandHistory(String command){
    	commandsList.getItems().add(command);
    }
    
    public void addToFunctionsHistory(String function){
    	functionsList.getItems().add(function);
    }
    
    public void addToVariableList(String variable){
    	variableList.getItems().add(variable);
    }
}
