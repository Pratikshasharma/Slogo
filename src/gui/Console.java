package gui;

/**
 * @author Pratiksha Sharma
 */
import javafx.scene.control.TextField;
public class Console {
    private TextField myCommand;
    
    public Console(){
        createTextField();
    }
    
   private void createTextField(){
       myCommand = new TextField("Enter Command");
       myCommand.setLayoutY(0.8*GUIController.SCENE_HEIGHT);
       myCommand.setLayoutX(0.02*GUIController.SCENE_WIDTH);
       myCommand.setPrefSize(GUIController.SCENE_WIDTH/8, GUIController.SCENE_HEIGHT/8.5);
    }
   
   public TextField getTextField(){
       return myCommand;
   }
}
