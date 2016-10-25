package Simulation.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Deque;

import Simulation.Node.InfoNode;
import Simulation.Node.Node;

public class Parser {
	private final static String LANG_PATH = "resources/languages/";
	private final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
	private final static String COMMAND = "Command";
	private final static String NO_MATCH = "NO MATCH";
	private final static String COMMENT = "Comment";
	private final static String CONSTANT = "Constant";
	private final static String TO = "To";
	private final static String MAKE = "Make";

	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	private static TypeDictionary lang;
	private VariableTable myVar;
	private Deque<InfoNode> myList;

	public Parser(String language) {
		lang = new TypeDictionary();
		lang.addPatterns(LANG_PATH + language);
		lang.addPatterns(LANG_PATH + SYNTAX_LANG);
		myVar = new VariableTable();
	}

	public Parser() {
		this(DEFAULT_LANG);

	}

	// given some text, prints results of parsing it using the given language
	public InfoNode parseText(String[] text) {
		myList = new ArrayDeque<InfoNode>();

		for (String s : text) {
			if (s.trim().length() > 0) {
				myList.add(new InfoNode(s, lang.getSymbol(s)));
			}
		}
		InfoNode tree;
		if (checkMake(text)) {
			tree = myVar.getVar(text[1]);
		} else if (checkTo(text)) {
			tree = new InfoNode("1", "1"); //CHANGE THIS TO RETRIEVE THE TO FUNCTION RETURN
		} else {
			tree = createTree();
		}
		
		return tree;
	}

	private boolean checkMake(String[] text) {
		if (text[0].equals(MAKE)) {
			String variable = text[1];
			String[] makeVar = new String[text.length-2];
			for (int i=2; i < text.length; i++) {
				makeVar[i-2] = text[i];
			}
			InfoNode varNode = parseText(makeVar);
			myVar.storeVar(variable, varNode);
			return true;
		}
		return false;
	}

	private boolean checkTo(String[] text) {
		if (text[0].equals(TO)) {
			return true;
		}
		return false;
	}

	private InfoNode createTree() {
		Deque<InfoNode> stack = new ArrayDeque<InfoNode>();
		
		while (!myList.isEmpty()) {
			InfoNode current = myList.pop();
			String check = current.getToken();

			addTreeNode(stack, current, check);

		}
		// if there's still stuff at the end but no more commands or something
		// weird like that
		// throw compile error
		InfoNode finalTree = stack.pop();
		if (!stack.isEmpty()) {
			// throw a new error: there were leftover stuff when there shouldn't
			// have been
		}

		return finalTree;
	}

	private void addTreeNode(Deque<InfoNode> bigStack, InfoNode node, String token) {
		InfoNode current = node;
		if (!token.equals(COMMENT)) {
			if (!token.equals(NO_MATCH)) {
				// if it's create custom command (check for "To")
				// new method that uses the myList to find
				// [ bracket, all the nodes in between, then end bracket] THESE
				// ARE VARIABLES LOL.
				if (token.equals(MAKE)) {
					// need a new method: at the beginning, check if it's a make
					// or a to.
					// there's no way to do this in the middle of making the
					// tree.
				}

				// check if use custom command (Command)
				// if no such command, throw error
				// if it's VARIABLE
				// get it and use it
				// if no such variable, throw compile error.
				// split it
				// if size is greater than 1, evaluate the expression
				if (!token.equals(CONSTANT)) {
					current = createTreeNode(bigStack, node);
				}
				bigStack.push(current);

			} else {
				// throw new compiler exception
			}

		}
	}

	private InfoNode createTreeNode(Deque<InfoNode> stack, InfoNode command) {
		// InfoNode[] parameter = new InfoNode[3];
		try {
			String numParam = inputResource.getString(command.getToken());
			switch (numParam) {
			case ("1"):
				command.setLeft(stack.pop());
			case ("2"):
				command.setLeft(stack.pop());
				command.setRight(stack.pop());
			case ("3"):
				command.setLeft(stack.pop());
				command.setRight(stack.pop());
				command.setMiddle(stack.pop());
			default:
			}

			if (!stack.isEmpty()) {
				command.setNext(stack.pop());
			}

		} catch (NoSuchElementException e) {
			// throw new compileException(message: incorrect number of
			// parameter)
		}
		return command;

	}

}
