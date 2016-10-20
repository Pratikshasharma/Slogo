package gui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StartScreen {
    private final String START_IMAGE = "slogo.jpg";
    
    public Group createRoot(){
        Group root = new Group();
        ImageView myStartImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(START_IMAGE)));
        myStartImage.setFitWidth(GUIController.SCENE_WIDTH);
        myStartImage.setFitHeight(GUIController.SCENE_HEIGHT);
        root.getChildren().addAll(myStartImage);
        return root;
    }
}
