//package gui;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Line;
//import java.io.File;
//import java.net.MalformedURLException;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
///**
// * * @author pratiksha sharma
// */
//public class Turtle {
//
//    private Line myLine;
//    private ImageView myTurtleImageView;
//    private static final String TURTLE_TEST_IMAGE = "turtle.png";
//    private Image turtleImage;
//    private int pencolorindex;
//    private int pensizeindex;
//    private int turtleshapeindex;
//
//    public Turtle(boolean defaultTurtle)  {
//        myLine = new Line();
//        if(defaultTurtle){
//            myTurtleImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_TEST_IMAGE)));
//            
//        }else{
//            try {
//                myTurtleImageView = new ImageView(new Image(chooseFile().toURI().toURL().toString()));    
//            }
//            catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        //myTurtleImageView = new ImageView(turtleImage);
//        initializeTurtle(); 
//        initializeLine();
//    }
//    
//    /**
//     * Purpose: To choose file for Turtle using File Chooser
//     */
//    private File chooseFile(){
//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif");
//        String userDirectoryString = System.getProperty("user.dir") + File.separator + "images";
//        File userDirectory = new File(userDirectoryString);
//        fileChooser.setInitialDirectory(userDirectory);
//        fileChooser.getExtensionFilters().add(extentionFilter);
//        Stage myStage = new Stage();
//        File chosenFile = fileChooser.showOpenDialog(myStage);
//        return chosenFile;
//    }
//
//    private void initializeLine(){
//        myLine.setStartX(myTurtleImageView.getX() + myTurtleImageView.getBoundsInLocal().getWidth()/2);
//        myLine.setStartY(myTurtleImageView.getY()); 
//        myLine.setEndX((myLine.getStartX()+2));
//        myLine.setEndY((myLine.getStartY()+2));
//    }
//
//    public void drawLine(double xPosition, double yPosition){
//        myLine.setStartX(myLine.getEndX());
//        myLine.setStartY(myLine.getEndY());
//        myLine.setEndX(xPosition);
//        myLine.setEndY(yPosition);
//    }
//
//    public ImageView getMyTurtleImageView(){
//        return this.myTurtleImageView;
//    }
//
//    public void setColor(String string){
//        myLine.setStroke(Paint.valueOf(string));
//    }
//    
//
//    public void setPenWidth(double width){
//        myLine.setStrokeWidth(width);
//    }
//
//    public Line getMyLine(){
//        return myLine;
//    }
//
//    public void setPosition(double xPosition, double yPosition){
//        myTurtleImageView.setX(xPosition);
//        myTurtleImageView.setY(yPosition);    
//    }
//
//    private void initializeTurtle(){
//        myTurtleImageView.setFitWidth(50);
//        myTurtleImageView.setFitHeight(50);
//        setPosition(MainGUI.TURTLE_PANE_WIDTH/2,MainGUI.TURTLE_PANE_HEIGHT/2);
//    }
//
//}
