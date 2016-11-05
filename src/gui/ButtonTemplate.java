package gui;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import navigationTabs.MenuTemplate;

/**
 * 
 * @author pratikshasharma
 * Dependencies: ButtonLayout Interface, Button.properties file
 * Assumptions: MenuTemplate Class exists
 */

public class ButtonTemplate implements ButtonLayout{
    private Button myButton;
    private ResourceBundle myResources;

    /**
     * @param property
     * Creates a button based on the String property
     */
    public ButtonTemplate(String property){
        myResources = ResourceBundle.getBundle(MenuTemplate.RESOURCE_PACKAGE + File.separator + MenuTemplate.BUTTON_LABEL_FILE );
        String label = myResources.getString(property) ;  
        myButton = new Button(label);
    }

    /**
     * @return Button
     */
    public Button getButton(){
        return this.myButton;
    }
    
    /**
     * Changes layout positions of the button
     */
    public void changeButtonSettings(double xPosition, double yPosition){
        myButton.setLayoutX(xPosition);
        myButton.setLayoutY(yPosition);
        myButton.setFont(Font.font("Comic Sans",14));
    }
}
