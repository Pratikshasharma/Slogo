package gui;

//import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorAlert {
    //private ResourceBundle myResources;
    public static final String ERROR_FILE = "Errors";
    
    public ErrorAlert(String property){
        displayAlert(property);
    }  
    
    public void displayAlert(String property){
//        myResources = ResourceBundle.getBundle(MenuCreator.RESOURCE_PACKAGE + File.separator + ERROR_FILE);
//        String error = myResources.getString(property);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(property);
        alert.showAndWait();
    }
}
