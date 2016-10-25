package Simulation.Node;

import java.util.ArrayList;

public class InfoNode extends Node{

	private String myName; //the name of the actual user input
	private String myToken; // the category it belongs in (Constant, Command, Variable, etc..)
	private InfoNode myLeft;
	private InfoNode myRight;
	private InfoNode myMiddle;
	private InfoNode myNext;

	
	
	public InfoNode(String name, String type) {
		myName = name;
		myToken = type;
		myLeft = null;
		myRight = null;
		myMiddle = null;
	}
	
	
	public String getName() {
		return myName;
	}
	
	public String getToken() {
		return myToken;
	}
	
	public void setLeft(InfoNode node) {
		myLeft = node;
	}
	
	public void setRight(InfoNode node) {
		myRight = node;
	}
	
	public void setMiddle(InfoNode node) {
		myMiddle = node;
	}
	
	public void setNext(InfoNode node) {
		myNext = node;
	}
	
	public InfoNode left() {
		return myLeft;
	}
	
	public InfoNode right() {
		return myRight;
	}
	
	public InfoNode middle() {
		return myMiddle;
	}
	
	public InfoNode next() {
		return myNext;
	}
}
