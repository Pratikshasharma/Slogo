package gui;

import commandreference.Coordinates;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GUIController {

    private MainGUI myMainGUI;
    private Coordinates myCoordinates;
    public static final double SCENE_WIDTH = 900;
    public static final double SCENE_HEIGHT = 650;

    public GUIController() {
        myMainGUI = new MainGUI();
        myCoordinates = new Coordinates();
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

    public void updateLocation(){
//    	myCoordinates.setX(x);
//    	myCoordinates.setY(y);
    	System.out.println(myCoordinates.getX().get() + " " + myCoordinates.getY());
    	myMainGUI.getTurtle().getMyLine().setStartX(myCoordinates.getX().get());
    	myMainGUI.getTurtle().getMyLine().setStartY(myCoordinates.getY().get());
    	myMainGUI.getTurtle().setPosition(myCoordinates.getX().get(), myCoordinates.getY().get());
    }
    
    public MainGUI getMainGUI(){
    	return myMainGUI;
    }
    
    public Coordinates getCoordinates(){
    	return myCoordinates;
    }
    
}

