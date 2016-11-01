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

public class GUIController {
    private MainGUI myMainGUI;
    public static final double SCENE_WIDTH = 900;
    public static final double SCENE_HEIGHT = 800;
    public static final String DEFAULT_LANGUAGE = "English";
    private SimpleStringProperty myCommandLanguage = new SimpleStringProperty(DEFAULT_LANGUAGE);
 
    public GUIController() {
        myMainGUI = new MainGUI();
    }

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

    public void setConsole(String text){
        myMainGUI.setConsole(text);
    }

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

    public File chooseFile(){
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
<<<<<<< HEAD
=======

    
    public void addPenSizeListeners (){
        myMainGUI.getPenSizeMenu().getItems().stream().forEach(size -> size.setOnAction(e-> {
           penSizeCommand.set( PEN_SIZE_COMMAND + " " + size.getText());    
        }));
    }
        
        public void addPenColorListeners(){
        myMainGUI.getPenColorMenu().getItems().stream().forEach(color ->color.setOnAction( e-> {
            penColorCommand.set(PEN_COLOR_COMMAND + " " + color.getText());
        }));
    }
    
    public void addBackGroundListeners(){
        myMainGUI.getBackgroundMenu().getItems().stream().forEach(item -> item.setOnAction( e->{
            backgroundColorCommand.set(BACKGROUND_COMMAND + " " + item.getText());
        }));
    }
    
    public SimpleStringProperty getPenColorCommand(){
        return this.penColorCommand;
    }

    public SimpleStringProperty getPenSizeCommand(){
        return this.penSizeCommand;
    }
>>>>>>> d5a8f642dae57ee3f100611eb802a80a72e58689
    
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
}