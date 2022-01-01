package aspalayoor.hw4;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Q1 {
	/**
	 * Complete this implementation that takes a postfix expression string and converts it into
	 * an Expression node using a fixed Capacity stack. When done, an Expression node will
	 * be returned.
	 * 
	 * Using the postfix expression as input
	 * 
	 *     3 1 + 4 / 1 5 + 9 * 2 6 * - *
	 *  
	 * should produce the expression from the homework, namely
	 * 
	 *     (((3+1)/4 * (((1+5)*9)-(2*6)))
	 *
	 * Note that postfix expressions do not need parentheses, which is one of their
	 * major selling points.
	 */
	public static void main(String[] args) {

		// since everything IS an expression (even Values) you only need a single stack.
		FixedCapacityStack<Expression> exprs = new FixedCapacityStack<Expression>(100);
		while (!StdIn.isEmpty()) {
			// Read token. push if operator
			String s = StdIn.readString();

			if ("*/+-".contains(s)) {

				if (s.equals ("+")) {
					Expression right = exprs.pop();
					Expression left = exprs.pop();
					
					Add add = new Add(left, right);
					exprs.push(add);
				}
				else if (s.equals ("-")) {
					Expression right = exprs.pop();
					Expression left = exprs.pop();
					
					Subtract sub = new Subtract(left,right);
					exprs.push(sub);
				}
				else if (s.equals ("*")) { 
					Expression right = exprs.pop();
					Expression left = exprs.pop();
					Multiply mul = new Multiply(left,right);
					exprs.push(mul);}
				else if (s.equals ("/")) { 
					Expression right = exprs.pop();
					Expression left = exprs.pop();
					Divide div = new Divide(left,right);
					exprs.push(div);}

			} else {
				// If not an operator, then must be value
				Value v = new Value(Double.parseDouble(s));
				exprs.push(v);
			}

		}
		Expression exp = exprs.pop();
		System.out.println(exp.format() + " = " + exp.eval());
		System.out.println("The height of the expression is:" +exp.height());

	}


}
