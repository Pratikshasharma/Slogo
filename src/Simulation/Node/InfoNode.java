package Simulation.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * The node object that contains a name (actual command given), a token (the
 * "type" of syntax, for example "Command" or "Constant"), a parameter list
 * to represent the children nodes, and the next node to represent the next 
 * command "tree" whose nodes are independent of the previous command's node
 * and its children. 
 * 
 * @author joykim
 */
public class InfoNode {

	private String myName; 
	private String myToken; 
	private List<InfoNode> myParameters;
	private InfoNode myNext;

	public InfoNode(String name, String type) {
		myName = name;
		myToken = type;
		myParameters = new ArrayList<>();
	}
	
	public String getName() {
		return myName;
	}
	
	public String getToken() {
		return myToken;
	}
	
	public void setNext(InfoNode next) {
		myNext = next;
	}
	
	public InfoNode next() {
		return myNext;
	}
	
	public void addParameter(InfoNode node) {
		myParameters.add(node);
	}
	
	public List<InfoNode> getParameters() {
		return myParameters;
	}
	
}
