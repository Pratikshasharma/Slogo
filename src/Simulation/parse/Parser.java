package Simulation.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Simulation.Node.Node;
import Simulation.Node.TestNode;


public class Parser {
	private final static String LANG_PATH = "resources/languages/";
	private final static String DEFAULT_LANG = "English";
	private final static String SYNTAX_LANG = "Syntax";
	
	private static TypeDictionary lang;
	private List<Node> myList;
	
	
	public Parser(String language) {
		lang = new TypeDictionary();
		lang.addPatterns(LANG_PATH + language);
		lang.addPatterns(LANG_PATH + SYNTAX_LANG);
		myList = new ArrayList<Node>();
	}
	
	public Parser() {
		this(DEFAULT_LANG);
	}
	
    // utility function that reads given file and returns its entire contents as a single string
    public String readFileToString (String filename) throws FileNotFoundException {
        final String END_OF_FILE = "\\z";
        Scanner input = new Scanner(new File(filename));
        input.useDelimiter(END_OF_FILE);
        String result = input.next();
        input.close();
        return result;
    }

    // given some text, prints results of parsing it using the given language
    public void parseText (String[] text) {
        for (String s : text) {
            if (s.trim().length() > 0) {
                //System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
            	myList.add(new TestNode(s, lang.getSymbol(s)));
            }
            else {
            	System.out.println("Did not reach over length 0");
            }
        }
        System.out.println();
        for (Node n : myList) {
        	TestNode node = (TestNode) n;
        	System.out.println(n.execute());
        	System.out.println(node.getName());
        	System.out.println(node.getToken());
        	
        }
        
    }

}
