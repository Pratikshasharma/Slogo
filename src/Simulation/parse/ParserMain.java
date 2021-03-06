package Simulation.parse;

import java.io.FileNotFoundException;
import java.util.List;

import Simulation.Node.InfoNode;


public class ParserMain {

	
    public static void main (String[] args) {
    	Parser myParser = new Parser();
    	
        final String WHITESPACE = "\\p{Space}";
        String[] examples = {
            "",
            "# foo",
            "foo #",
            "#",
            "fd",
            "FD",
            "forwardd",
            "equalp",
            "equal?",
            "equal??",
            "+",
            "SuM",
            "-",
            "*",
            "/",
            "%",
            "~",
            "+not",
            "not+",
            "++",
            "+*+",
            "or",
            "FOR",
            "allOrNothing",
            "all_or_nothing",
            "allOr_nothing?",
            "allOr?nothing_",
            ":allornothing",
            "PI",
            "90",
            "9.09",
            "9.0.0",
            "[",
            "]",
            "(",
            ")"
        };

        try {
            String userInput = "fd 50 rt 90 BACK :distance Left :angle";
            String fileInput = myParser.readFileToString("data/square.logo");
            // try against different inputs
            List<InfoNode> list1 = myParser.parseText(examples);
            myParser.printList(list1);
            List<InfoNode> list2 = myParser.parseText(userInput.split(WHITESPACE));
            myParser.printList(list2);
            List<InfoNode> list3 = myParser.parseText(fileInput.split(WHITESPACE));
            myParser.printList(list3);
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println(String.format("Could not load pattern file %s", e.getMessage()));
        }
    }
}
