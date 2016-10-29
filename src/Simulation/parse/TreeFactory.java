package Simulation.parse;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import Simulation.CommandStorage;
import Simulation.Node.InfoNode;

public class TreeFactory {
	private Deque<InfoNode> myList; 
	private CommandStorage myCustom;
	private List<String> myLocalVar;
	private List<String> myLocalFunc;
	private Map<String, Boolean> myActiveMethods;
	
	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	
	public TreeFactory(CommandStorage custom, Deque<InfoNode> list) {
		myList = list;
		myCustom = custom;
		myLocalVar = new ArrayList<String>();
		myLocalVar.add(":repcount");
		myLocalFunc = new ArrayList<String>();

	}
	
	public TreeFactory(Deque<InfoNode> list) {
		this(new CommandStorage(), list);
	}
	
	public InfoNode produceTree() {
		InfoNode first = createTree(myList.pop());
		InfoNode current = first;
		
		while (!myList.isEmpty()) {
			InfoNode nextTree = createTree(myList.pop());
			current.setNext(nextTree);
			current = current.next();
		}
		return first;
	}
	
	private InfoNode createTree(InfoNode current) {
		InfoNode listNode;
		int intParam;
		String token = current.getToken();
		String name = current.getName();

		try {
			if (token.equals("Constant")){
				return current;
			}
			if (token.equals(TO)) {
				return createTo(current);
			}
			
			if (token.equals("ListBegin")) {
				
			}
			if (myList)
			
			if (token.equals("GroupBegin")) {
				
			}

			checkVariable(current);
			checkCommand(current);

			String stringParam = inputResource.getString(token);
			intParam = Integer.parseInt(stringParam);

			while (intParam > 0) {
				listNode = myList.pop();
				make
				:dist
				current.addParameter(createTree(listNode));
				intParam--;
			}

		} catch (NoSuchElementException e) {
			// throw new compileException(message: incorrect number of
			// parameter)
		}

		return current;
	}
	
	
}
