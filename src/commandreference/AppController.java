package commandreference;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Scanner;
import Actors.Actor;
import Simulation.SimulationController;
import Simulation.Node.InfoNode;
import gui.ErrorAlert;
import gui.GUIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AppController {

	private static final String BACKGROUND_COMMAND = "SETBACKGROUND ";
	private static final String PEN_COLOR_COMMAND = "setpencolor " ;
	private static final String PEN_SIZE_COMMAND = "setpensize ";
	private final String PEN_DOWN_COMMAND = "pendown";
	private final String PEN_UP_COMMAND = "penup";

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
		setOnSaveButtonClicked();
		setOnLoadButtonClicked();
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
		setBackgroundColorMenuHandler();
		setPenColorMenuHandler();
		setPenSizeMenuHandler();
		setOnTogglePenClicked();
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
				if(!myGUIController.functionHistoryContains((String) change.getKey())){
					myGUIController.addToFunctionHistory((String) change.getKey());
				}
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
		for(i = 1; i <= mySimulationController.getStorage().getActorMap().keySet().size(); i++){
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

	private void setPenColorMenuHandler(){
		myGUIController.getPenColorMenu().getItems().stream().forEach(item -> {
			item.setOnAction(e -> {
				mySimulationController.receive(PEN_COLOR_COMMAND + item.getText());
			});
		});
	}

	private void setPenSizeMenuHandler(){
		myGUIController.getPenSizeMenu().getItems().stream().forEach(item -> {
			item.setOnAction(e -> {
				mySimulationController.receive(PEN_SIZE_COMMAND + item.getText());
			});
		});
	}

	private void setBackgroundColorMenuHandler(){
		myGUIController.getBackgroundMenu().getItems().stream().forEach(item -> {
			item.setOnAction(e -> {
				mySimulationController.receive(BACKGROUND_COMMAND + item.getText());
			});
		});
	}

	private void addBackgroundColorListener(){
		mySimulationController.getStorage().getBackgroundIndex().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateBackgroundColor(newValue.intValue());
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

			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();   
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Logo Files");
			fileChooser.getExtensionFilters().add(
					new ExtensionFilter("SLogo Files", "*.logo"));

			File selectedFile = fileChooser.showSaveDialog(stage);
			if(selectedFile!=null){
				try {
					FileOutputStream fos=new FileOutputStream(selectedFile);
					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
					for(String var: variables.keySet()){
						bw.write("make " + var + " " + variables.get(var));
						bw.newLine();
					}
					for(String func: functions.keySet()){
						bw.write("to " + func + " [ ");
						for(String funcvar: functionvariables.get(func)){
						    bw.write(funcvar + " ");
						}
						
						bw.write("] [ ");
						writeNode(bw,functions.get(func));
						bw.write(" ]");
						bw.newLine();
					}
					bw.close();
				}
				catch (IOException e1) {
					//do nothing
				}
			}
		});
	}

	private void writeNode(BufferedWriter bw, InfoNode currentNode){
		try {
			if(currentNode!=null){
				switch(currentNode.getToken()){
				    case "Tell":
		                        bw.write(currentNode.getName() + " ");
				        bw.write("[ ");
				        writeBasic(bw,currentNode);
	                                bw.write(" ] ");
				        break;
				    case "Ask":case "AskWith": case "DoTimes":
				    case "For":case "Command":
		                        bw.write(currentNode.getName() + " ");
				        writeTwoSections(bw,currentNode);
				        break;
                                    case "Repeat":case "If":
                                        bw.write(currentNode.getName() + " ");
                                        writeExprCommand(bw,currentNode);
                                        break;
                                    case "IfElse":case "To":
                                        bw.write(currentNode.getName() + " ");
                                        writeExprTwoSections(bw,currentNode);
                                        break; 
                                    case "Backward": case "Forward": case "Left": case "Right":
                                    case "Sum": case "Difference": case "Product":
                                    case "And": case "Or":
                                        bw.write("( ");
                                        bw.write(currentNode.getName() + " ");
                                        writeBasic(bw,currentNode);
                                        bw.write(" ) ");
                                        break;
				    default:
		                        bw.write(currentNode.getName() + " ");
		                        writeBasic(bw,currentNode);
		                        break;
				}
				writeNode(bw,currentNode.next());
			}
		}
		catch (IOException e) {
			//do nothing
		}
	}
	
	private void writeBasic(BufferedWriter bw, InfoNode currentNode){
            for(InfoNode paramNode:currentNode.getParameters()){
                writeNode(bw, paramNode);
            }
	}
	
        private void writeTwoSections(BufferedWriter bw, InfoNode currentNode) throws IOException{
            bw.write("[ ");
            for(int i=0;i<currentNode.getParameters().size()-1;i++){
                InfoNode paramNode=currentNode.getParameters().get(i);
                writeNode(bw, paramNode);
            }
            bw.write(" ] [ ");   
            writeNode(bw,currentNode.getParameters().get(currentNode.getParameters().size()-1));
            bw.write(" ] ");   
        }
        
        private void writeExprCommand(BufferedWriter bw, InfoNode currentNode) throws IOException{
            writeNode(bw,currentNode.getParameters().get(0));
            bw.write(" [ ");   
            writeNode(bw,currentNode.getParameters().get(1));
            bw.write(" ] ");   
        }
        
        private void writeExprTwoSections(BufferedWriter bw, InfoNode currentNode) throws IOException{
            writeNode(bw,currentNode.getParameters().get(0));
            bw.write(" [ ");
            for(int i=1;i<currentNode.getParameters().size()-1;i++){
                InfoNode paramNode=currentNode.getParameters().get(i);
                writeNode(bw, paramNode);
            }
            bw.write(" ] [ ");   
            writeNode(bw,currentNode.getParameters().get(currentNode.getParameters().size()-1));
            bw.write(" ] ");    
        }


	private void setOnLoadButtonClicked(){
		myGUIController.setOnLoadButtonClicked(e -> {
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Logo Files");
			fileChooser.getExtensionFilters().add(
					new ExtensionFilter("SLogo Files", "*.logo"));

			File selectedFile = fileChooser.showOpenDialog(stage);
			if(selectedFile!=null){
				try {
					myGUIController.addToCommandHistory("Load " + selectedFile.toString());
					String content = new Scanner(selectedFile).useDelimiter("\\Z").next();
					myGUIController.setConsole(content);
					mySimulationController.receive(myGUIController.getCommandEntered());
					myGUIController.clearConsole();
				}
				catch (FileNotFoundException e1) {
					//do nothing
				}
			}
		});
	}
	
	private void setOnTogglePenClicked(){
		myGUIController.setOnTogglePen(e -> {
			if(myTurtleManager.getActiveTurtle().getPenStatus().get()){
				mySimulationController.receive(PEN_UP_COMMAND);
			} else {
				mySimulationController.receive(PEN_DOWN_COMMAND);
			}
			System.out.println("here");
			updateActiveLabels();
		});
	}

}