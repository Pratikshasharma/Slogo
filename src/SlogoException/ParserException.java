package SlogoException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ParserException extends RuntimeException{

    private static final String ERROR_TITLE="Back End: Command Parser Error";

	public ParserException() {
		super();
	}
	
	public ParserException (String message){
		super(message);
		
	}
	
    public void showError (String commandName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText("Error in parsing commmand at method: " + commandName);
        alert.showAndWait();
    }
	
}
