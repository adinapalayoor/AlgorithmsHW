package aspalayoor.hw4;

import edu.princeton.cs.algs4.*;

/**
 * How many random directed graphs of V vertices have a cycle? and are connected?
 * 
 * Create a random graph by adding an edge between two vertices u and v with a probability
 * of 50%.
 * 
 * Run the same trial, this time using graphs whose edges each have a probability of 1/N chance
 * of being present.
 */
public class Q3 {
	public static void main(String[] args) {
		System.out.println("Graphs with probability 0.5" );
		System.out.println(String.format("N\t# of Cycles\t# Connected"));
		for(int i =2;i<15;i++) {
			int totalCycles = 0;
			int totalConnected = 0;
			for(int j = 0;j<10000;j++) {
				Digraph D = new Digraph(i);
				for(int k=0;k<D.V();k++) {
					for(int l =0;l<D.V();l++) {
						if(Math.random()<0.5 &&!(k==l)) {
							D.addEdge(k, l);
						}
					}
				}
				DirectedCycle dc = new DirectedCycle(D);
				boolean c = dc.hasCycle();
				if(c == true) {
					totalCycles = totalCycles+1;
				}
				boolean path = true;
				DirectedDFS points = new DirectedDFS(D,0);
				for(int m =1;m<D.V();m++) {
					if(!(points.marked(m)==true)) {
						path =false;
					}
				}
				if(path == true) {
					totalConnected=totalConnected+1;
				}
			}
			System.out.println(String.format("%d\t%d\t\t%d",i,totalCycles,totalConnected));
		}
		System.out.println("Graphs with probability 1/N" );
		System.out.println(String.format("N\t# of Cycles\t# Connected"));
		for(int n =2;n<15;n++) {
			int totalCycle2 = 0;
			int totalConnected2 = 0;
			for(int x = 0;x<10000;x++) {
				Digraph D = new Digraph(n);
				for(int y=0;y<D.V();y++) {
					for(int z =0;z<D.V();z++) {
						if(Math.random()<1.0/n &&!(y==z)) {
							D.addEdge(y, z);
						}
					}
				}
				DirectedCycle check2 = new DirectedCycle(D);
				boolean nc2 = check2.hasCycle();
				if(nc2 == true) {
					totalCycle2 = totalCycle2+1;
				}
				boolean path2 = true;
				DirectedDFS points2 = new DirectedDFS(D,0);
				for(int w =1;w<D.V();w++) {
					if(!(points2.marked(w)==true)) {
						path2 =false;
					}
				}
				if(path2 == true) {
					totalConnected2=totalConnected2+1;
				}
			}
			System.out.println(String.format("%d\t%d\t\t%d",n,totalCycle2,totalConnected2));
		}
	}
}
