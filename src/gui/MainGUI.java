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
    private UserDefaults myPrefs;
    private ActiveTurtleDisplayInformation myActiveTurtleInfo;
    public static final double TURTLE_PANE_WIDTH = 550;
    public static final double TURTLE_PANE_HEIGHT = 450;
    private String backgroundColor;

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
        myPrefs = new UserDefaults(this.getClass().toString());
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
        //setBackgroundColorProps();    
        setPenColorProps();
        setPenWidthProps();
        left.getChildren().addAll(createTurtlePane(), myConsole.getTextField());
        return left;
    }

    private HBox createTop(){
        HBox top = new HBox(20);
        top.getChildren().addAll(addItemsInMenuBar());
        return top;
    }

    //	private void setBackgroundColorProps(){
    //		for(MenuItem m : myTools.getBackgroundColorMenu().getItems()){
    //			BackgroundChangeable p = getBackgroundChanger(m);
    //			m.setOnAction(e -> {
    //				p.changeBackground(myRoot);
    //				myPrefs.setBackground(m.getText().toLowerCase());
    //			});
    //		}
    //	}

    private void setPenColorProps(){
        for(MenuItem m : myTools.getPenColorSubMenu().getItems()){
            m.setOnAction(e -> {
                myPrefs.setPenColor(m.getText().toLowerCase());
            });
        }
    }

    private void setPenWidthProps(){
        for(MenuItem m : myTools.getPenSizeSubMenu().getItems()){
            m.setOnAction(e -> {
                myPrefs.setPenWidth(Integer.valueOf(m.getText().toLowerCase()));
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
        //setBackgroundColorProps();
        myCanvas.setStyle("-fx-background-color: " + myPrefs.getBackground("white") + "; -fx-border-color: black; -fx-border-width: 4px");
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
            turtle.getImageView().setX(turtle.getCoordinates().getX().get());
            turtle.getImageView().setY(turtle.getCoordinates().getY().get());
            myCanvas.getChildren().add(turtle.getImageView());
        }
    }

    public FileTab getMyFileTab(){
        return myFileTab;
    }

    public void updateTurtleLocation(FrontTurtle turtle){
        double x = turtle.getImageView().getX();
        double y = turtle.getImageView().getY();
        System.out.println(x + " " + y);
        turtle.getImageView().setX(turtle.getCoordinates().getX().get());
        turtle.getImageView().setY(turtle.getCoordinates().getY().get());
        if(x == 0 && y == 0){
            addTurtleOnScene(turtle);
            return;
        }
        if(turtle.isPenUp()){
            addLineOnCanvas(turtle, x, y);
        }
    }

    private void addLineOnCanvas(FrontTurtle turtle, double x, double y){
        System.out.println("line added");
        myCanvas.getChildren().add(turtle.drawLine(x, y, turtle.getCoordinates().getX().get(), turtle.getCoordinates().getY().get()));
    }

    public Menu getLanguageMenu(){
        return myLanguageTab.getMyMenu();
    }

    private boolean isOnCanvas(Node myNode){
        return myCanvas.getChildren().contains(myNode);
    }

    public BackgroundChangeable getBackgroundChanger(){
        BackgroundChangeable backgroundChanger = (root) ->{
            VBox pane = (VBox) root.getLeft();
            Pane p = (Pane) pane.getChildren().get(0);
            p.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width:4px");
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
        return myWindow.getMyMenu().getItems().get(0);
    }

    public Menu getPenSizeMenu(){
        return myTools.getPenSizeSubMenu();
    }

    public Menu getPenColorMenu(){
        return myTools.getPenColorSubMenu();
    }
    
    public void addColorOption(String key){
        MenuItem newOption = new MenuItem(key);
        myTools.getPenColorSubMenu().getItems().add(newOption);
    }

    public void setBackgroundColor(String backgroundRGB){
        backgroundColor = backgroundRGB;
        BackgroundChangeable p = getBackgroundChanger();
        p.changeBackground(myRoot);    
    }

    //    private BackgroundChangeable getBackgroundChanger(){
    //        BackgroundChangeable backgroundChanger = (root) -> {  
    //            System.out.println(" Color " + backgroundColor.toString());
    //            VBox pane = (VBox) root.getLeft();
    //            Pane p = (Pane) pane.getChildren().get(0);
    //            p.setStyle("-fx-background-color: " + backgroundColor + "; -fx-border-color: black; -fx-border-width:4px");
    //            root.setLeft(pane);
    //        };
    //        return backgroundChanger;
    //    }

    public Menu getBackgroundMenu(){
        return myTools.getBackgroundColorMenu();
    }
}  