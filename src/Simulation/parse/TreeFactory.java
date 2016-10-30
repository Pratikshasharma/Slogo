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

public class TreeFactory {
	private Deque<InfoNode> myList;
	private CommandStorage myCustom;
	private List<String> myLocalVar;
	private Map<String, Integer> myLocalFunc;
	private List<String> mySpecialList;
	private Map<String, Boolean> myActiveMethods;

	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	public TreeFactory(CommandStorage custom, Deque<InfoNode> list) {
		myList = list;
		myCustom = custom;
		myLocalVar = new ArrayList<String>();
		myLocalVar.add(":repcount");
		myLocalFunc = new HashMap<String, Integer>();
		initializeSpecialList();
	}

	public TreeFactory(Deque<InfoNode> list) {
		this(new CommandStorage(), list);
	}

	private void initializeSpecialList() {
		mySpecialList = new ArrayList<String>();
		mySpecialList.add("DoTimes");
		mySpecialList.add("For");
		mySpecialList.add("Tell");
		mySpecialList.add("Ask");
	}

	public InfoNode produceTree() {
		InfoNode first = createTree(myList.pop());
		InfoNode current = first;

		while (!myList.isEmpty()) {
			InfoNode nextNode = myList.pop();
			if (nextNode.equals("ListEnd")) {
				return first;
			}
			InfoNode nextTree = createTree(myList.pop());
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

		try {
			if (token.equals("Constant")) {
				return current;
			}

			if (token.equals("GroupBegin")) {
				List<InfoNode> grouping = loopList();
				current = appendList(current, grouping);
			}

			checkExistence(current);

			String stringParam = inputResource.getString(token);
			intParam = Integer.parseInt(stringParam);

			while (intParam > 0) {
				nextItem = myList.pop();
				if (token.equals("MakeVariable")) {
					myLocalVar.add(nextItem.getName());
				}
				// TO method
				if (token.equals("MakeUserInstruction")) {
					current = makeUserDefined(current);
					return current;
				}
				if (nextItem.getToken().equals("ListStart")) {
					if (mySpecialList.contains(token)) {
						List<InfoNode> toAdd = loopList();
						current = appendList(current, toAdd);

					} else {
						produceTree();
					}
				}
				current.addParameter(createTree(nextItem));
				intParam--;
			}

		} catch (NoSuchElementException e) {
			// throw new compileException(message: incorrect number of
			// parameter)
		}

		return current;
	}

	private InfoNode makeUserDefined(InfoNode current) {
		InfoNode commandName = myList.pop();
		InfoNode nextTo = myList.pop();
		List<InfoNode> varList = new ArrayList<InfoNode>();
		if (nextTo.getToken().equals("ListStart")) {
			varList = loopList();
		}
		myLocalFunc.put(commandName.getName(), varList.size());
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

	private List<InfoNode> loopList() {
		List<InfoNode> list = new ArrayList<InfoNode>();
		try {
			InfoNode current = myList.pop();
			while (!current.getToken().equals("ListEnd") || !current.getToken().equals("GroupEnd")) {
				list.add(createTree(current));
				current = myList.pop();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error inside looplist");
		}
		return list;
	}

	private void checkExistence(InfoNode current) {
		String name = current.getName();
		Set<String> mapKey;
		if (current.getToken().equals("Variable")) {
			mapKey = myCustom.getVariableMap().keySet();
			if (!mapKey.contains(name) || !myLocalVar.contains(name)) {
				// throw error
				// undefined variable name
			}
		}
		if (current.getToken().equals("Command")) {
			mapKey = myCustom.getFunctionMap().keySet();
			if (!mapKey.contains(name) || !myLocalFunc.keySet().contains(name)) {
				// throw error
				// message: undefined function name
			}
		}
	}

}
