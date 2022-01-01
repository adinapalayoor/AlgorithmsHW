package aspalayoor.hw3;

import algs.hw3.ShakespearePlay;

public class Q3 {

	public static void main(String[] args) {
		for(int i =1;i<=38;i++) {
			BST b = new BST();
			ShakespearePlay h = new ShakespearePlay(i);
			for(String s:h) {
				Integer word = b.get(s);
				if(word == null) {
					b.put(s,1);
				}
				else {
					b.put(s, word+1);
				}
			}
			for(int j=0;j<5;j++) {
				String MOSTCOMMON = b.mostFrequent();
				System.out.print(MOSTCOMMON + "\t");
				b.delete(MOSTCOMMON);
			}
			System.out.println(h.getTitle());
		}

	}

}
