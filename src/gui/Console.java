package gui;

import javafx.scene.control.TextArea;

/**
 * @author Pratiksha Sharma
 */
public class Console {
    private TextArea myCommandWindow;
    private boolean StartsTyping;

    public Console(){
        createTextField();
    }

    private void createTextField(){
        myCommandWindow = new TextArea("Enter Command");
        myCommandWindow.setOnKeyTyped(e -> {
            if(!StartsTyping){
                myCommandWindow.clear();
                StartsTyping = true;}
        });
        myCommandWindow.setLayoutY(0.8*GUIController.SCENE_HEIGHT);
        myCommandWindow.setLayoutX(0.02*GUIController.SCENE_WIDTH);
        myCommandWindow.setPrefHeight(MainGUI.TURTLE_PANE_HEIGHT/4);
        myCommandWindow.setPrefSize(MainGUI.TURTLE_PANE_WIDTH, MainGUI.TURTLE_PANE_HEIGHT/4);
        //myCommandWindow.setMaxWidth(MainGUI.TURTLE_PANE_WIDTH);
    }

    public TextArea getTextField(){
        return myCommandWindow;
    }
}



