package Simulation.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Deque;

import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import Simulation.Node.Node;

public class Parser {
	public final static String LANG_PATH = "resources/languages/";
	public final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
	private final static String TO = "To";

	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	private static TypeDictionary lang;
	private CommandStorage myCustom;
	private Deque<InfoNode> myList;

	public Parser(String language) {
		lang = new TypeDictionary();
		lang.addPatterns(LANG_PATH + language);
		lang.addPatterns(LANG_PATH + SYNTAX_LANG);
	}

	public Parser() {
		this(DEFAULT_LANG);

	}

	// given some text, prints results of parsing it using the given language
	public InfoNode parseText(String[] text, CommandStorage custom) {
		myList = new ArrayDeque<InfoNode>();
		myCustom = custom;

		for (String s : text) {
			myList.add(new InfoNode(s, lang.getSymbol(s)));
		}

		InfoNode first = createTree(myList.pop());
		InfoNode current = first;
		while (!myList.isEmpty()) {
			InfoNode nextTree = createTree(myList.pop());
			current.setNext(nextTree);
			current = current.next();
		}

		return first;
	}

	private InfoNode createTo(InfoNode customTo) {
		InfoNode invalidCommand = new InfoNode("0", "Constant");
		InfoNode commandNode = myList.pop();
		if (commandNode.getToken().equals("COMMAND")) {
			customTo.addParameter(createTree(commandNode));
			//to check for recursion, save name of command
			String commandName = commandNode.getName();
			List<String> localVar = new ArrayList<>(); // this is to see if localVar used is valid
			int intParam = 0; //this is to see if recursed method name param numbers are valid. 

			
			InfoNode listBegin = myList.pop();
			if (listBegin.getToken().equals("ListBegin")) {
				InfoNode root = myList.pop();
				//if the first pop after bracket is not the end bracket
				if (!root.getToken().equals("ListEnd")) {
					//the first becomes the root, the current becomes the next
					InfoNode currentParam = root;
					InfoNode nextParam = myList.pop();
					localVar.add(currentParam.getName());
					intParam++;
					while (!nextParam.getToken().equals("ListEnd")) {
						currentParam.setNext(nextParam);
						currentParam = currentParam.next();
						localVar.add(currentParam.getName());
						intParam++;
						nextParam = myList.pop();
					}
					//the parameter "tree" (variables in a line of .next) 
					customTo.addParameter(root);
				} else {
					//no parameters in TO
				}
			} else {
				//the next item wasn't a list hrm... return new node 0
				return invalidCommand;
			}
			//check for the commands list and create the tree out of the variables
			//in this list. 
			//but how do i check for the temporary variables???!!?1
			InfoNode commandBegin = myList.pop();
			if (commandBegin.getToken().equals("ListBegin")) {
				InfoNode firstTree = myList.pop();
				if (!firstTree.getToken().equals("ListEnd")) {
					firstTree = createTree(firstTree, localVar, commandName, intParam);
					InfoNode currentTree = firstTree;
					//where is localvar being checked?
					//where is intParam being checked?
					while (!currentTree.getToken().equals("ListEnd")) {
						InfoNode nextTree = createTree(currentTree, localVar, commandName, intParam);
						currentTree.setNext(nextTree);
						currentTree = currentTree.next();
					}
					customTo.addParameter(commandBegin);
				} else {
					//commands list is empty??
				}

			} else {
				//doesn't have a commands list or doesn't immediately go into one
				return invalidCommand;
			}
			return customTo;
		}

		return invalidCommand;
	}
	
	private InfoNode getCommandsList(InfoNode current) {
		if (current.getName().equals("ListStart")) {
			myList.pop();
		}
		return current;
	}
	
	private InfoNode createTree(InfoNode current) {

		InfoNode listNode;
		int intParam;

		try {
			if (current.getToken().equals(TO)) {
				return createTo(current);
			}

			checkGlobalVariable(current); 
			String stringParam = inputResource.getString(current.getToken());
			intParam = Integer.parseInt(stringParam);
			

			while (intParam > 0) {
				listNode = myList.pop();
				current.addParameter(createTree(listNode));
				intParam--;
			}

		} catch (NoSuchElementException e) {
			// throw new compileException(message: incorrect number of
			// parameter)
		}

		return current;
	}

	private InfoNode createTree(InfoNode current, List<String> localVar, String recurseCommand, int size) {

		InfoNode listNode;
		int intParam;

		try {
			if (current.getToken().equals("COMMAND")) {
				if (current.getName().equals(recurseCommand)) {
					intParam = size;
				} else {
					intParam = getCustomParamSize(current.getName());
				}
			} else {
				checkLocalVariable(current, localVar);
				String stringParam = inputResource.getString(current.getToken());
				intParam = Integer.parseInt(stringParam);
			}
			

			while (intParam > 0) {
				listNode = myList.pop();
				current.addParameter(createTree(listNode, localVar, recurseCommand, size));
				intParam--;
			}

		} catch (NoSuchElementException e) {
			// throw new compileException(message: incorrect number of
			// parameter)
		}

		return current;
	}
	
	private int getCustomParamSize(String command) {
		Map<String, List<String>> customParamMap = myCustom.getFunctionVariablesMap();
		if (customParamMap.containsKey(command)) {
			return customParamMap.get(command).size();
		} else {
			//throw stuffs
		}
		return 0;
	}

	private void checkGlobalVariable(InfoNode current) {
		if (current.getToken().equals("Variable")) {
			Map<String,Double>varMap = myCustom.getVariableMap();
			if (!varMap.containsKey(current.getName())){
				//throw error that it does not currently exist as a variable
				//message: undefined variable name
			}
		}
	}
	private void checkLocalVariable(InfoNode variable, List<String> localVar) {
		if (variable.getToken().equals("Variable")) {
			if (!localVar.contains(variable.getName())) {
				//throw message "local variable not defined"
			}
		}
		
	}
}