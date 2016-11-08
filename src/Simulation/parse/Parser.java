package Simulation.parse;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import SlogoException.ParserException;
import SlogoException.UserDefinitionException;


/**
 * The class that begins the parsing. Entering this class initiates the 
 * syntax regex checking. When a syntax error/exception is not caught, 
 * it continues to call the produceTree method in TreeFactory class to
 * continue the parsing and creating of the expression tree to send to
 * execution. 
 * 
 * @author joykim
 */
public class Parser {
	public final static String LANG_PATH = "resources/languages/";
	public final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
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

	/**
	 * The central method to parsing the commands. Given a String array of
	 * lines of texts, it separates them into individual words or tokens into
	 * a list of nodes and goes into the TreeFactory class then returns one
	 * tree node. 
	 * 
	 * @param text
	 * @param custom
	 * @return toSend, a successfully made InfoNode to be executed. 
	 */
	public InfoNode parseText(String[] text, CommandStorage custom) {

		InfoNode toSend = null;
		try {
			Deque<InfoNode> nodeList = createNodeList(text);
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
		// System.out.println("Printing Tree");
		// printTree(toSend);

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
				// Semantics check
				if (token.equals("NO MATCH")) {
					throw new ParserException("Incorrect Syntax at " + t);
				}
				list.add(new InfoNode(t, token));
			}

		}
		return list;
	}

}