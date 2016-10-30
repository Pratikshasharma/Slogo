
import gui.Slogo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static final String TITLE = "SLOGO";
	
	@Override
	public void start(Stage myStage) throws Exception{
	    Slogo myWindowsManager = new Slogo();
		myStage.setTitle(TITLE);
		Scene scene = myWindowsManager.startSlogo();
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


