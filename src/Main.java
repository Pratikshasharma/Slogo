
import gui.GUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static final String TITLE = "SLOGO";
	private GUIController myGUIController;
	
	@Override
	public void start(Stage myStage) throws Exception{
		myGUIController = new GUIController();
		myStage.setTitle(TITLE);
		Scene scene = myGUIController.init();
		scene.setOnKeyPressed(e -> myGUIController.handleKeyInput(e.getCode()));
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Start the program.
	 */
	 public static void main (String[] args) {
		 launch(args);
	 }
}


