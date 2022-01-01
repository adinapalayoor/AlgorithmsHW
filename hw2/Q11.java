package aspalayoor.hw2;

public class Q11 {

	public static void main(String[] args) {
		//1.1 - returns the number of in shuffles for the deck to be in order
				System.out.println("Max Rank	In Shuffles");
				for(int max_rank=1;max_rank<=20;max_rank++) {

					MyDeck d2 = new MyDeck(max_rank);

					d2.in();
					int shuffles = 1;
					while(!d2.isInOrder()) {
						d2.in();
						shuffles = shuffles +1;
					}
					System.out.println(max_rank + "\t" + shuffles);
				}

	}

}
