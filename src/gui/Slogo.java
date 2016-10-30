package gui;
import java.util.HashMap;
import java.util.Random;
import commandreference.AppController;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class Slogo {
    private TabPane myTabPane;
    private final String TAB_TITLE = "Slogo ";
    BorderPane root;

    ObservableMap<Integer,AppController> myMultipleWindowMap; 
    public TabPane getTab(){
        return myTabPane;
    }

    public Scene startSlogo(){
        myMultipleWindowMap = FXCollections.observableMap(new HashMap<Integer,AppController>());
        myTabPane = new TabPane();
        root = new BorderPane();
        setNewWindowObserver();
        Scene scene = createFirstWindow();  
        return scene;
    }

    private Integer getId(){
        Random rand = new Random();
        int randomInteger = rand.nextInt();
        while(myMultipleWindowMap.containsKey(randomInteger)){
            randomInteger = rand.nextInt();
        }
        return randomInteger;
    }

    private void addNewTab(){
        AppController myAppController = new AppController();
        myMultipleWindowMap.put(getId(),myAppController);
        Tab myTab = new Tab(TAB_TITLE + myMultipleWindowMap.size());
        myTab.setContent(myAppController.initiateApp());
        myTabPane.getTabs().add(myTab);
    }

    private Scene createFirstWindow(){
        addNewTab();
        root.setCenter(myTabPane);
        Scene scene = new Scene(root,GUIController.SCENE_WIDTH,GUIController.SCENE_HEIGHT);
        // scene.setOnKeyPressed(e -> myAppController.handleKeyInput(e.getCode()));  
        return scene;
    }

    private void setNewWindowObserver(){
        myMultipleWindowMap.addListener( new MapChangeListener<Integer,AppController>(){
            @Override
            public void onChanged (javafx.collections.MapChangeListener.Change<? extends Integer, ? extends AppController> change) {
                change.getValueAdded().getNewWindowMenu().setOnAction(e -> addNewTab());
            }
        });
    }   
}