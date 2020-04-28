package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuickSortTest {

	ArrayList<Integer> testList;
	ArrayList<Double> smallList;
	QuickSort<Integer> quickSort = new QuickSort<Integer>();
	
	@BeforeEach
	void setUp() throws Exception {
		testList = new ArrayList<Integer>();
		Random random = new Random();
		for(int i=0; i< 100; i++)
			testList.add(random.nextInt(100));
		
		smallList = new ArrayList<Double>();
		smallList.add(Math.PI);
	}
	
	@Test
    final void sortTest() {
        quickSort.quickSort(testList);
        for(int i=1; i<testList.size(); i++)
        	Assertions.assertEquals(testList.get(i-1) <= testList.get(i), true);
    }
	
	@Test
    final void sortSmallListTest() {
		ArrayList<Double> nonSorted = (ArrayList<Double>) smallList.clone();
		QuickSort<Double> doubleQuickSort = new QuickSort<Double>();
		doubleQuickSort.quickSort(smallList);
        	Assertions.assertArrayEquals(nonSorted.toArray(), smallList.toArray());
    }
	
	@Test
    final void partialSortTest() {
		Random random = new Random();
		int left = random.nextInt(100);
		int right = random.nextInt(100);
		if(left > right) {
			int temp = left;
			left = right;
			right = temp;
		}
        quickSort.quickSort(testList, left, right);
        for(int i=left; i<right; i++)
        	Assertions.assertEquals(testList.get(i) <= testList.get(i+1), true);
    }
	
	@Test
    final void sortSortedListTest() {
        quickSort.quickSort(testList);
        ArrayList<Integer> sorted = (ArrayList<Integer>) (testList.clone());
        quickSort.quickSort(testList);
        Assertions.assertArrayEquals(testList.toArray() , sorted.toArray());
    }
	
	@Test
    final void quickSortTest4Exceptions() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
        	quickSort.quickSort(testList, 100, 4);
        });
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	quickSort.quickSort(testList, -2, 2);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	quickSort.quickSort(testList, 2, 1);
        });
    }
}
