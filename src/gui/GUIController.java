package gui;

import java.io.File;

import commandreference.Turtleable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import navigationTabs.FileTab;

public class GUIController {

	private MainGUI myMainGUI;
	public static final double SCENE_WIDTH = 900;
	public static final double SCENE_HEIGHT = 650;
	public static final String DEFAULT_LANGUAGE = "English";
	private String myCommandLanguage;
	
	public GUIController() {
		myMainGUI = new MainGUI();
	}

	public Scene init(){
		Scene myScene = new Scene(myMainGUI.createRoot(),SCENE_WIDTH,SCENE_HEIGHT,Color.WHITE);
		setLanguage();
		return myScene;
	}
	
	public void setActiveTurtle(Turtleable turtle){
		myMainGUI.updateActiveTurtleInfo(turtle);
	}

	public Console getConsole(){
		return myMainGUI.getConsole();
	}

	public FileTab getFileTab(){
		return myMainGUI.getMyFileTab();
	}

	public History getHistory(){
		return myMainGUI.getHistory();
	}

	public String getCommandEntered(){
		return myMainGUI.getConsole().getTextField().getText();
	}

	public void updateLocation(){
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
		myMainGUI.addTurtleOnCanvas(t);
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

	public Button getRunButton(){
		return myMainGUI.getRunButton();
	}
	
	public void updateActiveLabels(Turtleable turtle){
		myMainGUI.updateActiveTurtleInfo(turtle);
	}
}

