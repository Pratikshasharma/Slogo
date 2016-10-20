package gui;

import java.net.MalformedURLException;

import commandreference.HTMLReferencePage;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import navigationTabs.FileTab;
import navigationTabs.Language;
import navigationTabs.Tools;

public class MainGUI {
    private Turtle myTurtle;
    private FileTab myFileTab;
    private Language myLanguageTab; 
    private Tools myTools;
    private Console myConsole;
    private History myHistory;
//    private MenuBar myMenuBar = new MenuBar();
    VBox myVBox;
    public static final double TURTLE_PANE_WIDTH = 550;
    public static final double TURTLE_PANE_HEIGHT = 450;

    public MainGUI(){
    	try {
    		myTurtle = new Turtle();
    	} catch (MalformedURLException e){
    		
    	}
        myFileTab = new FileTab();
        myTools = new Tools();
        myLanguageTab = new Language();
        myConsole = new Console();
        myHistory = new History();
    }
    
    public Parent createRoot(){
    	BorderPane root = new BorderPane();
//        Group root = new Group();
//        myVBox = new VBox(30);
//        myVBox.setPadding(new Insets(20));
//        addItemsInMenuBar();
//        myVBox.getChildren().addAll(myMenuBar,createTurtlePane(),myConsole.getTextField());
//        root.getChildren().add(myVBox);
//        root.getChildren().add(myTurtle.getMyTurtleImageView());
//    	addItemsInMenuBar();
    	root.setTop(createTop());
    	root.setLeft(createTurtlePane());
    	root.setBottom(myConsole.getTextField());
    	root.setRight(myHistory.getMyHistoryVBox());
        return root;
    }
    
    private HBox createTop(){
    	HBox top = new HBox();
    	top.getChildren().addAll(addItemsInMenuBar(), createCommandReferenceButton());
    	return top;
    }
    
    private Pane createTurtlePane(){
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width:4px");
        canvas.setLayoutX(20);
        canvas.setLayoutY(50);
        canvas.setPrefSize(TURTLE_PANE_WIDTH,TURTLE_PANE_HEIGHT);
        return canvas;
    }
    
    private MenuBar addItemsInMenuBar(){
    	createCommandReferenceButton();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(myFileTab.getMyMenu(), myTools.getMyMenu(),myLanguageTab.getMyMenu());
        return menuBar;
    }

	private Button createCommandReferenceButton() {
		Button htmlReference = new Button("Command References");
    	htmlReference.setOnAction(e -> openHTMLReference());
    	return htmlReference;
	}
    
    private void openHTMLReference(){
    	HTMLReferencePage page = new HTMLReferencePage();
    	page.getPage();
    }
    
    public Console getConsole(){
    	return myConsole;
    }
    
    public Turtle getTurtle(){
    	return myTurtle;
    }
    
    public History getHistory(){
    	return myHistory;
    }
}
