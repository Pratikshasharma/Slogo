package gui;

import java.net.MalformedURLException;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GUIController {
    private MainGUI myMainGUI;
    public static final double SCENE_WIDTH = 900;
    public static final double SCENE_HEIGHT = 650;
    
    public GUIController() throws MalformedURLException{
        myMainGUI = new MainGUI();
    }
    
    public Scene init(){
        Scene myScene = new Scene(myMainGUI.createRoot(),SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
        return myScene;
    }
}
