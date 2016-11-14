package SlogoException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This error is used to specifically check for UserDefined method 
 * errors. This way, when handling this error in other classes, it
 * would be handled differently from a normal ParserException.
 * 
 * @author joykim
 */
public class UserDefinitionException extends ParserException{
	private static final String ERROR_TITLE = "ERROR";

	public UserDefinitionException() {
		super();
	}
	
	public UserDefinitionException (String message){
		super(message);
		
	}
	
	public void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText("Error in defining a commmand: " + message);
        alert.showAndWait();
    }
	
}
