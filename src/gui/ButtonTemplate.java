package gui;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import navigationTabs.MenuCreator;

public class ButtonTemplate {
    private Button myButton;
    private ResourceBundle myResources;

    public ButtonTemplate(String property){
        myResources = ResourceBundle.getBundle(MenuCreator.RESOURCE_PACKAGE + File.separator + MenuCreator.BUTTON_LABEL_FILE );
        String label = myResources.getString(property) ;  
         myButton = new Button(label);
    }
    
    public Button getButton(){
        return this.myButton;
    }
    
    public void changeButtonSettings(double xPosition, double yPosition){
        myButton.setLayoutX(xPosition);
        myButton.setLayoutY(yPosition);
        myButton.setFont(Font.font("Comic Sans",14));
    }
}
