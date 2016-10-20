package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import java.io.File;
import java.net.MalformedURLException;
import org.omg.CORBA.SystemException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * * @author pratiksha sharma
 *
 */

public class Turtle {
    private Polyline myLine;
    private ImageView myTurtleImageView;

    
    public Turtle() throws MalformedURLException{
        myTurtleImageView = new ImageView(new Image(chooseFile().toURI().toURL().toString()));
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
        
        // Change based on Size 
        setPosition(450,200);
    }
    
    public void setLineColor(String color){
        myLine.setFill(Paint.valueOf(color));
    }
}
