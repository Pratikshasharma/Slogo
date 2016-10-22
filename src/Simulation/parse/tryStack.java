package Simulation.parse;

import java.util.Stack;

import Simulation.Node.Node;

//Java program for expression tree
class StackNode {

	char value;
	StackNode left, right;

	StackNode(char item) {
		value = item;
		left = right = null;
	}
}

public class tryStack {

	// A utility function to check if 'c'
	// is an operator

	boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
			return true;
		}
		return false;
	}

	// Utility function to do inorder traversal
	void inorder(StackNode t) {
		if (t != null) {
			inorder(t.left);
			System.out.print(t.value + " ");
			inorder(t.right);
		}
	}

	// Returns root of constructed tree for given
	// postfix expression
	StackNode constructTree(char postfix[]) {
		Stack<StackNode> st = new Stack();
		StackNode t, t1, t2;

		// Traverse through every character of
		// input expression
		for (int i = 0; i < postfix.length; i++) {

			// If operand, simply push into stack
			if (!isOperator(postfix[i])) {
				System.out.println("This is an operand " + postfix[i]);
				t = new StackNode(postfix[i]);
				st.push(t);
			} else // operator
			{
				System.out.println("This is an operaTOR " + postfix[i]);
				t = new StackNode(postfix[i]);

				// Pop two top nodes
				// Store top
				t1 = st.pop(); // Remove top
				System.out.println("t1 == " + t1.value);
				t2 = st.pop();
				System.out.println("t2 == " + t2.value);

				// make them children
				t.right = t1;
				t.left = t2;

				// System.out.println(t1 + "" + t2);
				// Add this subexpression to stack
				st.push(t);
			}
		}

		// only element will be root of expression
		// tree
		t = st.peek();
		st.pop();

		return t;
	}

	public static void main(String args[]) {

		tryStack et = new tryStack();
		String postfix = "ab+ef*g*-";
		char[] charArray = postfix.toCharArray();
		StackNode root = et.constructTree(charArray);
		System.out.println("infix expression is");
		et.inorder(root);

	}
}