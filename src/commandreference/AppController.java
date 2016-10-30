package commandreference;

import java.net.MalformedURLException;
import Actors.Actor;
import Simulation.SimulationController;
import Simulation.Node.InfoNode;
import SlogoException.CommandException;
import gui.ErrorAlert;
import gui.FrontTurtle;
import gui.GUIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;

public class AppController {

	SimulationController mySimulationController;
	private GUIController myGUIController;
	private TurtleManager myTurtleManager;
	private final String DEFAULT_TURTLE = "turtle.png";

	public AppController() {
		initializeSimulationController();
		initializeGUIController();
		myTurtleManager = new TurtleManager();
		setObservables();
	}

	public Parent initiateApp(){
		Parent mainRoot = myGUIController.init();
		setRunButton();
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
		mySimulationController.setLanguage(myGUIController.DEFAULT_LANGUAGE);
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
				processNewTurtle(newTurtle, id);
			}
		});
	}
	
	private void processNewTurtle(Turtleable newTurtle, int id) {
		myTurtleManager.addTurtle(id, newTurtle);
		newTurtle.getImageView().setOnMouseClicked(e -> {
			setActiveID(id);
		});
		updateTurtlesOnFront(myTurtleManager.getTurtleAtIndex(id));
	}

	private void setActiveID(int id) {
		mySimulationController.getStorage().setActive(id);
		myTurtleManager.setActiveTurtle(id);
		updateActiveLabels();
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
		updateActiveLabels();
	}

	private void updateActiveLabels(){
		myGUIController.updateActiveLabels(myTurtleManager.getActiveID(), myTurtleManager.getTurtleAtIndex(myTurtleManager.getActiveID()));
	}

	private boolean sendCommand(){
		if(!myGUIController.getCommandEntered().isEmpty()){
			myGUIController.addToCommandHistory(myGUIController.getCommandEntered());
			double d = mySimulationController.receive(myGUIController.getCommandEntered());
			if(Double.isNaN(d)){
				ErrorAlert error = new ErrorAlert("error");
				error.displayAlert("error");
			}
			return true;
		}
		return false;
	}

	public void handleKeyInput(KeyCode code){
		switch(code) {
		case ENTER: 
			myGUIController.setConsole(myGUIController.getCommandEntered() + "\n");
			break;
		default:
			//Do nothing
		}
	}

	public MenuItem getNewWindowMenu(){
		return myGUIController.getNewWindowMenu();
	}
}