package main;

import java.util.ArrayList;
import java.util.List;

public class MedianOfMedians<T extends Comparable<T>> {
	public T medianOfMedians(List<T> list) {
		return medianOfMedians(list, 0, list.size() - 1);
	}
	public T medianOfMedians(List<T> list, int left, int right) {
		if(list == null) throw new NullPointerException("Argument 'list' cannot be null");
		if(list.size() == 0) throw new IllegalArgumentException("Argument 'list' cannot be an empty list");
		if(left < 0 || right < 0) throw new IllegalArgumentException("Arguments 'left' and 'right' must a positive integer");
		if(left >= list.size() || right >= list.size()) throw new IndexOutOfBoundsException("Arguments 'left' and 'right' must be in range of 'list'");
		if(left > right) throw new IllegalArgumentException("Argument 'left' must be less then argument 'right'");
		
		ArrayList<T> medians = new ArrayList<T>();
		for(int i=left+4; i<=right; i+= 5)
			medians.add(kthSmallest(3, list, i-4, i));
		if((right - left + 1) % 5 != 0)
			medians.add(kthSmallest(((right - left + 1)%5 + 1)/2, list, right - ((right - left + 1)%5) + 1 ,right));
		//for(int i=0; i<medians.size(); i++) System.out.print(medians.get(i));
		return kthSmallest((medians.size()+1)/2, medians);
	}
	
	private T kthSmallest(int k, List<T> list) {
		return kthSmallest(k, list, 0, list.size()-1);
	}
	private T kthSmallest(int k, List<T> list, int left, int right) {
        if (list == null)
            throw new NullPointerException("Argument 'list' cannot be null");
        
        if (list.size() == 0)
            throw new IllegalArgumentException("Argument 'list' cannot be an empty list");
        
        if(left > right)
        	throw new IllegalArgumentException("Argument 'left' must be less than argument 'right'");
        
        if(k <= 0)
        	throw new IllegalArgumentException("Argument 'k' must be a positive number");
        
        if (k <= 0 || k > (right - left + 1))
            throw new IndexOutOfBoundsException("Argument 'k' cannot be greater than size of argument 'list'");
        
        if(list.size() == 1) return list.get(0);
        
        int rnd = (int)Math.floor(Math.random() * (right - left + 1));
        T v = list.get(rnd);
        
        ArrayList<T> sl = new ArrayList<T>();
        ArrayList<T> sv = new ArrayList<T>();
        ArrayList<T> sr = new ArrayList<T>();
        
        T t;
        int c;
        for(int i=left; i <= right; i++) {
        	t = list.get(i);
        	c = v.compareTo(t);
        	if(c == 1) sl.add(t);
        	else if (c == 0) sv.add(t);
        	else if (c == -1) sr.add(t);
        }
        
        if(k <= sl.size()) return kthSmallest(k, sl);
        else if(k <= sv.size() + sl.size()) return v;
        else return kthSmallest(k - sl.size() - sv.size(), sr);
	}
}
