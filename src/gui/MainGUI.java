package gui;

import commandreference.Turtleable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import navigationTabs.FileTab;
import navigationTabs.Help;
import navigationTabs.Language;
import navigationTabs.Tools;

public class MainGUI {

	private FileTab myFileTab;
	private Language myLanguageTab; 
	private Tools myTools;
	private Console myConsole;
	private History myHistory;
	private Help myHelpTab;
	private BorderPane myRoot;
	private Pane myCanvas;
	private Button runButton;
	private Button clearButton;
	private ActiveTurtleDisplayInformation myActiveTurtleInfo;
	public static final double TURTLE_PANE_WIDTH = 550;
	public static final double TURTLE_PANE_HEIGHT = 450;

	public MainGUI(){
		myRoot = new BorderPane();
		myFileTab = new FileTab();
		myTools = new Tools();
		myLanguageTab = new Language();
		myConsole = new Console();
		myHelpTab = new Help();
		myHistory = new History();
		myActiveTurtleInfo = new ActiveTurtleDisplayInformation();
		setHistoryClickables();
	}

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
		left.setPadding(new Insets(20));   
		setBackgroundColorProps();     
		left.getChildren().addAll(createTurtlePane(), myConsole.getTextField());
		return left;
	}

	private HBox createTop(){
		HBox top = new HBox(20);
		top.getChildren().addAll(addItemsInMenuBar());
		return top;
	}

	private void setBackgroundColorProps(){
		for(MenuItem m : myTools.getBackgroundColorMenu().getItems()){
			BackgroundChangeable p = getBackgroundChanger(m);
			m.setOnAction(e -> {
				p.changeBackground(myRoot);
			});
		}
	}
	
	public void updateActiveTurtleInfo(Turtleable turtle){
		myActiveTurtleInfo.updateStatus(turtle);
	}

	private HBox createBottom(){
		HBox bottomBox = new HBox(20);
		makeConsoleControlButtons();
		VBox activeLabels = new VBox();
		activeLabels.getChildren().addAll(myActiveTurtleInfo.getIDLabel(), myActiveTurtleInfo.getCurrentOrienation(), myActiveTurtleInfo.getPenStatus());
		bottomBox.getChildren().addAll(myConsole.getTextField(), runButton, clearButton, activeLabels);
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
		setBackgroundColorProps();
		myCanvas.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width:4px");
		myCanvas.setPrefSize(TURTLE_PANE_WIDTH,TURTLE_PANE_HEIGHT);
		return myCanvas;
	}

	private MenuBar addItemsInMenuBar(){
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(myFileTab.getMyMenu(), myTools.getMyMenu(), myLanguageTab.getMyMenu(), myHelpTab.getMyMenu());
		return menuBar;
	}

	public Console getConsole(){
		return myConsole;
	}

	public History getHistory(){
		return myHistory;
	}

	private void addTurtleOnScene(Turtleable turtle){
		if(!isOnCanvas(turtle.getImageView())){
			myCanvas.getChildren().add(turtle.getImageView());
		}
	}

	public FileTab getMyFileTab(){
		return myFileTab;
	}

	public void addTurtleOnCanvas(Turtleable turtle){
		turtle.getImageView().setX(turtle.getX().get());
		turtle.getImageView().setY(turtle.getY().get());
		addTurtleOnScene(turtle);
		if(turtle.getPenStatus()){
			addLineOnCanvas(turtle);
		}
	}

	private void addLineOnCanvas(Turtleable turtle){
		if(!isOnCanvas(turtle.getLine())){
			turtle.getLine().setStartX(turtle.getImageView().getX());
			turtle.getLine().setStartY(turtle.getImageView().getY());
			myCanvas.getChildren().add(turtle.getLine());;
		} else {
			turtle.getLine().setEndX(turtle.getImageView().getX());
			turtle.getLine().setEndY(turtle.getImageView().getY());
		}
	}

	public Menu getLanguageMenu(){
		return myLanguageTab.getMyMenu();
	}

	private boolean isOnCanvas(Node myNode){
		return myCanvas.getChildren().contains(myNode);
	}

	public BackgroundChangeable getBackgroundChanger(MenuItem m){
		BackgroundChangeable backgroundChanger = (root) -> {
			VBox pane = (VBox) root.getLeft();
			Pane p = (Pane) pane.getChildren().get(0);
			p.setStyle("-fx-background-color: " + m.getText().toLowerCase() + "; -fx-border-color: black; -fx-border-width:4px");
			root.setLeft(pane);
		};
		return backgroundChanger;
	}

	private void makeConsoleControlButtons(){
		runButton = setButton("RunButtonCommand",0.8*GUIController.SCENE_WIDTH, 0.9*GUIController.SCENE_HEIGHT/9);
		clearButton = setButton("ClearButtonCommand",0.9*GUIController.SCENE_WIDTH/9, 0.9*GUIController.SCENE_HEIGHT);
		clearButton.setOnAction(e -> myConsole.getTextField().clear());
	}

	private Button setButton(String property, double xPosition, double yPosition){
		ButtonTemplate buttonCreator = new ButtonTemplate(property);
		buttonCreator.changeButtonSettings(xPosition, yPosition);
		return buttonCreator.getButton();
	}

	public Button getRunButton(){
		return runButton;
	}
}  

