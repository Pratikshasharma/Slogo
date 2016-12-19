package gui;

import java.io.File;
import commandreference.Turtleable;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import navigationTabs.FileTab;

/**
 * Purpose: Controls execution between the front end objects 
 * Dependencies: MainGUI, File Chooser (Java Fx)
 * @author  Teddy,pratikshasharma
 */

public class GUIController implements IGUIController{
    private MainGUI myMainGUI;
    public static final double SCENE_WIDTH = 900;
    public static final double SCENE_HEIGHT = 800;
    public static final String DEFAULT_LANGUAGE = "English";
    private SimpleStringProperty myCommandLanguage = new SimpleStringProperty(DEFAULT_LANGUAGE);

    public GUIController() {
        myMainGUI = new MainGUI();
    }

    /**Returns the main root for the Scene
     * @return Parent 
     */
    public Parent init(){
        setLanguage();
        return myMainGUI.createRoot();
    }


    public void setActiveTurtle(int id, Turtleable turtle){
        myMainGUI.updateActiveTurtleInfo(id, turtle);
    }

    public void clearConsole(){
        myMainGUI.clearConsole();
    }

    /**
     * Sets the received text as the Text on the Console
     */
    public void setConsole(String text){
        myMainGUI.setConsole(text);
    }
    /**
     * Returns "NewFile" Menu Option that lets the user select a New turtle
     */
    public FileTab getFileTab(){
        return myMainGUI.getMyFileTab();
    }

    public void addToCommandHistory(String command){
        myMainGUI.getHistory().addToCommandHistory(command);
    }

    public void addToVariableHistory(String variable){
        myMainGUI.getHistory().addToVariableList(variable);
    }

    public void addToFunctionHistory(String function){
        myMainGUI.getHistory().addToFunctionsHistory(function);
    }

    public String getCommandEntered(){
        return myMainGUI.getCommand();
    }

    /**
     * Lets user choose a turtle file by opening file chooser
     * @return Turtle Image File
     */
    public File chooseFile(){
//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif");
//        String userDirectoryString = System.getProperty("user.dir") + File.separator + "images";
//        File userDirectory = new File(userDirectoryString);
//        fileChooser.setInitialDirectory(userDirectory);
//        fileChooser.getExtensionFilters().add(extentionFilter);
//        Stage myStage = new Stage();
//        File chosenFile = fileChooser.showOpenDialog(myStage);
//        return chosenFile;
        FileOpener myFileOpener = new FileOpener();
        return myFileOpener.chooseFile();
    }

    /**
     * Adds turtle to scene
     */
    public void addToScene(Turtleable t){
        myMainGUI.updateTurtleLocation(t);
    }

    private void setLanguage(){
        myMainGUI.getLanguageMenu().getItems().stream().forEach((menuItem) -> {
            menuItem.setOnAction( e -> {
                myCommandLanguage.set(menuItem.getText()); 
                return;
            }); 
        });
    }

    public String getLanguage(){
        return myCommandLanguage.get();
    }


    public void setOnRunButton(EventHandler<? super MouseEvent> handler){
        myMainGUI.setOnRunButton(handler);
    }

    public void updateActiveLabels(int id, Turtleable turtle){
        myMainGUI.updateActiveTurtleInfo(id, turtle);
    }

    public MenuItem getNewWindowMenu(){
        return myMainGUI.getMyNewWindow();
    }

    /**
     * Adds Color option in the Menu when set by the user through console
     */
    public void addColorOption(String index){
        myMainGUI.addColorOption(index);
    }

    public void setBackgroundColor(String rgb){
        myMainGUI.setBackgroundColor(rgb);
    }

    public void setOnSaveButtonClicked(EventHandler<? super MouseEvent> handler){
        myMainGUI.setOnSaveButtonClicked(handler);
    }

    public void setOnLoadButtonClicked(EventHandler<? super MouseEvent> handler){
        myMainGUI.setOnLoadButtonClicked(handler);
    }

    public void clearTurtleLines(Turtleable turtle){
        myMainGUI.clearTurtleLines(turtle);
    }

    public Menu getPenColorMenu(){
        return myMainGUI.getPenColorMenu();
    }

    public Menu getPenSizeMenu(){
        return myMainGUI.getPenSizeMenu();
    }

    public Menu getBackgroundMenu(){
        return myMainGUI.getBackgroundMenu();
    }

    public boolean functionHistoryContains(String func){
        return myMainGUI.getHistory().getFunctionsList().getItems().contains(func);
    }

    public void setOnTogglePen(EventHandler<? super MouseEvent> handler){
        myMainGUI.setOnTogglePenClicked(handler);
    }
}