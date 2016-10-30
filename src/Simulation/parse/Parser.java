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






}