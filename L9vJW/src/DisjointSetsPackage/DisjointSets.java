package DisjointSetsPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DisjointSets <T extends Comparable>
{
    private List< List<T> > disjointSets;


    public DisjointSets()
    {
        disjointSets = new ArrayList<>();
    }


    public void makeSet(T element)
    {
        if (element == null)
            throw new NullPointerException("Element to make set cannot be null");

        // check if this element was already added
        if (getSetByItsElement(element) != null)
            return;

        List<T> tempList = new LinkedList<>();
        tempList.add(element);
        disjointSets.add(tempList);
    }


    // returns representative element of set that contain element in the argument
    public T findSet(T element)
    {
        if (element == null)
            throw new NullPointerException("Element to find cannot be null");

        return getSetByItsElement(element).get(0);
    }


    public void union(T first, T second)
    {
        if (first == second)
            throw new IllegalArgumentException("Elements to union have to be different");

        if (first == null || second == null)
            throw new NullPointerException("Both elements cannot be null");

        List<T> list1 = getSetByItsElement(first);
        List<T> list2 = getSetByItsElement(second);

        // if elements already are in the same set, do nothing
        if (list1.get(0).compareTo(list2.get(0)) == 0) // check the set representatives
            return;

        // merge, put all elements from second list to the first one
        while (list2.size() > 0)
        {
            list1.add(list2.get(0));
            list2.remove(0);
        }

        // remove list 2 from set list, its all elements are now in list1
        disjointSets.remove(list2);
    }


    public List< List<T> > getDisjointSets()
    {
        return disjointSets;
    }



    private List<T> getSetByItsElement(T element)
    {
        for (int i=0; i<disjointSets.size(); i++)
        {
            List<T> curList = disjointSets.get(i);

            // check if this list contain given element
            for (int j=0; j<curList.size(); j++)
                if (element.compareTo(curList.get(j)) == 0)
                    return curList;
        }
        return null;
    }

}
