package aspalayoor.hw5;

import java.io.FileNotFoundException;
import java.util.Scanner;

// use any classes from Sedgewick...
import edu.princeton.cs.algs4.*;

// Note that the Day18 implementation of AVL removes <Key,Value> and only uses <Key>
import algs.days.day18.AVL;
import algs.hw5.Dictionary;

/**
 * Copy this class into your project area and modify it for problem 1 on HW5.
 */
public class WordZipper {

	/**
	 * Represent the mapping of (uniqueID, 3- and 4-letter words) from String <-> Integer where Integer is vertex id
	 */
	static SeparateChainingHashST<String,Integer> map = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();
	//Graph G = new Graph(map.size());
	/** Store all three-letter and four-letter words (in lowercase). */
	static AVL<String> avl; 

	/**
	 * Return a Queue of words that result by adding a single letter to the three letter word.
	 * 
	 * There are 4*26 possible words that could result by adding a single letter (a-z) at each of the 
	 * four possible spots
	 * 
	 *      E A T
	 *      
	 *     SEAT
	 *      ERAT
	 *       EAST
	 *        EATS
	 *        
	 * It is acceptable for this method to return duplicates in the queue.
	 * 
	 * For example, if the word is "BET" then it could include in its response
	 * "BEET" (where the E is inserted between the B and E) and "BEET" (where
	 * the E is inserted between the E and the T).
	 */
	public static Queue<String> addOne(String three) {
		Queue wordQ = new Queue<String>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		for(char c : alphabet) {
			String word = c + three;
			String wordTwo = three.substring(0,1)+c+three.substring(1,3);
			String wordThree = three.substring(0,2)+c+three.substring(2,3);
			String wordFour = three+c;
			if(map.contains(word)) {
				wordQ.enqueue(word);

			}
			if(map.contains(wordTwo)) {
				wordQ.enqueue(wordTwo);

			}
			if(map.contains(wordThree)) {
				wordQ.enqueue(wordThree);

			}
			if(map.contains(wordThree)) {
				wordQ.enqueue(wordThree);

			}
		}
		return wordQ;
	}

	/**
	 * Return valid words by removing one of the four letters.
	 * 
	 * It is acceptable for this method to return duplicates in the queue.
	 * For example, if the word is 'BEET' then the words returned could 
	 * be {"BEE", "BET", "BET"}
	 */
	public static Queue<String> removeOne(String four) {
		Queue wordQ2 = new Queue<String>();
		String word = four.substring(0,3);
		String wordTwo = four.substring(0,2)+four.substring(3,4);
		String wordThree = four.substring(0,1)+four.substring(2,4);;
		String wordFour = four.substring(1,4);
		if(map.contains(word)) {
			wordQ2.enqueue(word);

		}
		if(map.contains(wordTwo)) {
			wordQ2.enqueue(wordTwo);

		}
		if(map.contains(wordThree)) {
			wordQ2.enqueue(wordThree);

		}
		if(map.contains(wordThree)) {
			wordQ2.enqueue(wordThree);

		}
		return wordQ2;
	}

	/**
	 * Main method to execute.
	 *
	 * From console, enter the start and end of the word ladder.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Use this to contain all three- and four-letter words that you find in dictionary
		avl = new AVL<String>();

		// Construct AVL tree of all three- and four-letter words.
		// Note: you will have to copy this file into your project to access it.
		Scanner sc = Dictionary.words();



		// now construct graph, where each node represents a word, and an edge exists between
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree. Your graph will be an undirected graph.

		// TODO: FILL IN HERE
		int counter = 0;
		while (sc.hasNextLine()) {
			String words = sc.nextLine();
			if(words.length() == 3 ||words.length() == 4) {
				avl.insert(words);
				map.put(words, counter);
				reverse.put(counter, words);
				counter++;
			}
		}
		
		sc.close();  // once done, you can close this resource.
		Graph G = new Graph(map.size());
		Queue<String> connect;
		for(int i=0;i<counter;i++) {
			String s = reverse.get(i);
			if(s.length()==3) {
				connect = addOne(s);
			}
			else{
				connect = removeOne(s);
			}
			while(!connect.isEmpty()) {
				String str = connect.dequeue();
				int stringIndex= map.get(str);
				boolean edge = false;
			
			for(int c : G.adj(stringIndex)){
				if(c == i) {
					edge = true;
				}
			}
			if(!edge) {
				G.addEdge(i, stringIndex);
			}
			}
		}
		// this loop will complete when the user enters in a non-word.
		while (true) {
			StdOut.println("Enter word to start from (all in lower case):");
			String start = StdIn.readString().toLowerCase();
			StdOut.println("Enter word to end at (all in lower case):");
			String end = StdIn.readString().toLowerCase();

			// need to validate that these are both actual four-letter words in the dictionary
			if (!avl.contains(start)) {
				StdOut.println (start + " is not a valid word in the dictionary.");
				System.exit(-1);
			}
			if (!avl.contains(end)) {
				StdOut.println (end + " is not a valid word in the dictionary.");
				System.exit(-1);
			}
			BreadthFirstPaths bfs = new BreadthFirstPaths(G,map.get(start));

			if(bfs.hasPathTo(map.get(end))) {
				Iterable<Integer> path = bfs.pathTo(map.get(end));
				String str = "";
				for(int i :path) {
					str += reverse.get(i)+ " --> ";
				}
				str= str.substring(0,str.length()-4);
				System.out.println(str);

				
			}
			else {
				System.out.println("Not possible");
			}
			



			// Once both words are known to exist in the dictionary, then create a search
			// that finds shortest distance (should it exist) between start and end.
			// be sure to output the words in the word zipper, IN ORDER, from the start to end.
			// IF there is no word zipper possible, then output "NONE POSSIBLE."

		}

	}
}
