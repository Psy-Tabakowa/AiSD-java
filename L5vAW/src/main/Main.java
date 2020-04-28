package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

	public static void main(String[] args) {		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i=0; i<1000000; i++) lista.add(rand.nextInt());
		
		BucketSort<Integer> bucketSort = new BucketSort<Integer>();
		QuickSort<Integer> quickSort = new QuickSort<Integer>();
		
		ArrayList<Integer> toSort = (ArrayList<Integer>) lista.clone();
		Measurement.start();
		quickSort.quickSort(toSort);
		double time = Measurement.end();
		System.out.println("quick sort: " + time + "s");
		
		toSort = (ArrayList<Integer>) lista.clone();
		Measurement.start();
		bucketSort.bucketSort(toSort);
		time = Measurement.end();
		System.out.println("bucket sort: " + time + "s");
		
		int [] arrayToSort = new int[lista.size()];
		for(int i=0; i<lista.size(); i++) arrayToSort[i] = lista.get(i);
		Measurement.start();
		Arrays.sort(arrayToSort);
		time = Measurement.end();
		System.out.println("Arrays.sort: " + time + "s");
		
		toSort = (ArrayList<Integer>) lista.clone();
		Measurement.start();
		Collections.sort(toSort);
		time = Measurement.end();
		System.out.println("Collections.sort: " + time + "s");
		
	}
}

class Measurement {
	private static long start;
	
	public static void start() {
		start = System.nanoTime();
	}
	
	public static double end() {
		return (System.nanoTime() - start)/1000000000.0;
	}
}
