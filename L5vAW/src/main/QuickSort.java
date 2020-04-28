package main;

import java.util.List;

public class QuickSort<T extends Comparable<T>> {
	public void quickSort(List<T> list) {
		quickSort(list, 0, list.size() - 1);
	}
	public void quickSort(List<T> list, int left, int right) {
		if(list == null) throw new NullPointerException("Argument 'list' cannot be null");
		if(left < 0 || right < 0) throw new IllegalArgumentException("Arguments 'left' and 'right' must a positive integer");
		if(left >= list.size() || right >= list.size()) throw new IndexOutOfBoundsException("Arguments 'left' and 'right' must be in range of 'list'");
		if(left > right) throw new IllegalArgumentException("Argument 'left' must be less then argument 'right'");
		
		if(right == left || list.size() < 2) return;
	
		MedianOfMedians<T> medianOfMedians = new MedianOfMedians<T>();
		T pivot = medianOfMedians.medianOfMedians(list, left, right);
		
		int leftIdx = left;
		int rightIdx = right;
		
		do {
			while(list.get(leftIdx).compareTo(pivot) < 0 && leftIdx+1 <= right) leftIdx++;
			
			while(list.get(rightIdx).compareTo(pivot) > 0 && rightIdx-1 >= left) rightIdx--;
			
			if(leftIdx <= rightIdx) {
				T temp = list.get(leftIdx);
				list.set(leftIdx, list.get(rightIdx));
				list.set(rightIdx, temp);
				leftIdx++;
				rightIdx--;
			}
			
		} while( leftIdx <= rightIdx);
		
		if(rightIdx > left) quickSort(list, left, rightIdx);
		if(leftIdx < right) quickSort(list, leftIdx, right);
	}
}
