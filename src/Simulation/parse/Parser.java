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
import SlogoException.ParserException;
import SlogoException.UserDefinitionException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author joykim
 *
 */
public class Parser {
	public final static String LANG_PATH = "resources/languages/";
	public final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
	private CommandStorage myCommands;
	private boolean killParse;


	private static TypeDictionary lang;


	public Parser(String language) {
		lang = new TypeDictionary();
		lang.addPatterns(LANG_PATH + language);
		lang.addPatterns(LANG_PATH + SYNTAX_LANG);
	}

	public Parser() {
		this(DEFAULT_LANG);

	}
	
	public void printTree(InfoNode print) {
		InfoNode current = print;
		List<InfoNode> parameters;
		while (current != null) {
			System.out.println("Node name: " + current.getName());
			parameters = current.getParameters();
			for (InfoNode n : parameters) {
				System.out.println("Parameter in " + current.getName());
				printTree(n);
			}
			current = current.next();
		}
	}
	
	public static String readFileToString(String filename) throws FileNotFoundException {
		final String END_OF_FILE = "\\z";
		Scanner input = new Scanner(new File(filename));
		input.useDelimiter(END_OF_FILE);
		String result = input.next();
		input.close();
		return result;
	}

	// given some text, prints results of parsing it using the given language
	public InfoNode parseText(String[] text, CommandStorage custom) {
		myCommands = custom;
		killParse = false;
		InfoNode toSend = null;
		Deque<InfoNode> nodeList = createNodeList(text);
		if (killParse) {
			custom.setKillCommands(true);
			return toSend;
		}
		try {
			TreeFactory myFactory = new TreeFactory(custom, nodeList);
			toSend = myFactory.produceTree();
		} catch (UserDefinitionException e) {
			e.showError(e.getMessage());
			toSend = new InfoNode("0", "Constant");
		} catch (ParserException e) {
			custom.setKillCommands(true);
			toSend = null;
			e.showError(e.getMessage());
		}
		//System.out.println("Printing Tree");
		//printTree(toSend);
		
		return toSend;
	}

	private Deque<InfoNode> createNodeList(String[] text) {
		Deque<InfoNode> list = new ArrayDeque<InfoNode>();
		
		for (String s : text) {
			// text should represent the lines of code,
			// so splitting would divide them by token
			String[] tokens = s.trim().split("\\s+");
			if (tokens[0].equals("#")) {
				continue;
			}
			for (String t : tokens) {
				String token = lang.getSymbol(t);
				//Semantics check
				if (token.equals("NO MATCH")) {
					killParse = true;
					return list;
				}
				list.add(new InfoNode(t, token));
			}

		}
		return list;
	}







}