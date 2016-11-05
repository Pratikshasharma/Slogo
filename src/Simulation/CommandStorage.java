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

/**
 * The main storage class that contains all the data that needs to be stored (ie actors, variables, functions).
 * An instance of this class is passed through commands to store operating stages.
 * This storage class also serves as a set of  observables that allows the front end to catch with observers when changed.
 * 
 * 
 * Called by every class in the backend including SimulationController, CommandProcess, Parser, and Commands.
 * 
 * @author Vincent
 *
 */
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

    /**
     * Constructor
     */
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
    
    
    /**
     * Add new actors to the field with a image specified.
     * 
     * @param newnum
     * @param filePath
     */
    public void addNewActors(int newnum, String filePath){
        if(!actors.containsKey(newnum)){
            actors.put(newnum, new Turtle(filePath));
        }
    }
    
    
    /**
     * Add new Actors to the field with default image.
     * 
     * @param newnum
     */
    public void addNewActors(int newnum){
        if(!actors.containsKey(newnum)){
            actors.put(newnum, new Turtle(DEFAULT_IMAGE_PATH));
        }
    }

    /**
     * Add new variables.
     * 
     * @param key
     * @param expr
     */
    public void addVariable(String key, double expr){
        variables.put(key,expr);
    }
    
    /**
     * Add new functions.
     * 
     * @param key
     * @param funcvars
     * @param Node
     */
    public void addFunction(String key, List<String> funcvars, InfoNode Node){
        functions.put(key,Node);
        functionvariables.put(key,funcvars);
    }
    
    /**
     * Set a list of actors as active.
     * 
     * @param act
     */
    public void setActive(List <Integer> act){
        active=new ArrayList<Integer>(act);
    }
    
    /**
     * Change the active actor.
     * 
     * @param act
     */
    public void setActive(int act){
        active=new ArrayList<Integer>();
        active.add(act);
    }
    
    /**
     * Change background index.
     * 
     * @param index
     */
    public void setBackgroundIndex(int index){
        backgroundColorIndex.set(index);
    }
    
    /**
     * Set color values for indices in the palette.
     * 
     * @param index
     * @param color
     */
    public void setPaletteVal(int index, int[] color){
        colorMap.put(index, color);
    }
    
    /**
     * Invalidate all following commands after finding an error.
     * 
     * @param bool
     */
    public void setKillCommands(boolean bool){
        killcommands=bool;
    }
    
    //accessing parts of maps/lists
    
    /**
     * Get the list of actors
     * 
     * @param index
     * @return
     */
    public Actor getActor(int index){
        return actors.get(index);
    }
    
    /**
     * Get the value of a variable.
     * 
     * @param key
     * @return
     */
    public double getVariable(String key){
        return variables.get(key);
    }
    
    /**
     * Get the tree for a function.
     * 
     * @param key
     * @return
     */
    public InfoNode getFunction(String key){
        return functions.get(key);
    }
    
    /**
     * Get the local variables used in a function.
     * 
     * @param key
     * @return
     */
    public List<String> getFunctionVariables(String key){
        return functionvariables.get(key);
    }
    
    /**
     * Get the current active actor (situations where only one actor is active)
     * 
     * @return
     */
    public int getActive(){
        return active.get(0);
    }
    
    /**
     * Get the current color index used for hte background.
     * 
     * @return
     */
    public IntegerProperty getBackgroundIndex(){
        return backgroundColorIndex;
    }
    
    /**
     * Get the current color rgb value stored at the index in the palette.
     * 
     * @param key
     * @return
     */
    public int[] getPaletteVal(int key){
        return colorMap.get(key);
    }
    
    /**
     * Get whether or not commands should be nullified (in case of errors).
     * 
     * @return
     */
    public boolean getKillCommands(){
        return killcommands;
    }
    
    //return lists/maps

    /**
     * Return the map of actors.
     * 
     * @return
     */
    public ObservableMap<Integer,Actor> getActorMap(){
        return actors;
    }
    
    /**
     * Return the map of variables.
     * 
     * @return
     */
    public ObservableMap<String,Double> getVariableMap(){
        return variables;
    }
    
    /**
     * Return the map of functions.
     * 
     * @return
     */
    public ObservableMap<String,InfoNode> getFunctionMap(){
        return functions;
    }
    
    /**
     * Return the map of function variables.
     * 
     * @return
     */
    public Map<String,List<String>> getFunctionVariablesMap(){
        return functionvariables;
    }
    
    /**
     * Return the list of actors.
     * 
     * @return
     */
    public List <Integer> getActiveList(){
        return active;
    }
    
    /**
     * Return the map of pallet.
     * 
     * @return
     */
    public ObservableMap<Integer,int[]> getPalette(){
        return colorMap;
    }

    
    /**
     * Set the map of actors.
     * For use in temporary variables
     * 
     * @return
     */
    public void setVariableMap(Map <String,Double> inputMap){
        variables=FXCollections.observableMap(inputMap);
    }
    
    /**
     * Initiate the palette. (Added by front end).
     */
    private void addColorValues(){
        colorMap.put(1, new int[]{255,0,0});
        colorMap.put(2, new int[]{0,255,0});
        colorMap.put(3, new int[]{0,0,255});
        colorMap.put(4, new int[]{128,0,128});
        colorMap.put(5,  new int[]{255, 128,0});
        colorMap.put(6, new int[]{128,128,128});
    }
}
