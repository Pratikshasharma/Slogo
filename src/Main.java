
import java.io.File;
import gui.Slogo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import navigationTabs.MenuCreator;

public class Main extends Application{
    private static final String TITLE = "SLOGO";
    public static final String STYLESHEET = "default.css";

    @Override
    public void start(Stage myStage) {
        Slogo myWindowsManager = new Slogo();
        myStage.setTitle(TITLE);
        Scene scene = myWindowsManager.startSlogo();
        myStage.setScene(scene);
        scene.getStylesheets().add(MenuCreator.RESOURCE_PACKAGE + File.separator + STYLESHEET);
        myStage.show();	
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}


