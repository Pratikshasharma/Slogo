
import commandreference.AppController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static final String TITLE = "SLOGO";
	
	@Override
	public void start(Stage myStage) throws Exception{
		AppController myAppController = new AppController();
		myStage.setTitle(TITLE);
		Scene scene = myAppController.initiateApp();
		scene.setOnKeyPressed(e -> myAppController.handleKeyInput(e.getCode()));
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


