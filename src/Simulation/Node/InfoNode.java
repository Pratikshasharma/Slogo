package Simulation.Node;

import java.util.ArrayList;

public class InfoNode extends Node{

	private String myName; //the name of the actual user input
	private String myToken; // the category it belongs in (Constant, Command, Variable, etc..)

	
	
	public InfoNode(String name, String type) {
		myName = name;
		myToken = type;
	}
	
	
	public String getName() {
		return myName;
	}
	
	public String getToken() {
		return myToken;
	}
	
}
