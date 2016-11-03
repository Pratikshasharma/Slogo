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

/**
 * The class that creates the expression tree of InfoNodes through recursive methods. 
 * 
 * @author joykim
 *
 */
public class TreeFactory {
	private Deque<InfoNode> myList;
	private CommandStorage myCustom;
	private List<String> myLocalVar;
	private Map<String, String> myLocalFunc;
	private Map<String, Boolean> mySpecialList;
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

	//lists that require loopList, lists that turn into lists, not trees.
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

	/**
	 * Creates a branch of the tree.
	 * @param current
	 * @return InfoNode current
	 * @throws UserDefinitionException
	 */
	private InfoNode createTree(InfoNode current) throws UserDefinitionException {
		InfoNode nextItem;
		int intParam;
		String currentToken = current.getToken();
		String currentName = current.getName();
		String stringParam = "0";

		try {
			switch (currentToken) {
			case ("Variable"):
				checkExistence(current);
				break;
			case ("Command"):
				checkExistence(current);
			System.out.println(current.getName());
				if (myCustom.getFunctionVariablesMap().containsKey(currentName)){
					stringParam = Integer.toString((myCustom.getFunctionVariables(currentName).size()));
				} else if (myLocalFunc.containsKey(currentName)){
					stringParam = myLocalFunc.get(currentName);
				} else {
					throw new ParserException("no such method");
				}
				break;
			case ("GroupStart"):
				nextItem = myList.pop();
				List<InfoNode> grouping = loopList(false, false);
				current = appendList(nextItem, grouping);
				return current;
			case ("ListStart"):
				throw new ParserException("Cannot start command with a list");
			case ("ListEnd"):
				return NULL_NODE;
			case ("MakeUserInstruction"):
				current = makeUserDefined(current);
				return current;
			default:
				stringParam = inputResource.getString(currentToken);
				break;
			}

			intParam = Integer.parseInt(stringParam);
			while (intParam > 0) {
				nextItem = myList.pop();
				if (currentToken.equals("MakeVariable")) {
					checkExistingLocal(nextItem);
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
			throw new ParserException("error in createTree: possibly not enough parameters given");
		}
		return current;
	}



	/**
	 * A more specific method that takes care of the creation User Defined methods, 
	 * through the method TO
	 * @param current
	 * @param command
	 * @return
	 */
	private InfoNode makeUserDefined(InfoNode current) {
		InfoNode commandName = myList.pop();
		if (commandName.getToken().equals("Command")) {
			current.addParameter(commandName);
		} else {
			throw new UserDefinitionException("No command name, improper syntax");
		}
		InfoNode nextTo = myList.pop();
		List<InfoNode> varList = new ArrayList<InfoNode>();
		if (nextTo.getToken().equals("ListStart")) {
			varList = loopList(false, true);
			for (InfoNode e : varList) {
				myLocalVar.add(e.getName());
			}
		} else {
			throw new UserDefinitionException("Incorrect method creating syntax (TO)");
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

	/**
	 * Adds a list of InfoNodes to the parameter list of current. 
	 * @param current
	 * @param list
	 * @return
	 */
	private InfoNode appendList(InfoNode current, List<InfoNode> list) {
		for (InfoNode node : list) {
			current.addParameter(node);
		}
		return current;
	}

	/**
	 * The special method to loop through a list (indicated by brackets) instead
	 * of the standard making the contents of a list into a tree.
	 * @param variableBool
	 * @param userBool
	 * @return
	 */
	private List<InfoNode> loopList(boolean variableBool, boolean userBool) {
		List<InfoNode> list = new ArrayList<InfoNode>();
		InfoNode current = myList.pop();
		// FOR and DOTIMES add the first item as a local variable
		if (variableBool) {
			myLocalVar.add(current.getName());
		}
		while (!current.getToken().equals("ListEnd") && !current.getToken().equals("GroupEnd")) {
			// TO method needs to add all as
			if (userBool) {
				checkExistingGlobal(current);
			}
			list.add(createTree(current));
			current = myList.pop();
		}
		return list;
	}

	/**
	 * A method to check that a desired variable name for the creation of a custom method 
	 * does not already exist as a global variable.
	 * @param current
	 * @throws UserDefinitionException
	 */
	private void checkExistingGlobal(InfoNode current) {
		Map<String, Double> variableMap = myCustom.getVariableMap();

		if (!variableMap.keySet().contains(current.getName()) && !myLocalVar.contains(current.getName())) {
			myLocalVar.add(current.getName());
		} else {
			throw new UserDefinitionException("User Defined: Cannot use global variable as local variable.");
		}
	}
	
	private void checkExistingLocal(InfoNode nextItem) {
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

	/**
	 * A method that checks whether the desired variable or functions exists and is ready
	 * for use. 
	 * @param current
	 * @throws ParserException
	 */
	private void checkExistence(InfoNode current) {
		String name = current.getName();
		Set<String> mapKey;
		if (current.getToken().equals("Variable")) {
			mapKey = myCustom.getVariableMap().keySet();
			if (!mapKey.contains(name) && !myLocalVar.contains(name)) {
				throw new ParserException("undefined variable name or name of method's local variable: " + name);
			}
		}
		if (current.getToken().equals("Command")) {
			mapKey = myCustom.getFunctionMap().keySet();
			if (!mapKey.contains(name) && !myLocalFunc.keySet().contains(name)) {
				throw new ParserException("undefined function name (inside checkExistence method): " + name);
			}
		}
	}

}
