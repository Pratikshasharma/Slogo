package gui;

import java.net.MalformedURLException;

import commandreference.HTMLReferencePage;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import navigationTabs.FileTab;
import navigationTabs.Help;
import navigationTabs.Language;
import navigationTabs.Tools;

public class MainGUI {
    private Turtle myTurtle;
    private FileTab myFileTab;
    private Language myLanguageTab; 
    private Tools myTools;
    private Console myConsole;
    private History myHistory;
    private Help myHelpTab;
    private BorderPane myRoot;
    public static final double TURTLE_PANE_WIDTH = 550;
    public static final double TURTLE_PANE_HEIGHT = 450;

    public MainGUI(){
        try {
            myTurtle = new Turtle();
        }
        catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        myRoot = new BorderPane();
        myFileTab = new FileTab();
        myTools = new Tools(myRoot);
        myLanguageTab = new Language();
        myConsole = new Console();
        myHelpTab = new Help();
        myHistory = new History(myConsole);
    }

    public Parent createRoot(){
        myRoot.setTop(createTop());
        myRoot.setLeft(createLeft());
        myRoot.setBottom(myConsole.getTextField());
        myRoot.setRight(myHistory.getMyHistoryVBox());
        myRoot.getChildren().add(myTurtle.getMyTurtleImageView());
        myRoot.setPadding(new Insets(20));
        return myRoot;
    }

    private VBox createLeft(){
        VBox left = new VBox();
        left.setPadding(new Insets(20));
        left.getChildren().addAll(createTurtlePane(),myConsole.getTextField());
        return left;
    }
    private HBox createTop(){
        HBox top = new HBox();
        top.getChildren().addAll(addItemsInMenuBar());
        return top;
    }

    private Pane createTurtlePane(){
        Pane myCanvas = new Pane();
        myCanvas.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width:4px");
        myCanvas.setLayoutX(20);
        myCanvas.setLayoutY(50);
        myCanvas.setPrefSize(TURTLE_PANE_WIDTH,TURTLE_PANE_HEIGHT);
        return myCanvas;
    }

    private MenuBar addItemsInMenuBar(){
        //createCommandReferenceButton();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(myFileTab.getMyMenu(), myTools.getMyMenu(),myLanguageTab.getMyMenu(),myHelpTab.getMyMenu());
        return menuBar;
    }

//    private Button createCommandReferenceButton() {
//        Button htmlReference = new Button("Command References");
//        htmlReference.setOnAction(e -> openHTMLReference());
//        return htmlReference;
//    }

//    private void openHTMLReference(){
//        HTMLReferencePage page = new HTMLReferencePage();
//        page.getPage();
//    }

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