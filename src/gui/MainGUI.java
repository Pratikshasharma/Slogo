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
    //    private MenuBar myMenuBar = new MenuBar();
    //  VBox myVBox;
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

        myFileTab = new FileTab();
        myTools = new Tools();
        myLanguageTab = new Language();
        myConsole = new Console();
        myHelpTab = new Help();
        myHistory = new History(myConsole);
    }

    public Parent createRoot(){
        BorderPane root = new BorderPane();
        //        Group root = new Group();
        //        myVBox = new VBox(30);
        //        addItemsInMenuBar();
        //        myVBox.getChildren().addAll(myMenuBar,createTurtlePane(),myConsole.getTextField());
        //        root.getChildren().add(myVBox);
        //    	addItemsInMenuBar();
        
        root.setTop(createTop());
        root.setLeft(createLeft());
        root.setBottom(myConsole.getTextField());
        root.setRight(myHistory.getMyHistoryVBox());
        root.getChildren().add(myTurtle.getMyTurtleImageView());
        root.setPadding(new Insets(20));
        return root;
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
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width:4px");
        canvas.setLayoutX(20);
        canvas.setLayoutY(50);
        canvas.setPrefSize(TURTLE_PANE_WIDTH,TURTLE_PANE_HEIGHT);
        return canvas;
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