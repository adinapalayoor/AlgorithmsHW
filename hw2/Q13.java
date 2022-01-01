package aspalayoor.hw2;



public class Q13 {

	public static void main(String[] args) {
		//1.3 - returns the number of in shuffles for the deck to be in reverse order
				System.out.println("Max Rank	Shuffles to reverse");
					MyDeck d3 = new MyDeck(13);
					d3.in();
					int shuffles = 1;
					while(!d3.isInReverseOrder()) {
						d3.in();
						shuffles = shuffles +1;
					}
					System.out.println(13 + "\t" + shuffles);
	
					}
				

	}
	


