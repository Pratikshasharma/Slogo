package gui;

import javafx.scene.control.TextField;

/**
 * @author Pratiksha Sharma
 */
public class Console {
    private TextField myCommandWindow;
    private boolean StartsTyping;

    public Console(){
        createTextField();
    }

    private void createTextField(){
        myCommandWindow = new TextField("Enter Command");
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

    public TextField getTextField(){
        return myCommandWindow;
    }

}



