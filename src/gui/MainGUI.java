package gui;
import javafx.event.EventHandler;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import commandreference.ControlButtons;
import commandreference.Turtleable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import navigationTabs.FileTab;
import navigationTabs.Help;
import navigationTabs.Language;
import navigationTabs.Tools;
import navigationTabs.Window;

/**
 * Purpose: Add all nodes required in the scene
 * Dependencies: Classes: FileTab, Language, Tools, Console, Help,Window, ControlButtons,UserDefault, ActiveTurtleDisplayInformation
 * @author pratikshasharma, Teddy
 *
 */

public class MainGUI implements MainGUITemplate{
    private FileTab myFileTab;
    private Language myLanguageTab; 
    private Tools myTools;
    private Console myConsole;
    private History myHistory;
    private Help myHelpTab;
    private BorderPane myRoot;
    private Pane myCanvas;
    private Window myWindow;
    private ControlButtons myControlButtons;
    private UserDefaults myPrefs;
    private ActiveTurtleDisplayInformation myActiveTurtleInfo;
    public static final double TURTLE_PANE_WIDTH = 550;
    public static final double TURTLE_PANE_HEIGHT = 450;
    private HBox myHBox;
    private ScrollPane myScrollPane;

    public MainGUI()  {
        myRoot = new BorderPane();
        myFileTab = new FileTab();
        myTools = new Tools();
        myLanguageTab = new Language();
        myConsole = new Console();
        myHelpTab = new Help();
        myHistory = new History();
        myWindow = new Window();
        myActiveTurtleInfo = new ActiveTurtleDisplayInformation();
        myControlButtons = new ControlButtons();
        myPrefs = new UserDefaults(this.getClass().toString());
        setHistoryClickables();
        myHBox = new HBox();
        myScrollPane = new ScrollPane();
    }

    /**
     * @return Parent
     * Returns main root of the scene
     */
    public Parent createRoot(){
        myRoot.setTop(createTop());
        myRoot.setLeft(createLeft());
        myRoot.setBottom(createBottom());
        myRoot.setRight(myHistory.getMyHistoryVBox());
        myRoot.setPadding(new Insets(20));
        return myRoot;
    }

    private VBox createLeft(){
        VBox left = new VBox();   
        left.getChildren().addAll(createTurtlePane(), myConsole.getTextField());
        return left;
    }

    private HBox createTop(){
        HBox top = new HBox(20);
        top.getChildren().addAll(addItemsInMenuBar());

        top.getChildren().add(myScrollPane);
        return top;
    }

    public void updateActiveTurtleInfo(int id, Turtleable turtle){
        myActiveTurtleInfo.updateStatus(id, turtle);
    }

    private HBox createBottom(){
        HBox bottomBox = new HBox(20);
        VBox activeLabels = new VBox(20);
        VBox controlButtons = new VBox();
        setOnClearButton();
        controlButtons.getChildren().addAll(myControlButtons.getRunButton(), myControlButtons.getClearButton(), myControlButtons.getTogglePenButton(), myControlButtons.getSaveButton(), myControlButtons.getLoadButton());
        activeLabels.getChildren().addAll(myActiveTurtleInfo.getIDLabel(), myActiveTurtleInfo.getCurrentOrienation(), myActiveTurtleInfo.getPenStatus());
        bottomBox.getChildren().addAll(myConsole.getTextField(), controlButtons, activeLabels);
        return bottomBox;
    }

    private void setHistoryClickables(){
        HistoryClickable historyClickable = myHistory.getHistoryClickable();
        myHistory.getCommandsList().setOnMouseClicked(e -> historyClickable.updateConsole(myConsole, myHistory.getCommandsList()));
        myHistory.getFunctionsList().setOnMouseClicked(e -> historyClickable.updateConsole(myConsole, myHistory.getFunctionsList()));
        myHistory.getVariableList().setOnMouseClicked(e -> historyClickable.updateConsole(myConsole, myHistory.getVariableList()));
    }

    private Pane createTurtlePane(){
        myCanvas = new Pane();
        myCanvas.setStyle("-fx-background-color: " + myPrefs.getBackground("white") + "; -fx-border-color: black; -fx-border-width: 2px");
        myCanvas.setPrefSize(TURTLE_PANE_WIDTH,TURTLE_PANE_HEIGHT);
        return myCanvas;
    }

    private MenuBar addItemsInMenuBar(){
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(myFileTab.getMyMenu(), myTools.getMyMenu(), myLanguageTab.getMyMenu(), myHelpTab.getMyMenu(), myWindow.getMyMenu());
        return menuBar;
    }

    private void addTurtleOnScene(Turtleable turtle){
        if(!isOnCanvas(turtle.getImageView())){
            ImageView imageview = new ImageView(turtle.getImageView().getImage());

            // Added to change the ImageView of the active turtle
            imageview.setFitHeight(40);
            imageview.setFitWidth(40);
            myHBox.getChildren().add(imageview);

            myScrollPane.setContent(myHBox);

            turtle.getImageView().setX(turtle.getCoordinates().getX().get());
            turtle.getImageView().setY(turtle.getCoordinates().getY().get());
            turtle.getImageView().setId(turtle.getImageView().toString());
           
            imageview.setOnMouseClicked(e-> changeImageView(turtle, imageview));

            myCanvas.getChildren().add(turtle.getImageView());
        }
    }

