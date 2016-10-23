package gui;

import commandreference.Coordinates;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GUIController {

    private MainGUI myMainGUI;
    private Coordinates myCoordinates;
    public static final double SCENE_WIDTH = 900;
    public static final double SCENE_HEIGHT = 650;
    public static final String DEFAULT_LANGUAGE = "English";
    private String myCommandLanguage;

    public GUIController() {
        myMainGUI = new MainGUI();
        myCoordinates = new Coordinates();
    }

    public Scene init(){
        Scene myScene = new Scene(myMainGUI.createRoot(),SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
        setLanguage ();
        return myScene;
    }

    public void addToHistory(){
        myMainGUI.getHistory().addToCommandHistory(getCommandEntered());
    }

    public String getCommandEntered(){
        return myMainGUI.getConsole().getTextField().getText();
    }

    public void updateLocation(){
        System.out.println(myCoordinates.getX().get() + " " + myCoordinates.getY());
        System.out.println( myMainGUI.getTurtle().getMyTurtleImageView().getX());
        System.out.println(myMainGUI.getTurtle().getMyTurtleImageView().getY());
        //myMainGUI.getTurtle().getMyLine().setStartX(myMainGUI.getTurtle().getMyLine().getEndX());
        //myMainGUI.getTurtle().getMyLine().setStartY(myMainGUI.getTurtle().getMyLine().getEndY());
        myMainGUI.getTurtle().getMyLine().setEndX( myMainGUI.getTurtle().getMyLine().getStartX() + myCoordinates.getX().get());
        myMainGUI.getTurtle().getMyLine().setEndY(myMainGUI.getTurtle().getMyLine().getStartY()+ myCoordinates.getY().get());
        myMainGUI.getTurtle().setPosition(myMainGUI.getTurtle().getMyTurtleImageView().getX() + myCoordinates.getX().get(),myMainGUI.getTurtle().getMyTurtleImageView().getY() + myCoordinates.getY().get());
    }

    public MainGUI getMainGUI(){
        return myMainGUI;
    }

    public Coordinates getCoordinates(){
        return myCoordinates;
    }

    private void setLanguage(){
         myMainGUI.getLanguageMenu().getItems().stream().forEach((menuItem) -> {
            menuItem.setOnAction( e -> myCommandLanguage = menuItem.getText());
        });  
}  

    public String getLanguage(){
        if(myCommandLanguage.length()!=0){ return this.myCommandLanguage; };
         myCommandLanguage = DEFAULT_LANGUAGE;
         return myCommandLanguage;  
    }

//private boolean checkOutOfBounds(double xPosition, double yPosition){
//     if(myMainGUI.getTurtle().getMyTurtleImageView().getX() + myMainGUI.getTurtle().getMyTurtleImageView().getFitWidth() >=MainGUI.TURTLE_PANE_WIDTH){
//         return false;
//     } else if (myMainGUI.getTurtle())
//    return false;
//}
}

