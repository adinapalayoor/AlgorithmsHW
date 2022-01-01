package aspalayoor.hw3;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;
import algs.days.day16.ComparableTimSort;
import algs.hw3.CountedItem;
import algs.hw3.PrimitiveTimSort;

/**
 * 
 * Use the existing SortTrial class, and write your own for your implementation
 * of TimSort and also the HeapSort 
 * 
 * https://shakespeare.folger.edu/shakespeares-works/hamlet/download/
 * 
 * What is the longest word which is not a modern English word, according to
 * our dictionary?
 */
public class Q1 {
	
	/** Return time to sort array using merge sort. */
	public static double mergeSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU(); 
		edu.princeton.cs.algs4.Merge.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using quick sort. */
	public static double quickSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Quick.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Insertion Sort. */
	public static double insertionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Insertion.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Selection Sort. */
	public static double selectionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Selection.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Heap Sort. */
	public static double heapSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Heap.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Primitive Tim Sort. */
	public static double primitiveTimSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		PrimitiveTimSort.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Optimized Tim Sort. */
	public static double builtinSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		ComparableTimSort.sort(A);
		return start.elapsedTime();
	}
	
	/** Determine if the array is sorted. */
	public static boolean isSorted(Comparable[] A) {
		for(int i=0; i<A.length-1;i++) {
			if(A[i].compareTo(A[i+1])>0) {
				return false;
			}
		}
		return true;
	}

	/** 
	 * Given a sorted array of CountedItem<String> objects, ensure that for 
	 * any two index positions, i and j, if A[i] is equal to A[j] and i < j, 
	 * then A[i].earlier(A[j]) is true.
	 * 
	 * Performance must be O(N).
	 */
	public static boolean isSortedArrayStable(CountedItem[] A) {
		for(int i=0;i<A.length-1;i++) {
			if(A[i].equals(A[i+1])&&A[i].earlier(A[i+1])==false) {
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * Given an array of integers, return a CountedItem<Integer> array. If you construct
	 * and add the objects from left to right, then for two duplicate values A[i] and A[j],
	 * you know that the counter for A[i] is smaller than the counter for A[j] if i < j. 
	 */
	static CountedItem<Integer>[] toCountedArray(Integer vals[]) {
		CountedItem<Integer>[] copy = new CountedItem[vals.length];
		for (int i  = 0; i < copy.length; i++) {
			copy[i] = new CountedItem<>(vals[i]);
		}
		
		return copy;
	}
	
	public static void trial1_1() {
		System.out.println("Q1.1");
		
		// create array of integers with opportunities for duplicates
		Integer vals[] = new Integer[4096];
		for (int i = 0; i < vals.length; i++) { 
			vals[i] = StdRandom.uniform(128); 
			}
		CountedItem<Integer>[] heapvals = toCountedArray(vals);
		heapSort(heapvals);
		boolean h = isSortedArrayStable(heapvals);
		System.out.println("HeapSort:  "+ h);
		
		CountedItem<Integer>[] insertvals = toCountedArray(vals);
		insertionSort(insertvals);
		boolean i = isSortedArrayStable(insertvals);
		System.out.println("InsertionSort:  " + i);
		
		CountedItem<Integer>[] mergevals = toCountedArray(vals);
		mergeSort(mergevals);
		boolean m = isSortedArrayStable(mergevals);
		System.out.println("MergeSort:  " + m);
		
		CountedItem<Integer>[] quickvals = toCountedArray(vals);
		quickSort(quickvals);
		boolean q = isSortedArrayStable(quickvals);
		System.out.println("QuickSort:  " + q);
		
		CountedItem<Integer>[] selectionvals = toCountedArray(vals);
		selectionSort(selectionvals);
		boolean s = isSortedArrayStable(selectionvals);
		System.out.println("SelectionSort:  " + s);
		
		CountedItem<Integer>[] primvals = toCountedArray(vals);
		primitiveTimSort(primvals);
		boolean p = isSortedArrayStable(primvals);
		System.out.println("TimSort Primitive:  " + p);
		
		CountedItem<Integer>[] optivals = toCountedArray(vals);
		builtinSort(mergevals);
		boolean o = isSortedArrayStable(optivals);
		System.out.println("TimSort Optimized:  " + o);

		
		

		// using this SAME ARRAY, create different CountedItem<> arrays and 
		// determine which of the sorting algorithms are stable, and which ones are not.
	}
	
	public static void trial1_2() {
		System.out.println("Q1.2");
		System.out.println( "\tN\tMerge\tTimSort\tPrimTS\tQuick\tHeap");
		for (int n = 1048576; n <=16777216; n*=2) { 
			Integer A[] = new Integer[n];
			for (int i  = 0; i < A.length; i++) {
				A[i] = StdRandom.uniform(n);
			}
			Integer Acopy2[] = new Integer[n];
			System.arraycopy(A, 0, Acopy2, 0, A.length);
			double h = heapSort(Acopy2); 
			
			Integer Acopy[] = new Integer[n];
			System.arraycopy(A, 0, Acopy, 0, A.length);
			double m = mergeSort(Acopy);

			Integer Acopy4[] = new Integer[n];
			System.arraycopy(A, 0, Acopy4, 0, A.length);
			double p = primitiveTimSort(Acopy4);

			
			Integer Acopy3[] = new Integer[n];
			System.arraycopy(A, 0, Acopy3, 0, A.length);
			double q = quickSort(Acopy3);

			
			Integer Acopy5[] = new Integer[n];
			System.arraycopy(A, 0, Acopy5, 0, A.length);
			double o = builtinSort(Acopy5);

			System.out.println(String.format("%10d\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f" ,n ,m , o ,p ,q,h));
		}
		

		}
	
	
	public static void trial1_3() {
		System.out.println("Q1.3");
		System.out.println( "\tN\tMerge\tTimSort\tPrimTS\tQuick\tHeap");
		for (int n = 1048576; n <=16777216; n*=2) { 
			Integer A[] = new Integer[n];
			for (int i  = 0; i < A.length; i++) {
				A[i] = StdRandom.uniform(n/512);
			}
			Integer Acopy2[] = new Integer[n];
			System.arraycopy(A, 0, Acopy2, 0, A.length);
			double h = heapSort(Acopy2); 
			
			Integer Acopy[] = new Integer[n];
			System.arraycopy(A, 0, Acopy, 0, A.length);
			double m = mergeSort(Acopy);

			Integer Acopy4[] = new Integer[n];
			System.arraycopy(A, 0, Acopy4, 0, A.length);
			double p = primitiveTimSort(Acopy4);

			
			Integer Acopy3[] = new Integer[n];
			System.arraycopy(A, 0, Acopy3, 0, A.length);
			double q = quickSort(Acopy3);

			
			
			Integer Acopy5[] = new Integer[n];
			System.arraycopy(A, 0, Acopy5, 0, A.length);
			double o = builtinSort(Acopy5);

			System.out.println(String.format("%10d\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f" ,n ,m , o ,p ,q,h));
		}

	}
	
	public static void trial1_4() {
		System.out.println("Q1.4");
		System.out.println( "\tN\tTimSort\tMerge\tPrimTS\tQuick\tHeap");
		for (int n = 1048576; n <=16777216; n*=2) { 
			Integer A[] = new Integer[n];
			for (int i  = 0; i < A.length; i++) {
				A[i] = n-1-i;
			}
			Integer Acopy2[] = new Integer[n];
			System.arraycopy(A, 0, Acopy2, 0, A.length);
			double h = heapSort(Acopy2); 
			
			Integer Acopy[] = new Integer[n];
			System.arraycopy(A, 0, Acopy, 0, A.length);
			double m = mergeSort(Acopy);

			Integer Acopy4[] = new Integer[n];
			System.arraycopy(A, 0, Acopy4, 0, A.length);
			double p = primitiveTimSort(Acopy4);

			
			Integer Acopy3[] = new Integer[n];
			System.arraycopy(A, 0, Acopy3, 0, A.length);
			double q = quickSort(Acopy3);

			
			
			Integer Acopy5[] = new Integer[n];
			System.arraycopy(A, 0, Acopy5, 0, A.length);
			double o = builtinSort(Acopy5);

			System.out.println(String.format("%10d\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f" ,n ,o , m ,p ,q,h));
		}
	}
	
	public static void main(String[] args) {
		trial1_1();
		trial1_2();
		trial1_3();
		trial1_4();
	}
}
