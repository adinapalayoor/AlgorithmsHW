package aspalayoor.hw2;

import algs.hw2.Card;
import algs.hw2.Suit;

public class Q1 {

	public static void main(String[] args) {
		
		//testing MyDeck methods
		MyDeck d1 = new MyDeck(13);

		System.out.println(d1.peekTop());

		System.out.println(d1.peekBottom());

		Card a = new Card(Suit.SPADES,10);
		System.out.println(d1.match(a,49));

		System.out.println(d1.copy());

		System.out.println(d1.size());

		System.out.println(d1.cutInHalf());

		System.out.println(d1.isInOrder());
		
		
		MyDeck d7 = new MyDeck(13);
		System.out.println(d7.isInReverseOrder());

		System.out.println(d1.representation());
	}



}







