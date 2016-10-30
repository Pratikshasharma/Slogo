package gui;

import java.io.File;
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
	private String myCommandLanguage;
	
	public GUIController() {
		myMainGUI = new MainGUI();
	}

	public Parent init(){
		//Scene myScene = new Scene(myMainGUI.createRoot(),SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
		setLanguage();
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
			menuItem.setOnAction( e -> myCommandLanguage = menuItem.getText());
		});  
	}  

	public String getLanguage(){
		if(myCommandLanguage.length()!=0){ return this.myCommandLanguage; };
		myCommandLanguage = DEFAULT_LANGUAGE;
		return myCommandLanguage;  
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
}

