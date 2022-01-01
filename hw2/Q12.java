package aspalayoor.hw2;

public class Q12 {

	public static void main(String[] args) {
		//1.2 - returns the number of out shuffles for the deck to be in order(1.2)
				System.out.println("Max Rank	Out Shuffles");
				for(int max_rank=1;max_rank<=20;max_rank++) {

					MyDeck d3 = new MyDeck(max_rank);

					d3.out();
					int shuffles = 1;
					while(!d3.isInOrder()) {
						d3.out();
						shuffles = shuffles +1;
					}
					System.out.println(max_rank + "\t" + shuffles);
				}

	}

}
