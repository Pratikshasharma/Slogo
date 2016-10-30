package Simulation;

import java.util.List;
import java.util.Map;
import Simulation.Node.InfoNode;

public abstract class Storage {
    public Map<Integer,String> actors;
    private List<Integer> active;
    private Map<String,Double> variables;
    private Map<String,InfoNode> functions;
}
