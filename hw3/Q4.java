package aspalayoor.hw3;



import algs.days.day18.AVL;
import edu.princeton.cs.algs4.StdRandom;


public class Q4 {

	public static void main(String[] args) {
		int maxHeight =-1;
		int numOfHeights =0;
		int Heightchange=0;
		for(int i =1;i<=40;i++) {
			
			for(int j= 1;j<=10000;j++) {
				
				AVL<Integer>tree= new AVL<Integer>();
				for(int x=1;x<=i;x++) {

					tree.insert(StdRandom.uniform(100));
					
					if(tree.height()>maxHeight) {
						maxHeight =tree.height();
						Heightchange=i;
						numOfHeights =0;}
					
					if(tree.height()==maxHeight) {
						numOfHeights++;
					}
				}
				
			}
			if(Heightchange==i) {
				System.out.println(i+"\t"+maxHeight +"\t"+numOfHeights);
			}
		}
	}
}

