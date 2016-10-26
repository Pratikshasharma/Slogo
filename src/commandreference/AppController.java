package commandreference;

import Simulation.SimulationController;
import gui.GUIController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class AppController {

    SimulationController mySimulationController;
    GUIController myGUIController;

    public AppController(){
        mySimulationController = new SimulationController();
        mySimulationController.setLanguage("English");
        myGUIController = new GUIController();
        setBindings();
    }
    
    private void setRunButton(){
    	Button b = myGUIController.getRunButton();
    	b.setOnAction(e -> work());
    }

    private void setBindings(){
        myGUIController.getCoordinates().getX().bind(mySimulationController.getActorCoordinates().getX());
        myGUIController.getCoordinates().getY().bind(mySimulationController.getActorCoordinates().getY());
    }

    private void work(){
    	myGUIController.addToHistory();
        sendCommand();
        updatePositions();
    }

    public Scene initiateApp(){
        Scene mainScene = myGUIController.init();
        setRunButton();
        return mainScene;
    }

    private void sendCommand(){
        //TODO: implement send to back
        //String command = myGUIController.getCommandEntered();

        //what should a parsed/executed command return from the following line:
        //System.out.println(x);
        //mySimulationController.receive(command); 

    	//mySimulationController.receive("make :distance fd sum sin 20 20");
        //mySimulationController.receive("make :distance fd 50"); 
        //mySimulationController.receive("fd sum 80 sin 100");
    	//mySimulationController.receive("fd sum 20 30 bk 100 left 300");
        //mySimulationController.receive("fd sum 80 sin 100"); 
        
        mySimulationController.receive(myGUIController.getCommandEntered());

        //will be void, will just execute command or throw up error.
        //need to reach in updatePositions method.

    }

    private void updatePositions(){
        //Get updated values from backend
        myGUIController.updateLocation();
    }

    public void handleKeyInput(KeyCode code){
        switch(code) {
            case ENTER: 
                myGUIController.addToHistory();
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
