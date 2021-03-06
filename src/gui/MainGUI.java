package gui;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
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
    private Pane myCanvas;
    public static final double TURTLE_PANE_WIDTH = 550;
    public static final double TURTLE_PANE_HEIGHT = 450;
    private String myCommandLanguage;

    public MainGUI(){
        myTurtle = new Turtle(true);
        myRoot = new BorderPane();
        myFileTab = new FileTab();
        myTools = new Tools();
        myLanguageTab = new Language();
        myConsole = new Console();
        myHelpTab = new Help();

        myHistory = new History();
        setHistoryClickables();

    }

    public Parent createRoot(){
        myRoot.setTop(createTop());
        myRoot.setLeft(createLeft());
        myRoot.setBottom(myConsole.getTextField());
        myRoot.setRight(myHistory.getMyHistoryVBox());
        myRoot.setPadding(new Insets(20));
        return myRoot;

    }

    private VBox createLeft(){
        VBox left = new VBox();
        left.setPadding(new Insets(20));
        left.getChildren().addAll(createTurtlePane(), myConsole.getTextField());
        return left;
    }

    private HBox createTop(){
        HBox top = new HBox();
        top.getChildren().addAll(addItemsInMenuBar());
        return top;
    }

    private void setBackgroundColorProps(){

    	for(MenuItem m : myTools.getBackgroundColorMenu().getItems()){
    		BackgroundChangeable p = myTools.getBackgroundChanger(m);
    		m.setOnAction(e -> {
        		p.changeBackground(myRoot);
    		});
    	}

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
        myCanvas.setLayoutX(20);
        myCanvas.setLayoutY(50);
        addTurtleOnScene();
        getLine();
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

    public Turtle getTurtle(){
        return myTurtle;
    }

    public History getHistory(){
        return myHistory;
    }

    //    private void addListeners(){
    //        myTurtle.getMyTurtleImageView().imageProperty().addListener(new ChangeListener<Image>(){
    //            @Override
    //            public void changed (ObservableValue<? extends Image> observable,
    //                                 Image oldValue,
    //                                 Image newValue) {
    //                System.out.println(" HERE ");
    //                // TODO Auto-generated method stub
    //            }
    //        });
    //    }

    private void addTurtleOnScene(){
        myFileTab.getNewTurtleItem().setOnAction(e -> {
            if(isOnCanvas(myTurtle.getMyTurtleImageView())){
                myCanvas.getChildren().remove(myTurtle.getMyTurtleImageView());
            }
            if(isOnCanvas(myTurtle.getMyLine())){
                myCanvas.getChildren().remove(myTurtle.getMyLine());
            }
            myTurtle= new Turtle(false);
            addTurtleOnCanvas();
            
            addLineOnCanvas();
        });
        addTurtleOnCanvas();
    }

    private void getLine(){
        for(MenuItem m: myTools.getPenColorSubMenu().getItems()) {
            m.setOnAction(e-> {
                myTurtle.setColor(m.getText());
            });};
            for(MenuItem m: myTools.getPenSizeSubMenu().getItems()) {
                m.setOnAction(e-> {
                    myTurtle.setPenWidth(Integer.parseInt(m.getText()));
                });
            }
            addLineOnCanvas();
    }

    private void addTurtleOnCanvas(){
        myCanvas.getChildren().add(myTurtle.getMyTurtleImageView());
    } 

    public String getLanguage(){
        Language myLanguage = new Language();
        myLanguage.getLanguageMenu().getItems().stream().forEach((menuitem) -> {
            menuitem.setOnAction( e -> {
                myCommandLanguage = menuitem.getText();
                
            });
        });
        return myCommandLanguage;
    }
    private void addLineOnCanvas(){
        myCanvas.getChildren().add(myTurtle.getMyLine());;
    }
    
    private boolean isOnCanvas(Node myNode){
        return myCanvas.getChildren().contains(myNode);
    }
    
}  








