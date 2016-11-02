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
import SlogoException.UserDefinitionException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TreeFactory {
	private Deque<InfoNode> myList;
	private CommandStorage myCustom;
	private List<String> myLocalVar;
	private Map<String, String> myLocalFunc;
	private Map<String, Boolean> mySpecialList;
	private Map<String, Boolean> myActiveMethods;
	private InfoNode ZERO_NODE = new InfoNode("0", "Constant");
	private InfoNode NULL_NODE = null;

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
		InfoNode first = ZERO_NODE;
		first = createTree(myList.pop());
		InfoNode current = first;

		while (!myList.isEmpty()) {
			InfoNode nextNode = myList.pop();
			if (nextNode.getToken().equals("ListEnd")) {
				return first;
			}
			InfoNode nextTree = createTree(nextNode);
			if (nextTree != null) {
				current.setNext(nextTree);
			}

			current = current.next();
		}

		return first;
	}

	private InfoNode createTree(InfoNode current) throws UserDefinitionException {
		InfoNode nextItem;
		int intParam;
		String currentToken = current.getToken();
		String currentName = current.getName();
		String stringParam = "0";

		try {
			switch (currentToken) {
			case ("Constant"):
				return current;
			case ("Variable"):
				checkExistence(current);
				return current;
			case ("Command"):
				checkExistence(current);
				stringParam = Integer.toString((myCustom.getFunctionVariables(currentName).size()));
				break;
			case ("GroupStart"):
				nextItem = myList.pop();
				List<InfoNode> grouping = loopList(false, false);
				current = appendList(nextItem, grouping);
				return current;
			case ("ListStart"):
				// current = produceTree();
				// break;
				throw new ParserException("Cannot start command with a list");
			case ("ListEnd"):
				return NULL_NODE;
			default:
				stringParam = inputResource.getString(currentToken);
				break;
			}

			intParam = Integer.parseInt(stringParam);
			while (intParam > 0) {
				nextItem = myList.pop();
				if (currentToken.equals("MakeVariable")) {
					boolean globalVarCheck = false;
					for (List<String> e : myCustom.getFunctionVariablesMap().values()){
						if (e.contains(nextItem.getName())) {
							globalVarCheck = true;
						}
							
					}
					if (!globalVarCheck) {
						myLocalVar.add(nextItem.getName());
					} else {
						throw new ParserException("Cannot use function local variables as global variables");
					}
				}
				if (currentToken.equals("MakeUserInstruction")) {
					current = makeUserDefined(current, nextItem);
					return current;
				}
				if (nextItem.getToken().equals("ListEnd")) {
					throw new NoSuchElementException();

				}
				if (nextItem.getToken().equals("ListStart")) {
					if (mySpecialList.containsKey(currentToken)) {
						List<InfoNode> toAdd = loopList(mySpecialList.get(currentToken), false);

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
			// myCustom.setKillCommands(true);
			throw new ParserException("error in createTree: possibly not enough parameters given");
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
		} else {
			throw new ParserException("Incorrect method (TO) creating syntax");
		}
		myLocalFunc.put(commandName.getName(), Integer.toString(varList.size()));
		current = appendList(current, varList);

		nextTo = myList.pop();
		if (nextTo.getToken().equals("ListStart")) {
			nextTo = produceTree();

		}
		current.addParameter(nextTo);
		return current;
	}

	private InfoNode appendList(InfoNode current, List<InfoNode> list) {
		for (InfoNode node : list) {
			current.addParameter(node);
		}
		return current;
	}

	private InfoNode regularList() {
		InfoNode first = myList.pop();
		InfoNode current = first;
		while (!first.getToken().equals("ListEnd")) {
			current = createTree(current);
			current = myList.pop();
		}
		return first;
	}

	private List<InfoNode> loopList(boolean variableBool, boolean userBool) {
		List<InfoNode> list = new ArrayList<InfoNode>();
		InfoNode current = myList.pop();
		// FOR and DOTIMES add the first item as a local variable
		System.out.println("varbook" + variableBool);
		if (variableBool) {
			myLocalVar.add(current.getName());
		}
		while (!current.getToken().equals("ListEnd") && !current.getToken().equals("GroupEnd")) {
			// TO method needs to add all as
			if (userBool) {
				System.out.println("current name : " + current.getName());
				checkPreExistence(current);
			}
			list.add(createTree(current));
			current = myList.pop();
		}
		return list;
	}

	private void checkPreExistence(InfoNode e) {
		Map<String, Double> variableMap = myCustom.getVariableMap();

		if (!variableMap.keySet().contains(e.getName()) && !myLocalVar.contains(e.getName())) {
			myLocalVar.add(e.getName());
		} else {
			throw new UserDefinitionException("User Defined: Cannot use global variable as local variable.");
		}
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
