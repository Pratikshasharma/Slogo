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

/**
 * 
 * @author joykim
 *
 */
public class Parser {
	private final static String LANG_PATH = "resources/languages/";
	private final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
	private final static String TO = "To";


	private static TypeDictionary lang;


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
		Deque<InfoNode> nodeList = createNodeList(text);
		
		TreeFactory myFactory = new TreeFactory(custom, nodeList);

		InfoNode toSend = myFactory.produceTree();
		
		return toSend;
	}

	private Deque<InfoNode> createNodeList(String[] text) {
		Deque<InfoNode> list = new ArrayDeque<InfoNode>();
		
		for (String s : text) {
			// text should represent the lines of code,
			// so splitting would divide them by token
			String[] tokens = s.trim().split("\\s+");
			for (String t : tokens) {
				String token = lang.getSymbol(t);
				if (token.equals("COMMENT")) {
					break;
				}
				list.add(new InfoNode(s, token));
			}

		}
		return list;
	}

	private InfoNode createTo(InfoNode customTo) {
		InfoNode invalidCommand = new InfoNode("0", "Constant");
		InfoNode commandNode = myList.pop();
		customTo.addParameter(createTree(commandNode));
		// to check for recursion, save name of command
		String commandName = commandNode.getName();
		List<String> localVar = new ArrayList<>(); // this is to see if localVar
													// used is valid
		int intParam = 0; // this is to see if recursed method name param
							// numbers are valid.

		InfoNode listBegin = myList.pop();
		if (listBegin.getToken().equals("ListBegin")) {
			InfoNode root = myList.pop();
			// if the first pop after bracket is not the end bracket
			if (!root.getToken().equals("ListEnd")) {
				// the first becomes the root, the current becomes the next
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
				// the parameter "tree" (variables in a line of .next)
				customTo.addParameter(root);
			} else {
				// no parameters in TO
			}
		} else {
			// the next item wasn't a list hrm... return new node 0
			return invalidCommand;
		}
		// check for the commands list and create the tree out of the variables
		// in this list.
		// but how do i check for the temporary variables???!!?1
		InfoNode commandBegin = myList.pop();
		if (commandBegin.getToken().equals("ListBegin")) {
			InfoNode firstTree = myList.pop();
			if (!firstTree.getToken().equals("ListEnd")) {
				firstTree = createCustomTree(firstTree, localVar, commandName, intParam);
				InfoNode currentTree = firstTree;
				// where is localvar being checked?
				// where is intParam being checked?
				while (!currentTree.getToken().equals("ListEnd")) {
					InfoNode nextTree = createCustomTree(currentTree, localVar, commandName, intParam);
					currentTree.setNext(nextTree);
					currentTree = currentTree.next();
				}
				customTo.addParameter(commandBegin);
			} else {
				// commands list is empty??
			}

		} else {
			// doesn't have a commands list or doesn't immediately go into one
			// or
			// there wasn't a first parameters list and it completely misread
			// the parameters as commands
			return invalidCommand;
		}
		return customTo;

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
		String token = current.getToken();
		String name = current.getName();

		try {
			if (token.equals(TO)) {
				return createTo(current);
			}
			
			if (token.equals("ListBegin")) {
				
			}
			
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

	private InfoNode createCustomTree(InfoNode current, List<String> localVar, String recurseCommand, int size) {

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
				current.addParameter(createCustomTree(listNode, localVar, recurseCommand, size));
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
			// throw stuffs
		}
		return 0;
	}

	private void checkVariable(InfoNode current) {
		if (current.getToken().equals("Variable")) {
			Map<String, Double> varMap = myCustom.getVariableMap();
			String varName = current.getName();
			if (!varMap.containsKey(varName) || !myLocalVar.contains(varName)) {
				// throw error that it does not currently exist as a variable
				// message: undefined variable name
			}
		}
	}

	private void checkCommand(InfoNode current) {
		if (current.getToken().equals("Command")) {
			Map<String, InfoNode> funcMap = myCustom.getFunctionMap();
			String funcName = current.getName();
			if (!funcMap.containsKey(funcName) || !myLocalFunc.contains(funcName)) {
				// throw error
				// message: undefined function name
			}
		}
	}

}