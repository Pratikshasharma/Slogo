
import java.io.File;
import gui.Slogo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import navigationTabs.MenuTemplate;

/**
 * @author pratikshasharma
 * Sets up the stage to start the program
 * Dependencies: startSlogo() method in Slogo class
 */

public class Main extends Application{
    private static final String TITLE = "SLOGO";
    public static final String STYLESHEET = "default.css";

    /**
     * Sets up the stage 
     */
    @Override
    public void start(Stage myStage) {
        Slogo myWindowsManager = new Slogo();
        myStage.setTitle(TITLE);
        Scene scene = myWindowsManager.startSlogo();
        myStage.setScene(scene);
        scene.getStylesheets().add(MenuTemplate.RESOURCE_PACKAGE + File.separator + STYLESHEET);
        myStage.show();	
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}


