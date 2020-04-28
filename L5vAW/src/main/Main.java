package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("A");
		
		BucketSort<Integer> bucketSort = new BucketSort<Integer>();
		QuickSort<Integer> quickSort = new QuickSort<Integer>();
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i=0; i<1000; i++) lista.add(rand.nextInt(100));
		
		
		ArrayList<Integer> toSort = (ArrayList<Integer>) lista.clone();
		Measurement.start();
		quickSort.quickSort(lista);
		System.out.println("pomiar czasu: " + Measurement.end() + "s");
		
		toSort = (ArrayList<Integer>) lista.clone();
		Measurement.start();
		bucketSort.bucketSort(lista);
		System.out.println("pomiar czasu: " + Measurement.end() + "s");
		
		for(Integer i : lista) System.out.print(i + " ");
		System.out.println();
		
		MedianOfMedians<Integer> medianOfMedians = new MedianOfMedians<Integer>();
		
		System.out.println(medianOfMedians.medianOfMedians(lista));

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
