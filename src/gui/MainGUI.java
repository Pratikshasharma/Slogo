package gui;

import javafx.event.EventHandler;
import commandreference.ControlButtons;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

public class MainGUI {
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
	private ActiveTurtleDisplayInformation myActiveTurtleInfo;
	public static final double TURTLE_PANE_WIDTH = 550;
	public static final double TURTLE_PANE_HEIGHT = 450;

	public MainGUI() {
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

	public void updateActiveTurtleInfo(int id, FrontTurtle turtle){
		myActiveTurtleInfo.updateStatus(id, turtle);
	}

	private HBox createBottom(){
		HBox bottomBox = new HBox(20);
		VBox activeLabels = new VBox(20);
		VBox controlButtons = new VBox(20);
		setOnClearButton();
		controlButtons.getChildren().addAll(myControlButtons.getRunButton(), myControlButtons.getClearButton(), myControlButtons.getTogglePenButton());
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
		setBackgroundColorProps();
		myCanvas.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width:4px");
		myCanvas.setPrefSize(TURTLE_PANE_WIDTH,TURTLE_PANE_HEIGHT);
		return myCanvas;
	}

	private MenuBar addItemsInMenuBar(){
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(myFileTab.getMyMenu(), myTools.getMyMenu(), myLanguageTab.getMyMenu(), myHelpTab.getMyMenu(), myWindow.getMyMenu());
		return menuBar;
	}

	private void addTurtleOnScene(FrontTurtle turtle){
		if(!isOnCanvas(turtle.getImageView())){
			myCanvas.getChildren().add(turtle.getImageView());
		}
	}
	public FileTab getMyFileTab(){
		return myFileTab;
	}
	
	public void addTurtleOnCanvas(FrontTurtle turtle){
		Line myLine = new Line();
		myLine.setStartX(turtle.getImageView().getX() + turtle.getImageView().getFitWidth()/2);
		myLine.setStartY(turtle.getImageView().getY());
		turtle.getImageView().setX(turtle.getCoordinates().getX().get());
		turtle.getImageView().setY(turtle.getCoordinates().getY().get());
		addTurtleOnScene(turtle);
		if(turtle.isPenUp()){
			addLineOnCanvas(turtle, myLine);
		}
	}

	private void addLineOnCanvas(FrontTurtle turtle, Line line){
		line.setEndX(turtle.getImageView().getX());
		line.setEndY(turtle.getImageView().getY());
		myCanvas.getChildren().add(line);
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

	public void setOnRunButton(EventHandler<? super MouseEvent> handler){
		myControlButtons.setOnRun(handler);
	}
	
	private void setOnClearButton(){
		myControlButtons.setOnClear(e -> {
			myConsole.clear();
		});
	}
	
	public void clearConsole(){
		myConsole.clear();
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
	   return  myWindow.getMyMenu().getItems().get(0);
	}
}  

