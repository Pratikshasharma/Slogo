package commandreference;

import java.net.MalformedURLException;
import java.util.Map;

import Actors.Actor;
import Simulation.SimulationController;
import Simulation.Node.InfoNode;
import gui.GUIController;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class AppController {

	SimulationController mySimulationController;
	GUIController myGUIController;

	public AppController(){
		initializeSimulationController();
		initializeGUIController();
		setObservables();
	}

	public Scene initiateApp(){
		Scene mainScene = myGUIController.init();
		setRunButton();
		mySimulationController.getStorage().addNewActors(1, "turtle.png");
//		updateFront();
		handleNewTurtle();
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
				updateFunctionHistoryOnFront();
			}
		});
	}

	private void setFunctionObserver() {
		mySimulationController.getStorage().getVariableMap().addListener(new MapChangeListener<String, Double>(){
			@Override
			public void onChanged(MapChangeListener.Change change){
				updateVariableHistoryOnFront();
			}
		});
	}

	private void setActorObserver() {
		mySimulationController.getStorage().getActorMap().addListener(new MapChangeListener<Integer, Actor>() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
            	Turtleable newTurtle = (Turtleable) change.getValueAdded();
                updateTurtlesOnFront(newTurtle);
            }
        });
	}

	private void updateTurtlesOnFront(Turtleable turtle){
		myGUIController.addToScene(turtle);
	}

	private void updateFunctionHistoryOnFront(){
		for(String description : mySimulationController.getStorage().getFunctionMap().keySet()){
			myGUIController.getHistory().addToFunctionsHistory(description);
		}
	}

	private void updateVariableHistoryOnFront(){
		for(String description : mySimulationController.getStorage().getVariableMap().keySet()){
			myGUIController.getHistory().addToVariableList(description);
		}
	}

	private void handleNewTurtle(){
		myGUIController.getMainGUI().getMyFileTab().getNewTurtleItem().setOnAction(e -> {
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
	}

	private void sendCommand(){
		//mySimulationController.receive("make :distance fd sum sin 20 20");
		//mySimulationController.receive("make :distance fd 50"); 
		//mySimulationController.receive("fd sum 80 sin 100");
		//mySimulationController.receive("fd sum 20 30 bk 100 left 300");
		//mySimulationController.receive("fd sum 80 sin 100"); 
		mySimulationController.receive(myGUIController.getCommandEntered());
	}

	public void handleKeyInput(KeyCode code){
		switch(code) {
		case ENTER: 
			myGUIController.getMainGUI().getConsole().getTextField().setText("");
			work();
			break;

			//            case SHIFT:
			//                mySimulationController.getActorCoordinates().getX().set(mySimulationController.getActorCoordinates().getX().get() + 2);
			//                mySimulationController.getActorCoordinates().getY().set(mySimulationController.getActorCoordinates().getY().get() + 2);
			//                updatePositions();
			//                break;
			//
			//            case COMMAND:
			//                mySimulationController.getActorCoordinates().getX().set(mySimulationController.getActorCoordinates().getX().get() -2);
			//                mySimulationController.getActorCoordinates().getY().set(mySimulationController.getActorCoordinates().getY().get() - 2);
			//                updatePositions();
			//                break;
			//left
			//            case COMMAND:
			//                mySimulationController.getActorCoordinates().getX().set(mySimulationController.getActorCoordinates().getX().get() -50);
			//                updatePositions();
			//                break;
			//                //right
			//            case SHIFT:
			//                mySimulationController.getActorCoordinates().getX().set(mySimulationController.getActorCoordinates().getX().get() +50);
			//                updatePositions();
			//                break;
			//
		default:
			//Do nothing
		}
	}
}