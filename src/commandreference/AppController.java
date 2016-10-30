package commandreference;

import java.net.MalformedURLException;
import Actors.Actor;
import Simulation.SimulationController;
import Simulation.Node.InfoNode;
import gui.BackgroundChangeable;
import gui.FrontTurtle;
import gui.GUIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class AppController {
    SimulationController mySimulationController;
    private GUIController myGUIController;
    private TurtleManager myTurtleManager;
    private final String DEFAULT_TURTLE = "turtle.png";


    public AppController() {
        initializeGUIController();
        initializeSimulationController();
        myTurtleManager = new TurtleManager();
        setObservables();
    }

    public Parent initiateApp(){
        Parent mainRoot = myGUIController.init();
        setRunButton();
        addPenColorListener();
        addPenSizeListener();
        addColorMapListener();
        mySimulationController.getStorage().addNewActors(1, DEFAULT_TURTLE);
        setActiveID(1);
        setNewTurtleHandler();
        renderTurtles();
        return mainRoot;
    }

    private void initializeGUIController() {
        myGUIController = new GUIController();
    }

    private void initializeSimulationController() {
        mySimulationController = new SimulationController();
        mySimulationController.setLanguage(myGUIController.getLanguage());
    }

    private void setObservables(){
        setActorObserver();
        setFunctionObserver();
        setVariableListObserver();
    }

    private void setVariableListObserver() {
        mySimulationController.getStorage().getFunctionMap().addListener(new MapChangeListener<String, InfoNode>(){
            @Override
            public void onChanged(MapChangeListener.Change change) {
                String name = (String) change.getKey();
                myGUIController.addToVariableHistory(name);
            }
        });
    }

    private void setFunctionObserver() {
        mySimulationController.getStorage().getVariableMap().addListener(new MapChangeListener<String, Double>(){
            @Override
            public void onChanged(MapChangeListener.Change change){
                myGUIController.addToFunctionHistory((String) change.getKey());
            }
        });
    }

    private void setActorObserver() {
        mySimulationController.getStorage().getActorMap().addListener(new MapChangeListener<Integer, Actor>() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                Turtleable newTurtle = (Turtleable) change.getValueAdded();
                int id = (int) change.getKey();
                myTurtleManager.addTurtle(id, newTurtle);
                setCoordinateListeners(id, newTurtle);
                newTurtle.getImageView().setOnMouseClicked(e -> {
                    setActiveID(id);
                });
                updateTurtlesOnFront(myTurtleManager.getTurtleAtIndex(id));
            }
        });
    }

    private void setActiveID(int id) {
        mySimulationController.getStorage().setActive(id);
        myTurtleManager.setActiveTurtle(id);
        updateActiveLabels();
    }

    private void setCoordinateListeners(int id, Turtleable turtle){
        turtle.getX().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("changed");
                updateTurtlesOnFront(myTurtleManager.getTurtleAtIndex(id));
            }
        });
    }

    private void renderTurtles(){
        for(FrontTurtle turtle : myTurtleManager.getTurtles()){
            updateTurtlesOnFront(turtle);
        }
    }

    private void updateTurtlesOnFront(FrontTurtle turtle){
        myGUIController.addToScene(turtle);
    }

    private void setNewTurtleHandler(){
        myGUIController.getFileTab().getNewTurtleItem().setOnAction(e -> {
            try {
                String filePath = myGUIController.chooseFile().toURI().toURL().toString();
                int id = getUnusedID();
                mySimulationController.getStorage().addNewActors(id, filePath);
            } catch (MalformedURLException error) {
                error.printStackTrace();
            }
        });
    }

    private int getUnusedID(){
        int i = 0;
        for(i = 0; i < mySimulationController.getStorage().getActorMap().keySet().size(); i++){
            if(!mySimulationController.getStorage().getActorMap().keySet().contains(i)){
                return i;
            }
        }
        return i;
    }

    private void setRunButton(){
        myGUIController.setOnRunButton(e -> work());
    }

    private void work(){
        sendCommand();
        renderTurtles();
        myGUIController.clearConsole();
        updateActiveLabels();
    }

    private void updateActiveLabels(){
        myGUIController.updateActiveLabels(myTurtleManager.getActiveID(), myTurtleManager.getTurtleAtIndex(myTurtleManager.getActiveID()));
    }

    private void sendCommand(){
        Double index= 0.0;
        if(!myGUIController.getCommandEntered().isEmpty()){
            myGUIController.addToCommandHistory(myGUIController.getCommandEntered());
            mySimulationController.receive(myGUIController.getCommandEntered());
        }    
    }

    private void addPenColorListener(){
        myGUIController.getPenColorCommand().addListener(new ChangeListener <String>(){
            @Override
            public void changed (ObservableValue<? extends String> observable,
                                 String oldValue,
                                 String newValue) {
                Double colorIndex = mySimulationController.receive(newValue.toString());
                updateLineColor(colorIndex);
                
            }
        });
    }

    private void addPenSizeListener(){
        myGUIController.getPenSizeCommand().addListener( new ChangeListener <String>(){
            @Override
            public void changed (ObservableValue<? extends String> observable,
                                 String oldValue,
                                 String newValue) {
                Double sizeIndex = mySimulationController.receive(newValue.toString());
                updateLineSize(sizeIndex);
            }
        });   
    }

    private void addColorMapListener(){
        mySimulationController.getStorage().getPalette().addListener(new MapChangeListener<Integer, int[]>() {
            @Override
            public void onChanged (javafx.collections.MapChangeListener.Change<? extends Integer, ? extends int[]> change) {
                myGUIController.addColorOption(change.getKey().toString());
                
            }
        });
    }
    
    public MenuItem getNewWindowMenu(){
        return myGUIController.getNewWindowMenu();
    }
    
    private void updateLineColor(Double colorIndex){
        int[] colorValues = mySimulationController.getStorage().getPalette().get(colorIndex.intValue());
        String hex = String.format( "#%02X%02X%02X",
                    (int)( colorValues[0]* 255 ),(int)( colorValues[1] * 255 ),(int)( colorValues[2] * 255 ) );           
        Paint myColor = Paint.valueOf(hex);
        FrontTurtle activeTurtle = myTurtleManager.getActiveTurtle();
        activeTurtle.setLineColor(myColor);
    }
    
    private void updateLineSize(Double lineSize){
        FrontTurtle activeTurtle = myTurtleManager.getActiveTurtle();
        activeTurtle.setLineWidth(lineSize);
    }
}