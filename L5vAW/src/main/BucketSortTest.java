package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BucketSortTest {
	ArrayList<Integer> testList;
	ArrayList<Double> smallList;
	BucketSort<Integer> bucketSort = new BucketSort<Integer>();
	
	@BeforeEach
	void setUp() throws Exception {
		testList = new ArrayList<Integer>();
		Random random = new Random();
		for(int i=0; i< 100; i++)
			testList.add(random.nextInt(100) - 50);
		
		smallList = new ArrayList<Double>();
		smallList.add(Math.PI);
	}
	
	@Test
    final void insertSortTest() {
        bucketSort.insertSort(testList);
        for(int i=1; i<testList.size(); i++)
        	Assertions.assertEquals(testList.get(i-1) <= testList.get(i), true);
    }
	
	@Test
    final void insertSortSmallListTest() {
		ArrayList<Double> nonSorted = (ArrayList<Double>) smallList.clone();
		BucketSort<Double> doubleBucketSort = new BucketSort<Double>();
		doubleBucketSort.insertSort(smallList);
        	Assertions.assertArrayEquals(nonSorted.toArray(), smallList.toArray());
    }
	
	@Test
    final void bucketSortTest() {
        bucketSort.bucketSort(testList);
        for(int i=1; i<testList.size(); i++)
        	Assertions.assertEquals(testList.get(i-1) <= testList.get(i), true);
    }
	
	@Test
    final void bucketSortSmallListTest() {
		ArrayList<Double> nonSorted = (ArrayList<Double>) smallList.clone();
		BucketSort<Double> doubleBucketSort = new BucketSort<Double>();
		doubleBucketSort.bucketSort(smallList);
        	Assertions.assertArrayEquals(nonSorted.toArray(), smallList.toArray());
    }
	
	@Test
    final void sortSortedListTest() {
        bucketSort.bucketSort(testList);
        ArrayList<Integer> sorted = (ArrayList<Integer>) (testList.clone());
        bucketSort.bucketSort(testList);
        Assertions.assertArrayEquals(testList.toArray() , sorted.toArray());
	}
}
