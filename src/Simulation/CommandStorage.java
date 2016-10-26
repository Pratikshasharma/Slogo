package Simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Actors.Actor;
import Actors.Turtle;
import Simulation.Node.InfoNode;

public class CommandStorage {
    private List<Actor> actors;
    private  Map<String,Double> variables;
    private  Map<String,InfoNode> functions;
    
    public CommandStorage () {
        actors=new ArrayList<Actor>();
        variables=new HashMap<String,Double>();
        functions=new HashMap<String,InfoNode>();
    }
    
    public void addNewActors(int newnum){
        for(int i=0;i<newnum;i++){
            actors.add(new Turtle());
        }
    }

    public void addVariable(String key, double expr){
        variables.put(key,expr);
    }
    
    //note index starts at 1
    public Actor getActor(int index){
        return actors.get(index-1);
    }
    
    public double getVariable(String key){
        return variables.get(key);
    }
    
    public List<Actor> getActorList(){
        return actors;
    }
    
    public Map<String,Double> getVariableList(){
        return variables;
    }
    
    public Map<String,InfoNode> getFunctionList(){
        return functions;
    }
}
