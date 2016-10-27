package Simulation.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import Simulation.Node.InfoNode;


public class ParserMain {

	
	
	// utility function that reads given file and returns its entire contents as
	// a single string
	//put this back in parser class
	public static String readFileToString(String filename) throws FileNotFoundException {
		final String END_OF_FILE = "\\z";
		Scanner input = new Scanner(new File(filename));
		input.useDelimiter(END_OF_FILE);
		String result = input.next();
		input.close();
		return result;
	}
	
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
            String fileInput = readFileToString("data/square.logo");
            // try against different inputs
//            InfoNode list1 = myParser.parseText(examples);
//            list1.printTree();
//            InfoNode list2 = myParser.parseText(userInput.split(WHITESPACE));
//            list2.printTree();
//            InfoNode list3 = myParser.parseText(fileInput.split(WHITESPACE));
//            list3.printTree();
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println(String.format("Could not load pattern file %s", e.getMessage()));
        }
    }
}

//old way of createTree when parameters were assigned nodes and not arraylist
//switch (stringParam) {
//case ("1"):
//	leftNode = createTree(myList.pop());
//	current.setLeft(leftNode);
//
//	break;
//case ("2"):
//	leftNode = createTree(myList.pop());
//	rightNode = createTree(myList.pop());
//	current.setLeft(leftNode);
//	current.setRight(rightNode);
//	break;
//case ("3"):
//	leftNode = createTree(myList.pop());
//	rightNode = createTree(myList.pop());
//	middleNode = createTree(myList.pop());
//	current.setLeft(leftNode);
//	current.setRight(rightNode);
//	current.setMiddle(middleNode);
//	break;
//case ("MAKE"):
//	//do special stuffs
//	break;
//default:
//	break;
//}


//while (test != null) {
//	System.out.println("current: " + test.getName());
//	List<InfoNode> theList = test.myParameters;
//	System.out.println("current's parameters: ");
//	for (InfoNode node : theList) {
//		System.out.println(node.getName());
//	}
//	test = test.next();
//}