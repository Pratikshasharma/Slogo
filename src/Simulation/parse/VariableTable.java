package Simulation.parse;

import java.util.HashMap;
import java.util.Map;

import Simulation.Node.InfoNode;

public class VariableTable {
	private Map<String, InfoNode> varMap;
	private final String DNE = "DNE";
	
	public VariableTable() {
		varMap = new HashMap<>();
	}
	
	public InfoNode getVar(String key) {
		if (varMap.containsKey(key)) {
			return varMap.get(key);
		}
		else {
			return new InfoNode(DNE,DNE);
		}
	}
	
	public void storeVar(String key, InfoNode val) {
		varMap.put(key, val);
	}
	
}
