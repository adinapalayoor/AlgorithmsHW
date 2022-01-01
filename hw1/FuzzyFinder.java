package aspalayoor.hw1;

import algs.hw1.*;
import algs.hw1.api.*;

/**
 * Copy this class into your USERID.hw1 package and complete it.
 */
public class FuzzyFinder implements IFuzzySquareFinder {

	/**
	 * Return the Coordinate(r,c) where target exists. If it is not in 
	 * the 2D array, return null.
	 * 
	 * You can inspect the contents of the array for fs using the probe3x3() method.
	 */
	public Coordinate find(FuzzySquare fs, int target) {
		int row=0;
		int column=0;
		for(int i=1;i<fs.N;i+=2) {
			for(int j=1;j<fs.N;j+=2) {
				if(fs.probe3x3(i, j, target) == 0) {
					if(fs.probe3x3(i, j-1, target)!= 0) {
						column = j+1;
					}
					else if(fs.probe3x3(i, j+1, target) !=0) {
						column=j-1;
					}
					else {
						column = j;
					}
				}
			}
			if(fs.probe3x3(i, column, target) == 0) {
				if(fs.probe3x3(i-1, column, target)!=0) {
					row = i+1;
				}
				else if(fs.probe3x3(i+1, column, target)!=0) {
					row=i-1;
				}
				else {
					row=i;
				}
				return new Coordinate(row,column);
			}
		}
		return null;
	}


	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 5; i < 40; i++) {
			FuzzySquare fs = new FuzzySquare(i, 99);
			fs.solver(new FuzzyFinder());
			System.out.println(i + "\t" + fs.getNumProbes());
		}
	}
}
