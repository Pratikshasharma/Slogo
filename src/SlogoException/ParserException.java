package SlogoException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The exception that is thrown for most general parsing errors.
 * Thrown mostly in TreeFactory, handled in Parser. 
 * 
 * @author joykim
 */
public class ParserException extends RuntimeException{

    private static final String ERROR_TITLE="ERROR";

	public ParserException() {
		super();
	}
	
	public ParserException (String message){
		super(message);
		
	}
	
    public void showError (String commandName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText("Error in input: " + commandName);
        alert.showAndWait();
    }
	
}
