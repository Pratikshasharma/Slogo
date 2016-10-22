package commandreference;

import Simulation.SimulationController;
import gui.GUIController;

public class AppController {
	
	SimulationController mySimulationController;
	GUIController myGUIController;
	
	public AppController(GUIController guiController, SimulationController simulationController){
		mySimulationController = simulationController;
		myGUIController = guiController;
	}
	
	public void work(){
		sendCommand();
		updatePositions();
	}
	
	private void sendCommand(){
		//TODO: implement send to back
		//String command = myGUIController.getCommandEntered();
		//what should a parsed/executed command return from the following line:
		//mySimulationController.receive(command); 
			//will be void, will just execute command or throw up error.
		//need to reach in updatePositions method.
	}
	
	private void updatePositions(){
		//Get updated values from backend
		//myGUIController.updateLocation(x, y);
	}
}
