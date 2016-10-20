package gui;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class History {
    private VBox myHistoryBox;
    private ListView<String> commandsList;
    private ListView<String> functionsList;
    private ListView<String> historyList;

    public History(){
        createWindows();
    }
    
    private void createWindows(){
        myHistoryBox = new VBox(20);
        myHistoryBox.setPadding(new Insets(20));
      
        commandsList.setLayoutX(GUIController.SCENE_HEIGHT/8);
        commandsList.setLayoutY(GUIController.SCENE_HEIGHT/8);
    }
    public VBox getMyHistoryVBox(){
        return this.myHistoryBox;
    }
}
