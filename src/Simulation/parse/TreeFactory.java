package Simulation.parse;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Set;

import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import SlogoException.ParserException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TreeFactory {
	private Deque<InfoNode> myList;
	private CommandStorage myCustom;
	private List<String> myLocalVar;
	private Map<String, String> myLocalFunc;
	private Map<String, Boolean> mySpecialList;
	private Map<String, Boolean> myActiveMethods;

	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	public TreeFactory(CommandStorage custom, Deque<InfoNode> list) {
		myList = list;
		myCustom = custom;
		myLocalVar = new ArrayList<String>();
		myLocalVar.add(":repcount");
		myLocalFunc = new HashMap<String, String>();
		initializeSpecialList();
	}

	public TreeFactory(Deque<InfoNode> list) {
		this(new CommandStorage(), list);
	}

	private void initializeSpecialList() {
		mySpecialList = new HashMap<String, Boolean>();
		mySpecialList.put("DoTimes", true);
		mySpecialList.put("For", true);
		mySpecialList.put("Tell", false);
		mySpecialList.put("Ask", false);
	}

	public InfoNode produceTree() {
		InfoNode first = createTree(myList.pop());
		InfoNode current = first;

		while (!myList.isEmpty()) {
			InfoNode nextNode = myList.pop();
			if (nextNode.getToken().equals("ListEnd")) {
				return first;
			}
			InfoNode nextTree = createTree(nextNode);
			current.setNext(nextTree);
			current = current.next();
		}
		return first;
	}

	private InfoNode createTree(InfoNode current) {
		InfoNode nextItem;
		int intParam;
		String token = current.getToken();
		String name = current.getName();
		String stringParam = "0";

		try {
	        switch(token){
	            case ("Constant"):
	                return current;
	            case ("Variable"):
	                checkExistence(current);
	            	return current;
	            case("Command"):
	                checkExistence(current);
	            	stringParam = myLocalFunc.get(name);
	            	break;
	            case("GroupBegin"):
	            	nextItem = myList.pop();
	            	List<InfoNode> grouping = loopList(false, false);
					current = appendList(nextItem, grouping);
	            	return current;
	            case("ListBegin"):
					nextItem = produceTree();
	            	break;
	            default:
	            	stringParam = inputResource.getString(token);
	                break;
	        }

			intParam = Integer.parseInt(stringParam);
			List<InfoNode> currentParameter = new ArrayList<InfoNode>();
			while (intParam > 0) {
				nextItem = myList.pop();
				if (token.equals("MakeVariable")) {
					myLocalVar.add(nextItem.getName());
				}
				// TO method
				if (token.equals("MakeUserInstruction")) {
					System.out.println("ENTERED INSTRUFTION");
					System.out.println("current: " + current.getName());
					System.out.println("next: " + nextItem.getName());
					current = makeUserDefined(current, nextItem);
					return current;
				}
				if (nextItem.getToken().equals("ListStart")) {
					boolean addLoopVariable = false;
					if (mySpecialList.containsKey(token)) {
						List<InfoNode> toAdd = loopList(mySpecialList.get(token), false);
						
						current = appendList(current, toAdd);
						

					} else {
						nextItem = produceTree();
						current.addParameter(nextItem);
						
					}
				} else {
					nextItem = createTree(nextItem);
					current.addParameter(nextItem);

				}
				intParam--;
			}

		} catch (NoSuchElementException e) {
			throw new ParserException("error in createTree: possibly not enough parameters?");
		}

		return current;
	}

	private InfoNode makeUserDefined(InfoNode current, InfoNode command) {
		InfoNode commandName = command;
		current.addParameter(commandName);
		InfoNode nextTo = myList.pop();
		List<InfoNode> varList = new ArrayList<InfoNode>();
		if (nextTo.getToken().equals("ListStart")) {
			varList = loopList(false, true);
			for (InfoNode e : varList) {
				myLocalVar.add(e.getName());
			}
		}
		System.out.println("par size: " + varList.size());
		myLocalFunc.put(commandName.getName(), Integer.toString(varList.size()));
		current = appendList(current, varList);

		nextTo = myList.pop();
		if (nextTo.getToken().equals("ListStart")) {
			nextTo = produceTree();

		}
		current.addParameter(nextTo);
		return current;
	}

	private void validateToken(InfoNode node, String token) {
		if (!node.getToken().equals(token)) {
			// throw invalid something or another
			//
		}
	}

	private InfoNode appendList(InfoNode current, List<InfoNode> list) {
		for (InfoNode node : list) {
			current.addParameter(node);
		}
		return current;
	}

	private List<InfoNode> loopList(boolean variableBool, boolean userBool) {
		List<InfoNode> list = new ArrayList<InfoNode>();
		try {
			InfoNode current = myList.pop();
			if (variableBool) {
				myLocalVar.add(current.getName());
			}
			while (!current.getToken().equals("ListEnd") && !current.getToken().equals("GroupEnd")) {
				if (userBool) {
					myLocalVar.add(current.getName());
				}
				list.add(createTree(current));
				current = myList.pop();
			}
		} catch (ParserException e) {
			e.showError("error caught within loopList: " + e.getMessage());
		}
		return list;
	}

	private void checkExistence(InfoNode current) {
		String name = current.getName();
		Set<String> mapKey;
		if (current.getToken().equals("Variable")) {
			mapKey = myCustom.getVariableMap().keySet();
			if (!mapKey.contains(name) && !myLocalVar.contains(name)) {
				throw new ParserException("undefined variable name (inside checkExistence): " + name);
			}
		}
		if (current.getToken().equals("Command")) {
			mapKey = myCustom.getFunctionMap().keySet();
			if (!mapKey.contains(name) && !myLocalFunc.keySet().contains(name)) {
				throw new ParserException("undefined function name (inside checkExistence method): " + name);
				// message: undefined function name
			}
		}
	}
	
	


}
