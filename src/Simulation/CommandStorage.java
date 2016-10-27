package Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Actors.Actor;
import Actors.Turtle;
import Simulation.Node.InfoNode;

public class CommandStorage extends Storage{
    private Map<Integer,Actor> actors;
    private List<Integer> active;
    private Map<String,Double> variables;
    private Map<String,InfoNode> functions;
    private Map<String,List<String>> functionvariables;
    
    public CommandStorage () {
        actors=new HashMap<Integer,Actor>();
        active=new ArrayList<Integer>();
        variables=new HashMap<String,Double>();
        functions=new HashMap<String,InfoNode>();
        functionvariables=new HashMap<String,List<String>>();
        addNewActors(1);
        setActive(1);
    }
    
    //adding to lists/maps
    public void addNewActors(int newnum){
        if(!actors.containsKey(newnum)){
            actors.put(newnum,new Turtle());
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
        return actors.get(index-1);
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
    public Map<Integer,Actor> getActorList(){
        return actors;
    }
    
    public Map<String,Double> getVariableMap(){
        return variables;
    }
    
    public Map<String,InfoNode> getFunctionMap(){
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
        variables=new HashMap<String,Double>(inputMap);
    }
    
    public void removeFunction(String key){
        functions.remove(key);
        functionvariables.remove(key);
    }
}
