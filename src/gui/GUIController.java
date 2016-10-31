package gui;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
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
    private static final String BACKGROUND_COMMAND = "SetBackground";
    private static final String PEN_COLOR_COMMAND = "SetPenColor" ;
    //private static final String SET_PALETTE_COMMAND = "SetPalette";
    private static final String PEN_SIZE_COMMAND = "SetPenSize";

    private SimpleStringProperty myCommandLanguage = new SimpleStringProperty(DEFAULT_LANGUAGE);
    private SimpleStringProperty penSizeCommand = new SimpleStringProperty();
    private SimpleStringProperty penColorCommand = new SimpleStringProperty();


    public GUIController() {
        myMainGUI = new MainGUI();
    }

    public Parent init(){
        //Scene myScene = new Scene(myMainGUI.createRoot(),SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
        setLanguage();
        addSizeColorListeners();
        return myMainGUI.createRoot();
    }

    public void setActiveTurtle(int id, FrontTurtle turtle){
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

    public void addToScene(FrontTurtle t){
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
        return this.myCommandLanguage.get();
    }

    public void setOnRunButton(EventHandler<? super MouseEvent> handler){
        myMainGUI.setOnRunButton(handler);
    }

    public void updateActiveLabels(int id, FrontTurtle turtle){
        myMainGUI.updateActiveTurtleInfo(id, turtle);
    }

    public MenuItem getNewWindowMenu(){
        return myMainGUI.getMyNewWindow();
    }

    private void addSizeColorListeners (){
       // penColorCommand.set( PEN_COLOR_COMMAND + "1"); 
        //penSizeCommand.set(PEN_SIZE_COMMAND + "1");
        myMainGUI.getPenSizeMenu().getItems().stream().forEach(size -> size.setOnAction(e-> {
            String penSizeIndex = size.getText();
            penSizeCommand.set( PEN_SIZE_COMMAND + " " + penSizeIndex);
        }));
        
        myMainGUI.getPenColorMenu().getItems().stream().forEach(color ->color.setOnAction( e-> {
            String penColorIndex = color.getText();
            penColorCommand.set(PEN_COLOR_COMMAND + " " + penColorIndex);
        }));  
    }

    public SimpleStringProperty getPenColorCommand(){
        return this.penColorCommand;
    }

    public SimpleStringProperty getPenSizeCommand(){
        return this.penSizeCommand;
    }
    public void addColorOption(String index){
        myMainGUI.addColorOption(index);
    }
}
