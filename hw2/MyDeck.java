package aspalayoor.hw2;

import algs.hw2.Card;
import algs.hw2.Deck;
import algs.hw2.Node;
import algs.hw2.Suit;

/**
 * COPY THIS CLASS into your development area and complete it.
 * @author Home
 *
 */
public class MyDeck extends Deck {
	int N;
	/**
	 * Ensure that no one OUTSIDE of this class invokes the no-argument constructor. You will find
	 * it useful to have this constructor within the copy() method since it must return an accurate
	 * copy of the current Deck, and it will first need to construct an "empty" MyDeck object
	 * without using the MyDeck(int max_rank) constructor.
	 * 
	 */
	protected MyDeck() {
		// You do not need to modify this method. This constructor exists to ensure that 
		// within this class, you can construct an empty MyDeck whose first and last are null.
	}

	/** 
	 * Construct a playing deck with {max_rank} cards in specific order.
	 * 
	 * Once done, the linked list of card Nodes must represent a deck that looks like the following (if 
	 * {max_rank} were 3). The suites are ordered Club < Diamond < Hearts < Spades.
	 * 
	 * AC -> 2C -> 3C -> AD -> 2D -> 3D -> AH -> 2D -> 3H -> AS -> 2S -> 3S
	 * 
	 * Note your deck will have 4*{max_rank} cards.
	 * 
	 * Performance must be O(N) where N is max_rank.
	 */
	public MyDeck(int max_rank) {

		Suit s = null;
		int size = 4*max_rank;
		this.N=size;
		for(int j=1;j<=4;j++) {
			for(int r=1;r<=max_rank;r++) {

				if(j==1) {s = Suit.CLUBS; }	
				if(j==2) {s = Suit.DIAMONDS;}
				if(j==3) {s= Suit.HEARTS;}
				if(j==4) {s= Suit.SPADES;}

				Node oldlast = last;
				last = new Node(new Card(s,r));
				last.next = null;
				if(first == null) {
					first = last;
				}
				else {
					oldlast.next = last;
				}
			}
		}
	}

	@Override
	public Card peekTop() {

		return first.card;
	}

	@Override
	public Card peekBottom() {
		return last.card;
	}

	@Override
	public boolean match(Card c, int n) {
		Node elementfir =first;
		while(n>1) {
			elementfir =elementfir.next;
			n--;

		}
		return elementfir.card.equals(c);
	}

	@Override
	public Deck copy() {
		MyDeck newDeck = new MyDeck();
		Node copy = null;
		Node last = null;
		Node n = first;
		while(n != null) {
			if(newDeck.first==null) {
				copy = new Node (n.card);
				newDeck.first =copy;
				last =copy;
			}
			else {
				last.next = new Node(n.card);
				last = last.next;
			}
			n= n.next;
		}
		newDeck.N=N;
		return newDeck;
	}

	@Override
	public int size() {
		return this.N;
	}

	@Override
	protected Node cutInHalf() {
		Node n1=first;
		Node n2 = first;

		while(n1 != null) {
			n2 = n2.next;

			if(n2 == null || n2.next == null) {
				Node secondHalf =n1.next;
				n1.next =null;

				this.N = this.N/2;
				return secondHalf;
			}
			n2 = n2.next;
			n1 = n1.next;
		}
		throw new RuntimeException("Deck has an odd amount of cards");
	}

	@Override
	public void out() {
		Node r = cutInHalf();
		Node l = first;

		Node a =null;
		Node tail =null;

		while(l != null) {
			if(a == null) {
				a = l;
				l = l.next;
				a.next=r;


			}
			else {
				tail.next = l;
				l =l.next;
				tail = tail.next;
				tail.next =r;
			}
			tail = r;
			r = r.next;
		}
		first = a;
		last = tail;

		this.N = this.N*2;
	}

	@Override
	public void in() {
		Node r = cutInHalf();
		Node l = first;

		Node a =null;
		Node tail =null;

		while(l != null) {
			if(a == null) {
				a = r;
				r = r.next;
				a.next = l;
				tail = l;

			}
			else {
				tail.next = r;
				r =r.next;
				tail=tail.next;
				tail.next = l;
				tail = tail.next;
			}
			tail = l;
			l = l.next;
		}
		first = a;
		last = tail;

		this.N = this.N*2;

	}

	@Override
	public String representation() {
		StringBuffer s = new StringBuffer();
		Node f =first;
		while(f != null) {
			s.append(f.card).append(" ");
			f=f.next;
		}
		return s.toString();
	}

	@Override
	public boolean isInOrder() {
		
		Node left = first;
		Suit[] suits = new Suit[] {Suit.CLUBS,Suit.DIAMONDS, Suit.HEARTS,Suit.SPADES};

		for(Suit i:suits) {
			for(int j =1;j<=N/4;j++) {
				if(left.card.suit !=i) {
					return false;
				}
				if(left.card.rank != j) {
					return false;
				}
				left =left.next;
			}
		}
		return true;
	}

	@Override
	public boolean isInReverseOrder() {
		
		Node n1 = first;
		
		while(n1!=null && n1.next!=null) {
			
			Card nextC = n1.next.card;
			Card currentC = n1.card;
			
			int currentS = currentC.suit.getValue();
			int nextS = nextC.suit.getValue();
			
			if(currentS == nextS && currentC.rank < nextC.rank) {
				return false;
			}
			
			if(nextS > currentS) {
				return false;
			}
			
			n1 =n1.next;
		}
		return true;
	}
}
