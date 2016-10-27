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
	private final static String MAKE = "MakeVariable";

	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	private static TypeDictionary lang;
	private VariableTable myVar;
	private Deque<InfoNode> myList;
	private Deque<InfoNode> myStack;

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
			myList.add(new InfoNode(s, lang.getSymbol(s)));
		}

		InfoNode first = myList.pop();

		first = createTree(first);
		InfoNode current = first;
		while (!myList.isEmpty()) {
			InfoNode nextTree = createTree(myList.pop());
			current.setNext(nextTree);
			current = current.next();
		}
		

		return first;
	}

	private InfoNode makeVariable() {
			InfoNode variable = myList.removeLast();
			InfoNode varNode = createTree(variable);
			myVar.storeVar(variable.getName(), varNode);
			return varNode;
	}

	private boolean checkTo(String[] text) {
		if (text[0].equals(TO)) {
			return true;
		}
		return false;
	}

	private InfoNode createTree(InfoNode current) {

		InfoNode listNode, leftNode, rightNode, middleNode;

		try {
			String stringParam = inputResource.getString(current.getToken());
			System.out.println(stringParam);
			if (stringParam.equals("MAKE")) {
				
			}
			
			int intParam = Integer.parseInt(stringParam);
//			switch (stringParam) {
//			case ("1"):
//				leftNode = createTree(myList.pop());
//				current.setLeft(leftNode);
//
//				break;
//			case ("2"):
//				leftNode = createTree(myList.pop());
//				rightNode = createTree(myList.pop());
//				current.setLeft(leftNode);
//				current.setRight(rightNode);
//				break;
//			case ("3"):
//				leftNode = createTree(myList.pop());
//				rightNode = createTree(myList.pop());
//				middleNode = createTree(myList.pop());
//				current.setLeft(leftNode);
//				current.setRight(rightNode);
//				current.setMiddle(middleNode);
//				break;
//			case ("MAKE"):
//				//do special stuffs
//				break;
//			default:
//				break;
//			}
			while (intParam > 0) {
				listNode = myList.pop();
				current.myParameters.add(createTree(listNode));
				intParam--;
				System.out.println(intParam);
			}
			


		} catch (NoSuchElementException e) {
			// throw new compileException(message: incorrect number of
			// parameter)
		}

		return current;
	}
}