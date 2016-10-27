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
	private final static String LANG_PATH = "resources/languages/";
	private final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
	private final static String TO = "To";

	private ResourceBundle inputResource = ResourceBundle.getBundle("resources/CommandInputs");

	private static TypeDictionary lang;
	private CommandStorage myCustom;
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
	public InfoNode parseText(String[] text, CommandStorage custom) {
		myList = new ArrayDeque<InfoNode>();
		myCustom = custom;

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

	private InfoNode createTree(InfoNode current) {

		InfoNode listNode;
		int intParam;

		try {

			if (current.getToken().equals("Variable")) {
				Map<String,Double>varMap = myCustom.getVariableMap();
				if (varMap.containsKey(current.getName())){
					intParam = varMap.get(current.getName()).;
				}
			} else {
				String stringParam = inputResource.getString(current.getToken());
				intParam = Integer.parseInt(stringParam);
			}

			

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
}