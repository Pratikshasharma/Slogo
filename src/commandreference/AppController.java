package commandreference;

import java.net.MalformedURLException;
import Actors.Actor;
import Simulation.SimulationController;
import Simulation.Node.InfoNode;
import gui.FrontTurtle;
import gui.GUIController;
import javafx.collections.MapChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class AppController {

	SimulationController mySimulationController;
	GUIController myGUIController;
	TurtleManager myTurtleManager;

	public AppController(){
		initializeSimulationController();
		initializeGUIController();
		myTurtleManager = new TurtleManager();
		setObservables();
	}

	public Scene initiateApp(){
		Scene mainScene = myGUIController.init();
		setRunButton();
		mySimulationController.getStorage().addNewActors(1, "turtle.png");
		mySimulationController.getStorage().setActive(1);
		setNewTurtleHandler();
		renderTurtles();
		return mainScene;
	}

	private void initializeGUIController() {
		myGUIController = new GUIController();
	}

	private void initializeSimulationController() {
		mySimulationController = new SimulationController();
		mySimulationController.setLanguage("English");
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
				myGUIController.getHistory().addToVariableList(name);
			}
		});
	}

	private void setFunctionObserver() {
		mySimulationController.getStorage().getVariableMap().addListener(new MapChangeListener<String, Double>(){
			@Override
			public void onChanged(MapChangeListener.Change change){
				myGUIController.getHistory().addToFunctionsHistory((String) change.getKey());
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
				newTurtle.getImageView().setOnMouseClicked(e -> {
					mySimulationController.getStorage().setActive(id);
				});
				updateTurtlesOnFront(newTurtle);
			}
		});
	}

	private void renderTurtles(){
		for(Turtleable turtle : myTurtleManager.getTurtles()){
			updateTurtlesOnFront(turtle);
		}
	}

	private void updateTurtlesOnFront(Turtleable turtle){
		myGUIController.addToScene(turtle);
	}

	private void setNewTurtleHandler(){
		myGUIController.getFileTab().getNewTurtleItem().setOnAction(e -> {
			try {
				String filePath = myGUIController.chooseFile().toURI().toURL().toString();
				int id = getUnusedID();
				mySimulationController.getStorage().addNewActors(id, filePath);
			}
			catch (MalformedURLException error) {
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
		return i + 1;
	}

	private void setRunButton(){
		Button b = myGUIController.getRunButton();
		b.setOnAction(e -> work());
	}

	private void work(){
		sendCommand();
		renderTurtles();
		myGUIController.getConsole().getTextField().clear();
	}

	private void sendCommand(){
		if(!myGUIController.getCommandEntered().isEmpty()){
			myGUIController.getHistory().addToCommandHistory(myGUIController.getCommandEntered());
			mySimulationController.receive(myGUIController.getCommandEntered());
			
		}
	}

	public void handleKeyInput(KeyCode code){
		switch(code) {
		case ENTER: 
			myGUIController.getConsole().getTextField().setText(myGUIController.getConsole().getTextField().getText() + "\n");
			break;
		default:
			//Do nothing
		}
	}
}