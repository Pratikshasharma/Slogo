package SlogoException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserDefinitionException extends ParserException{
	private static final String ERROR_TITLE = "Error In User Definition";

	public UserDefinitionException() {
		super();
	}
	
	public UserDefinitionException (String message){
		super(message);
		
	}
	@Override
	public void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setContentText("Error in defining a commmand: " + message);
        alert.showAndWait();
    }
	
}
