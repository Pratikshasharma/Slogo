package commandreference;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import Actors.Actor;
import Simulation.SimulationController;
import Simulation.Node.InfoNode;
import gui.ErrorAlert;
//import gui.FrontTurtle;
import gui.GUIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class AppController {

    private class CoordinateObserver implements Observer {
        private int myID;
        CoordinateObserver(int id){
            myID = id;
        }
        @Override
        public void update(java.util.Observable o, Object arg) {
            updateTurtlesOnFront(myTurtleManager.getTurtleAtIndex(myID));
        }
    }

    private SimulationController mySimulationController;
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
        mySimulationController.getStorage().addNewActors(1, DEFAULT_TURTLE);
        setActiveID(1);
        setNewTurtleHandler();
        updateTurtlesOnFront(myTurtleManager.getTurtleAtIndex(1));
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
        addBackgroundColorListener();
        //addPenColorListener();
        //addPenSizeListener();
        addColorMapListener();
    }

    private void setVariableListObserver() {
        mySimulationController.getStorage().getVariableMap().addListener(new MapChangeListener<String, Double>(){
            @Override
            public void onChanged(MapChangeListener.Change change) {
                String name = (String) change.getKey();
                myGUIController.addToVariableHistory(name);
            }
        });
    }

    private void setFunctionObserver() {
        mySimulationController.getStorage().getFunctionMap().addListener(new MapChangeListener<String, InfoNode>(){
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
                processNewTurtle(newTurtle, id);
                addPenColorListener(newTurtle);
                addPenSizeListener(newTurtle);
                updateTurtlesOnFront(myTurtleManager.getTurtleAtIndex(id));
            }
        });
    }
    
    private void setActiveID(int id) {
        mySimulationController.getStorage().setActive(id);
        myTurtleManager.setActiveTurtle(id);
        updateActiveLabels();
    }
    
    private void processNewTurtle(Turtleable newTurtle, int id) {
        myTurtleManager.addTurtle(id, newTurtle);
        setCoordinateListeners(id, newTurtle);
        setResetListener(id, newTurtle);
        newTurtle.getImageView().setOnMouseClicked(e -> {
            setActiveID(id);
        });
    }

    private void setCoordinateListeners(int id, Turtleable turtle){
        turtle.getCoordinates().addObserver(new CoordinateObserver(id));
    }

    private void setResetListener(int id, Turtleable turtle){
        turtle.getReset().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                myGUIController.clearTurtleLines(myTurtleManager.getTurtleAtIndex(id));
                myTurtleManager.getTurtleAtIndex(id).clearLines();
            }

        });
    }
    
//    private Animatable prepareAnimation(){
//    	Animatable a = (turtle) -> {
//    		myGUIController.addToScene(turtle);
//    	};
//    	return a;
//    }

//    private void updateTurtlesOnFront(FrontTurtle turtle){
////    	AnimationManager a = new AnimationManager(prepareAnimation(), turtle);
////    	a.startAnimation();
//        myGUIController.addToScene(turtle);
//    }
    
    private void updateTurtlesOnFront(Turtleable turtle){
    	myGUIController.addToScene(turtle);
    }

    private void setNewTurtleHandler(){
        myGUIController.getFileTab().getNewTurtleItem().setOnAction(e -> {
            try {
                String filePath = myGUIController.chooseFile().toURI().toURL().toString();
                int id = getUnusedID();
                mySimulationController.getStorage().addNewActors(id, filePath);
            } catch (MalformedURLException error) {
                ErrorAlert alert = new ErrorAlert("Image File not Loaded");
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
        myGUIController.clearConsole();
        updateActiveLabels();
    }

    private void updateActiveLabels(){
        myGUIController.updateActiveLabels(myTurtleManager.getActiveID(), myTurtleManager.getTurtleAtIndex(myTurtleManager.getActiveID()));
    }

    private void sendCommand(){
        mySimulationController.setLanguage(myGUIController.getLanguage());
        if(!myGUIController.getCommandEntered().isEmpty()){
            myGUIController.addToCommandHistory(myGUIController.getCommandEntered());
            mySimulationController.receive(myGUIController.getCommandEntered());
        }    
    }

    private void addPenColorListener(Turtleable turtle){
        myGUIController.getPenColorCommand().addListener(new ChangeListener <String>(){
            @Override
            public void changed (ObservableValue<? extends String> observable,
                                 String oldValue,
                                 String newValue) {
                Double colorIndex = mySimulationController.receive(newValue.toString());
                updateLineColor(colorIndex.intValue());
            }
        });

        turtle.getPenColorIndex().addListener(new ChangeListener<Number>(){

            @Override
            public void changed (ObservableValue<? extends Number> observable,
                                 Number oldValue,
                                 Number newValue) {
                updateLineColor(newValue.intValue());

            }

        });

    }

    private void addBackgroundColorListener(){
        myGUIController.getBackGroundColorCommand().addListener(new ChangeListener <String>(){
            @Override
            public void changed (ObservableValue<? extends String> observable,
                                 String oldValue,
                                 String newValue) {
                mySimulationController.receive(newValue.toString());
            }
        });
        mySimulationController.getStorage().getBackgroundIndex().addListener(new ChangeListener<Number>(){

            @Override
            public void changed (ObservableValue<? extends Number> observable,
                                 Number oldValue,
                                 Number newValue) {
                updateBackgroundColor(newValue.intValue());  
            }   
        });   
    }

    private void addPenSizeListener(Turtleable turtle){
        myGUIController.getPenSizeCommand().addListener( new ChangeListener <String>(){
            @Override
            public void changed (ObservableValue<? extends String> observable,
                                 String oldValue,
                                 String newValue) {
                Double sizeIndex = mySimulationController.receive(newValue.toString());
                updateLineSize(sizeIndex);
            }
        }); 

        turtle.getPenColorIndex().addListener(new ChangeListener<Number>(){
            @Override
            public void changed (ObservableValue<? extends Number> observable,
                                 Number oldValue,
                                 Number newValue) {
                updateLineSize(newValue.doubleValue());
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

    private void updateLineColor(Integer colorIndex){
        Turtleable activeTurtle = myTurtleManager.getActiveTurtle();
//        activeTurtle.setLineColor(getColor(colorIndex));
    }

    private Paint getColor(Integer Index){
        int[] colorValues = mySimulationController.getStorage().getPalette().get(Index);
        Color color = Color.rgb(colorValues[0], colorValues[1], colorValues[2]); 
        return color;
    }

    private void updateLineSize(Double lineSize){
//        FrontTurtle activeTurtle = myTurtleManager.getActiveTurtle();
//        activeTurtle.setLineWidth(lineSize);
    }

    private void updateBackgroundColor(Integer colorIndex){
        myGUIController.setBackgroundColor(getRGBString(colorIndex));
    }   

    private String getRGBString(Integer index){
        int [] rgb = mySimulationController.getStorage().getPalette().get(index);
        String myString = "rgb(" + rgb[0] + "," + rgb[1] + ", " + rgb[2] + ")";
        return myString;
    }

    private void setOnSaveButtonClicked(){
        myGUIController.setOnSaveButtonClicked(e -> {
            Map<String,Double> variables=mySimulationController.getStorage().getVariableMap();
            Map<String,InfoNode> functions=mySimulationController.getStorage().getFunctionMap();
            Map<String,List<String>> functionvariables=mySimulationController.getStorage().getFunctionVariablesMap();  
        });
    }

    private void setOnLoadButtonClicked(){
        myGUIController.setOnLoadButtonClicked(e -> {
            //TODO:
        });
    }

}