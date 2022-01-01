package aspalayoor.hw1;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.*;

/**
 * Complete this implementation that takes a postfix expression and converts it into
 * an Infix Expression using a fixed Capacity stack. Also perform the necessary 
 * computation to produce its value
 * 
 * Using the postfix expression as input
 * 
 *     3 6 + 5 * 8 2 - /
 *  
 * should produce the following as output:
 * 
 *     (((3 + 6) * 5) / (8 - 2)) = 7.5
 *
 * Note that postfix expressions do not need parentheses, which is one of their
 * major selling points.
 */
public class Q3_PostFixToInfix {

	public static void main(String[] args) {

		FixedCapacityStack<String> exprs = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Double> vals = new FixedCapacityStack<Double>(100);

		while (!StdIn.isEmpty()) {
	
			String s = StdIn.readString();
			if("+-*/".contains(s)) {
				String first = exprs.pop();
				String second = exprs.pop();
				exprs.push("(" + second + " " + s + " " + first + ")");
				double v = vals.pop();
				if (s.equals ("+")) { v = vals.pop() + v; vals.push(v); }
				else if (s.equals ("-")) { v = vals.pop() - v; vals.push(v); }
				else if (s.equals ("*")) { v = vals.pop() * v; vals.push(v); }
				else if (s.equals ("/")) { v = vals.pop() / v; vals.push(v); }
			}
			else {
				exprs.push(s);
				vals.push(Double.valueOf(s));
			}

		}
		
		StdOut.print(exprs.pop() + " = " + vals.pop());

	}
}