    // ADded this to change the image of the active turtle
    
    private void changeImageView(Turtleable turtle, ImageView oldImageView){
        FileOpener file = new FileOpener();
        try{
            String filePath = file.chooseFile().toURL().toString();
            if(filePath!=null){
                ImageView image = new ImageView(new Image(filePath));
                image.setFitHeight(40);
                image.setFitWidth(40);    
                oldImageView.setImage(image.getImage());

                turtle.setImage(image.getImage());
            }
        } catch (MalformedURLException error) {
            ErrorAlert alert = new ErrorAlert("Image File not Loaded");
        }
    }


    public FileTab getMyFileTab(){
        return myFileTab;
    }

    /**
     * Updates location of the active turtle
     */
    public void updateTurtleLocation(Turtleable turtle){
        double x = turtle.getImageView().getX();
        double y = turtle.getImageView().getY();
        turtle.getImageView().setX(turtle.getCoordinates().getX().get());
        turtle.getImageView().setY(turtle.getCoordinates().getY().get());
        if(x == 0 && y == 0){
            addTurtleOnScene(turtle);
            return;
        }
        if(turtle.getPenStatus().get()){
            addLineOnCanvas(turtle, x, y);
        }
    }

    private void addLineOnCanvas(Turtleable turtle, double x, double y){
        double red = turtle.getColorArray()[0];
        double green = turtle.getColorArray()[1];
        double blue = turtle.getColorArray()[2];
        if(turtle.getLine().size()<1){
            Line l = turtle.drawLine(x+turtle.getImageView().getBoundsInLocal().getWidth()/2, y, turtle.getCoordinates().getX().get()+turtle.getImageView().getBoundsInLocal().getWidth()/2, turtle.getCoordinates().getY().get());
            String myString = "rgb(" + ((int) red) + "," + ((int) green) + "," + ((int) blue) + ")";
            l.setStyle("-fx-stroke: " + myString);
            myCanvas.getChildren().add(l);
        } else {
            Line l = turtle.drawLine(x, y, turtle.getCoordinates().getX().get(), turtle.getCoordinates().getY().get());
            String myString = "rgb(" + ((int) red) + "," + ((int) green) + "," + ((int) blue) + ")";
            l.setStyle("-fx-stroke: " + myString);
            myCanvas.getChildren().add(l);
        }
    }

    public Menu getLanguageMenu(){
        return myLanguageTab.getMyMenu();
    }

    private boolean isOnCanvas(Node myNode){
        return myCanvas.getChildren().contains(myNode);
    }

    private BackgroundChangeable getBackgroundChanger(){
        BackgroundChangeable backgroundChanger = (root, backgroundColor) ->{
            VBox pane = (VBox) root.getLeft();
            Pane p = (Pane) pane.getChildren().get(0);
            p.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width:2px");
            root.setLeft(pane);
        };
        return backgroundChanger;
    }

    public void setOnRunButton(EventHandler<? super MouseEvent> handler){
        myControlButtons.setOnRun(handler);
    }

    private void setOnClearButton(){
        myControlButtons.setOnClear(e -> {
            clearConsole();
        });
    }

    public void clearConsole(){
        setConsole("");
    }

    public void setConsole(String text){
        myConsole.setText(text);
    }

    public String getCommand(){
        return myConsole.getText();
    }

    public History getHistory(){
        return myHistory;
    }

    public MenuItem getMyNewWindow(){
        return myWindow.getMyMenu().getItems().get(0);
    }

    public Menu getPenSizeMenu(){
        return myTools.getPenSizeSubMenu();
    }

    public Menu getPenColorMenu(){
        return myTools.getPenColorSubMenu();
    }

    public void addColorOption(String key){
        myTools.addColorOption(key);
    }

    public void setBackgroundColor(String backgroundRGB){
        getBackgroundChanger().changeBackground(myRoot, backgroundRGB); 
        setBackgroundPreference(backgroundRGB);
    }

    public Menu getBackgroundMenu(){
        return myTools.getBackgroundColorMenu();
    }

    private void setBackgroundPreference(String color){
        myPrefs.setBackground(color);
    }

    public void setOnSaveButtonClicked(EventHandler<? super MouseEvent> handler){
        myControlButtons.setOnSave(handler);
    }

    public void setOnLoadButtonClicked(EventHandler<? super MouseEvent> handler){
        myControlButtons.setOnLoad(handler);
    }

    /**
     * Clears lines in the screen 
     */
    public void clearTurtleLines(Turtleable turtle) {
        for(Line l : turtle.getLine()){
            myCanvas.getChildren().remove(l);
        }
    }

    public void setOnTogglePenClicked(EventHandler<? super MouseEvent> handler){
        myControlButtons.setOnTogglePen(handler);
    }


    public void addImage(ImageView myImageView){
        myHBox.getChildren().add(myImageView);   
    }

}  