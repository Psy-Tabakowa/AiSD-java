package main;

import java.util.ArrayList;

public class BucketSort<T extends Number & Comparable<T>>{
	private void insertSort(ArrayList<T> list) {
		if(list.size() < 2) return;
		for(int i=0; i<list.size(); i++) {
			int j = i;
			while(j > 0 && list.get(j-1).compareTo(list.get(j)) > 0) {
				T temp = list.get(j);
				list.set(j,  list.get(j));
				list.set(--j,  temp);
			}
		}
	}
	
	public void bucketSort(ArrayList<T> list) {
		if(list == null) throw new NullPointerException("Argument 'list' cannot be null");
		
		if(list.size() < 2) return;
		
		ArrayList<T> [] buckets = (ArrayList<T> [])new ArrayList [list.size()];
		
		for(int i=0; i<buckets.length; i++) buckets[i] = new ArrayList<T>();
		
		T smallest = list.get(0);
		for(int i=1; i<list.size(); i++) {
			if(list.get(i).compareTo(smallest) < 0)
				smallest = list.get(i);
		}
		
		T biggest = list.get(0);
		for(int i=1; i<list.size(); i++) {
			if(list.get(i).compareTo(biggest) > 0)
				biggest = list.get(i);
		}
		
		for(T item : list) {
			int idx = (int)Math.round((item.doubleValue() - smallest.doubleValue()) / 
					(biggest.doubleValue() - smallest.doubleValue()) * (list.size()));
			if(idx >= list.size()) idx = list.size()-1;
			buckets[idx].add(item);
		}
		
		/*
		for(int i=0; i<buckets.length; i++) {
			System.out.println("b" + i);
			for(int j=0; j<buckets[i].size(); j++) {
				System.out.print(" " + buckets[i].get(j));
			}
			System.out.println();
		}
		*/
		
		for(int i=0; i<buckets.length; i++) insertSort(buckets[i]);
		
		int idx = 0;
		for(int i=0; i<buckets.length; i++) {
			for(int j=0; j<buckets[i].size(); j++) {
				list.set(idx++, buckets[i].get(j));
			}
		}
	}
}
