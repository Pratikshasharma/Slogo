package Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Actors.Actor;
import Actors.Turtle;
import Simulation.Node.InfoNode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class CommandStorage extends Storage {
	private final String DEFAULT_IMAGE_PATH = "turtle.png";
    private ObservableMap<Integer,Actor> actors;
    private List<Integer> active;
    private ObservableMap<String,Double> variables;
    private ObservableMap<String,InfoNode> functions;
    private Map<String,List<String>> functionvariables;
    private ObservableMap<Integer,int[]> colorMap;
    private IntegerProperty backgroundColorIndex;
    private boolean killcommands;

    public CommandStorage () {
        actors=FXCollections.observableMap(new HashMap<Integer,Actor>());
        active=new ArrayList<Integer>();
        variables=FXCollections.observableMap(new HashMap<String,Double>());
        functions=FXCollections.observableMap(new HashMap<String,InfoNode>());
        functionvariables=new HashMap<String,List<String>>();
        colorMap=FXCollections.observableMap( new HashMap<Integer,int[]>());
        backgroundColorIndex = new SimpleIntegerProperty(0);
        backgroundColorIndex.set(1);
        addColorValues();
        killcommands = false;
    }
    
    //adding to lists/maps
    public void addNewActors(int newnum, String filePath){
        if(!actors.containsKey(newnum)){
            actors.put(newnum, new Turtle(filePath));
        }
    }
    
    public void addNewActors(int newnum){
        if(!actors.containsKey(newnum)){
            actors.put(newnum, new Turtle(DEFAULT_IMAGE_PATH));
        }
    }

    public void addVariable(String key, double expr){
        variables.put(key,expr);
    }
    
    public void addFunction(String key, List<String> funcvars, InfoNode Node){
        functions.put(key,Node);
        functionvariables.put(key,funcvars);
    }
    
    public void setActive(List <Integer> act){
        active=new ArrayList<Integer>(act);
    }
    
    public void setActive(int act){
        active=new ArrayList<Integer>();
        active.add(act);
    }
    
    public void setBackgroundIndex(int index){
        backgroundColorIndex.set(index);
    }
    
    public void setPaletteVal(int index, int[] color){
        colorMap.put(index, color);
    }
    
    public void setKillCommands(boolean bool){
        killcommands=bool;
    }
    
    //accessing parts of maps/lists
    
    //note index starts at 1
    public Actor getActor(int index){
        return actors.get(index);
    }
    
    public double getVariable(String key){
        return variables.get(key);
    }
    
    public InfoNode getFunction(String key){
        return functions.get(key);
    }
    
    public List<String> getFunctionVariables(String key){
        return functionvariables.get(key);
    }
    
    public int getActive(){
        return active.get(0);
    }
    
    public IntegerProperty getBackgroundIndex(){
        return backgroundColorIndex;
    }
    
    public int[] getPaletteVal(int key){
        return colorMap.get(key);
    }
    
    public boolean getKillCommands(){
        return killcommands;
    }
    
    //return lists/maps

    public ObservableMap<Integer,Actor> getActorMap(){
        return actors;
    }
    
    public ObservableMap<String,Double> getVariableMap(){
        return variables;
    }
    
    public ObservableMap<String,InfoNode> getFunctionMap(){
        return functions;
    }
    
    public Map<String,List<String>> getFunctionVariablesMap(){
        return functionvariables;
    }
    
    public List <Integer> getActiveList(){
        return active;
    }
    
    public ObservableMap<Integer,int[]> getPalette(){
        return colorMap;
    }

    //for use in temporary variables
    public void setVariableMap(Map <String,Double> inputMap){
        variables=FXCollections.observableMap(inputMap);
    }
    
    public void removeFunction(String key){
        functions.remove(key);
        functionvariables.remove(key);
    }
    
    private void addColorValues(){
        colorMap.put(1, new int[]{255,0,0});
        colorMap.put(2, new int[]{0,255,0});
        colorMap.put(3, new int[]{0,0,255});
        colorMap.put(4, new int[]{128,0,128});
        colorMap.put(5,  new int[]{255, 128,0});
        colorMap.put(6, new int[]{128,128,128});
    }
}
