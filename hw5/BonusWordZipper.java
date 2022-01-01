package aspalayoor.hw5;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.*;

// Note that the Day18 implementation of AVL removes <Key,Value> and only uses <Key>
import algs.days.day18.AVL;
import algs.days.day21.BreadthFirstPaths;
import algs.hw5.Dictionary;

/**
 * More complicated ZIPPER.
 */
public class BonusWordZipper {
	
	/**
	 * Main method to execute.
	 *
	 * From console, enter the start and end of the word zipper.
	 * 
	 * Can you beat  117.875 seconds for computing RESTAFF to SHERIFF 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String word1 = args[0];
		String word2 = args[1];
		
		StopwatchCPU cpu = new StopwatchCPU();
		
		// do all work here
		
		System.out.println(cpu.elapsedTime() + " seconds");
	}
}
