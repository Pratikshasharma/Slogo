package gui;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import java.io.File;
import java.net.MalformedURLException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * * @author pratiksha sharma
 */

public class Turtle {
    private Polyline myLine;
    private ImageView myTurtleImageView;
    private static final String TURTLE_TEST_IMAGE = "turtle.png";

    
    public Turtle() throws MalformedURLException {
        //myTurtleImageView = new ImageView(new Image(chooseFile().toURI().toURL().toString()));
        myTurtleImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_TEST_IMAGE)));
        initializeTurtle();
        myLine = new Polyline();
    }

    /**
     * Purpose: To choose file for Turtle using File Chooser
     * Dependencies: StartScreen.java 
     */
    private File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif");
        String userDirectoryString = System.getProperty("user.dir") + File.separator + "images";
        File userDirectory = new File(userDirectoryString);
        fileChooser.setInitialDirectory(userDirectory);
        fileChooser.getExtensionFilters().add(extentionFilter);
        Stage myStage = new Stage();
        File chosenFile = fileChooser.showOpenDialog(myStage);
        return chosenFile;
    }
    
    public ImageView getMyTurtleImageView(){
        return this.myTurtleImageView;
    }
    
    public Polyline getMyLine(){
        return this.myLine;
    }
    
    public void setPosition(double xPosition, double yPosition){
        myTurtleImageView.setX(xPosition);
        myTurtleImageView.setY(yPosition);
    }
    
    private void initializeTurtle(){
        myTurtleImageView.setFitWidth(50);
        myTurtleImageView.setFitHeight(50);
        
        setPosition(MainGUI.TURTLE_PANE_WIDTH/2,MainGUI.TURTLE_PANE_HEIGHT/2);
    }
    
    public void setLineColor(String color){
        myLine.setFill(Paint.valueOf(color));
    }
}
