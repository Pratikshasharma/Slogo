package Simulation.Node;

import java.util.ArrayList;

public class TestNode extends Node{
	//instead of making this a node, might wanna just make this a 
	//class TokenInfo

	private String myName;
	private String myToken;
	Node left;
	Node right;
	
	
	public TestNode(String name, String parameter) {
		myName = name;
		myToken = parameter;
	}
	
	@Override
	public int execute() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	public String getName() {
		return myName;
	}
	
	public String getToken() {
		return myToken;
	}
	
}
