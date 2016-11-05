package gui;

import javafx.scene.control.TextArea;

public class Console{
	
    private TextArea myCommandWindow;
    private boolean StartsTyping;

    public Console(){
        createTextField();
    }

    private void createTextField(){
        myCommandWindow = new TextArea();
        myCommandWindow.setOnKeyTyped(e -> {
            if(!StartsTyping){
                myCommandWindow.clear();
                StartsTyping = true;}
        });
        myCommandWindow.setLayoutY(0.8*GUIController.SCENE_HEIGHT);
        myCommandWindow.setLayoutX(0.02*GUIController.SCENE_WIDTH);
        myCommandWindow.setPrefHeight(MainGUI.TURTLE_PANE_HEIGHT/4);
        myCommandWindow.setPrefSize(MainGUI.TURTLE_PANE_WIDTH, MainGUI.TURTLE_PANE_HEIGHT/4);
    }

    public TextArea getTextField(){
        return myCommandWindow;
    }
    
    public void setText(String text){
    	myCommandWindow.setText(text);
    }
    
    public String getText(){
    	return myCommandWindow.getText();
    }
}



