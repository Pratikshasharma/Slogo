package Simulation.parse;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;
import Simulation.CommandStorage;
import Simulation.Node.InfoNode;
import SlogoException.ParserException;
import SlogoException.UserDefinitionException;


/**
 * 
 * @author joykim
 *
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

	// given some text, prints results of parsing it using the given language
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