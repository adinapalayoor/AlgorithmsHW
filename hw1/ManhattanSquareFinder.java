package aspalayoor.hw1;

import algs.hw1.*;
import algs.hw1.api.*;

public class ManhattanSquareFinder implements IManhattanSquareFinder {

	/** 
	 * Return the Coordinate of location in ManhattanSquare containing target.
	 * 
	 * You can inspect the contents of the array for ms using the distance() method.
	 */
	public Coordinate find(ManhattanSquare ms, int target) {
		int distance = ms.distance(0,0, target);
		if(distance==0) {
			return new Coordinate(0,0);
		}
		int newdistance = ms.distance(0,ms.N-1, target);
		if(newdistance==0) {
			return new Coordinate(0,ms.N-1);
		}
		int c = ms.N-1-(newdistance-distance+ms.N-1)/2;
		int r = (distance+newdistance- ms.N+1)/2;	
		return new Coordinate(r,c);
		
	}	

				


	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int n = 1; n < 15; n++) {
			ManhattanSquare ms = new ManhattanSquare(n, 99);
			int numProbes = ms.solver(new ManhattanSquareFinder());
			System.out.println(n + "\t" + numProbes);
		}
	}
}
