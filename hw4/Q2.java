package aspalayoor.hw4;

import algs.days.day21.BreadthFirstPaths;
import algs.hw4.map.*;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;

/**
 * The goal of this question is to:
 * 
 * 1. Find the western-most location in Massachusetts
 * 2. Find the eastern-most location in Massachusetts
 * 3. Determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT
 * 4. Next create a copy of the highway graph that removes all line segments from I-90, the 
 *    Massachusetts Turnpike toll road.
 * 5. From this copy, determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT.
 */
public class Q2 {
	
	/**
	 * This method must create a copy of the graph, which you can do by recreate a graph with 
	 * the same number of vertices as the original one, BUT you only add an edge to the copy
	 * if the edge in the original graph IS NOT EXCLUSIVELY a line segment from the Mass Pike.
	 * 
	 * For example, in the data set you will see two nodes:
	 * 
	 * 		I-90@49|MA 42.169702 -72.580876
	 * 		I-90@51|MA 42.161558 -72.541995
	 * 
	 * These lines correspond to vertex #639 (the first one @49) and vertex #641 (the second one @51).
	 * Because the label for both of these vertices includes "I-90@" this edge must not appear in 
	 * the copied graph, since it is a highway segment exclusively on the Mass Turnpike.
	 * 
	 * Note that the edge is eliminated only when BOTH are present. For example, the following
	 * line segment will remain:
	 * 
	 * 		I-95(23)/MA128	                ==> vertex #705
	 * 		I-90@123B&I-95@24&MA128@24(95)  ==> vertex #1785
	 */
	static Information remove_I90_segments(Information info) {
		Graph copy = null;
		copy = new Graph(info.graph.V());
		for(int i =0;i<info.graph.V();i++) {
			String labeli = info.labels.get(i);
			for(int j:info.graph.adj(i)) {
				String labelj = info.labels.get(j);
				if (labelj.contains("I-90@") && labeli.contains("I-90@")) {
					continue;
				}
				copy.addEdge(i, j);

			}
		}
		
		Information newInfo = new Information(copy, info.positions, info.labels);
		return newInfo;
	}
	
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int westernMostVertex(Information info) {
		//Information information = HighwayMap.undirectedGraph();
		int west = -1;
		float smallestValue = Integer.MAX_VALUE;
		for(int id:info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.longitude<smallestValue) {
				smallestValue = gps.longitude;
				west = id;
			}
		}
		return west;
	}
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int easternMostVertex(Information info) {
		//Information information = HighwayMap.undirectedGraph();
		int east = -1;
		float largestValue = Integer.MIN_VALUE;
		for(int id:info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.longitude>largestValue) {
				largestValue = gps.longitude;
				east = id;
			}
		}
		return east;
	}
	
	/** 
	 * This helper method returns the southern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int southernMostVertex(Information info) {
		//Information information = HighwayMap.undirectedGraph();
		int south = -1;
		float smallestValue = Integer.MAX_VALUE;
		for(int id:info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.latitude<smallestValue) {
				smallestValue = gps.latitude;
				south = id;
			}
		}
		return south;
	}
	
	/** 
	 * This helper method returns the northern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int northernMostVertex(Information info) {
		//Information information = HighwayMap.undirectedGraph();
		int north = -1;
		float largestValue = Integer.MIN_VALUE;
		for(int id:info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.latitude>largestValue) {
				largestValue = gps.latitude;
				north = id;
			}
		}
		return north;
	}
	
	public static void main(String[] args) {
		Information info = HighwayMap.undirectedGraph();
		int west= westernMostVertex(info);
		int east= easternMostVertex(info);
		int north= northernMostVertex(info);
		int south= southernMostVertex(info) ;
		BreadthFirstPaths bfp = new BreadthFirstPaths(info.graph, west);
		Iterable<Integer> s = bfp.pathTo(east);
		int count = 0;
		for (int i : s) { count++; }
		count -= 1; // one fewer edge than # of vertices.
		System.out.println("BFS: " + info.labels.get(west) + "(" + west + ") to " + info.labels.get(east) + "(" + east + ") has " + count + " edges.");
		
		bfp = new BreadthFirstPaths(info.graph, south);
		s = bfp.pathTo(north);
		count = 0;
		for (int i : s) { count++; }
		count -= 1; // one fewer edge than # of vertices.
		System.out.println("BFS: " + info.labels.get(south) + "(" + south + ") to " + info.labels.get(north) + "(" + north + ") has " + count + " edges.");
		
		DepthFirstPaths dfp = new DepthFirstPaths(info.graph, west);
		s = dfp.pathTo(east);
		count = 0;
		for (int i : s) { count++; }
		count -= 1; // one fewer edge than # of vertices.
		System.out.println("DFS: " + info.labels.get(west) + "(" + west + ") to " + info.labels.get(east) + "(" + east + ") has " + count + " edges.");

		dfp = new DepthFirstPaths(info.graph, south);
		s = dfp.pathTo(north);
		count = 0;
		for (int i : s) { count++; }
		count -= 1; // one fewer edge than # of vertices.
		System.out.println("DFS: " + info.labels.get(west) + "(" + west + ") to " + info.labels.get(east) + "(" + east + ") has " + count + " edges.");
		
		System.out.println("Now without Mass Pike edges...");
		Information info2 = remove_I90_segments(info);
		
		bfp = new BreadthFirstPaths(info2.graph, west);
		s = bfp.pathTo(east);
		count = 0;
		for (int i : s) { count++; }
		count -= 1; // one fewer edge than # of vertices.
		System.out.println("BFS: " + info2.labels.get(west) + "(" + west + ") to " + info2.labels.get(east) + "(" + east + ") has " + count + " edges.");
		
		bfp = new BreadthFirstPaths(info2.graph, south);
		s = bfp.pathTo(north);
		count = 0;
		for (int i : s) { count++; }
		count -= 1; // one fewer edge than # of vertices.
		System.out.println("BFS: " +info2.labels.get(south) + "(" + south + ") to " + info2.labels.get(north) + "(" + north + ") has " + count + " edges.");

	}
}
