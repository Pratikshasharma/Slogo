package Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Actors.Actor;
import Actors.Turtle;
import Simulation.Node.InfoNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class CommandStorage extends Storage {
	private final String DEFAULT_IMAGE_PATH = "turtle.png";
    private ObservableMap<Integer,Actor> actors;
    private List<Integer> active;
    private ObservableMap<String,Double> variables;
    private ObservableMap<String,InfoNode> functions;
    private Map<String,List<String>> functionvariables;
    private Map<Integer,Integer[]> colorValues;
    private int backgroundindex;

    
    public CommandStorage () {
        actors=FXCollections.observableMap(new HashMap<Integer,Actor>());
        active=new ArrayList<Integer>();
        variables=FXCollections.observableMap(new HashMap<String,Double>());
        functions=FXCollections.observableMap(new HashMap<String,InfoNode>());
        functionvariables=new HashMap<String,List<String>>();
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
    
    //for use in temporary variables
    public void setVariableMap(Map <String,Double> inputMap){
        variables=FXCollections.observableMap(inputMap);
    }
    
    public void removeFunction(String key){
        functions.remove(key);
        functionvariables.remove(key);
    }
}
