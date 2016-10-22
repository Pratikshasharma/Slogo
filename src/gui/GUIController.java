package gui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GUIController {

    private MainGUI myMainGUI;
    public static final double SCENE_WIDTH = 900;
    public static final double SCENE_HEIGHT = 650;

    public GUIController() {
        myMainGUI = new MainGUI();
    }

    public Scene init(){
        Scene myScene = new Scene(myMainGUI.createRoot(),SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
        return myScene;
    }


    public void addToHistory(){
        myMainGUI.getHistory().addToCommandHistory(getCommandEntered());
    }

    public String getCommandEntered(){
        return myMainGUI.getConsole().getTextField().getText();
    }

    public void updateLocation(double x, double y){
        myMainGUI.getTurtle().setPosition(x, y);
    }

    public void handleKeyInput(KeyCode code){
        switch(code) {
            case ENTER: 
                addToHistory();
                myMainGUI.getConsole().getTextField().setText("");
            default:
                //Do nothing

        }
    }
    
}

