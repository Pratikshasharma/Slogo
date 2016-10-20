package gui;

import javafx.geometry.Insets;
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
        setList(commandsList, 8);
        setList(functionsList, 16);
        setList(variableList, 24);
    }

	private void setList(ListView<String> list, int offset) {
		list = new ListView<String>();
        list.setLayoutX(GUIController.SCENE_HEIGHT/offset);
        list.setLayoutY(GUIController.SCENE_HEIGHT/offset);
        myHistoryBox.getChildren().add(list);
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
