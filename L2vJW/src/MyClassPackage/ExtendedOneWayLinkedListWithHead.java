package MyClassPackage;

import ClassFromLecture.*;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ExtendedOneWayLinkedListWithHead<T>
        extends AbstractList<T> implements Iterable<T>
{
    private OneWayLinkedListWithHead<T> innerList;

    public ExtendedOneWayLinkedListWithHead()
    {
        innerList = new OneWayLinkedListWithHead<T>();
    }


    @Override
    public Iterator<T> iterator()
    {
        return this.listIterator();
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return new ListIterator<T>()
        {
            int lastIndex = -1;

            @Override
            public void forEachRemaining(Consumer<? super T> action)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public boolean hasNext()
            {
                // Check if next index in array border

                int testIndex = lastIndex + 1;

                // If index in the next position is in the array border
                if (inBorder(testIndex))
                    return true;
                return false;
            }

            @Override
            public T next()
            {
                // If there is next element move index on it and return this element

                if (hasNext())
                {
                    lastIndex++;
                    return get(lastIndex);
                }
                else
                    throw new NoSuchElementException();
            }

            @Override
            public boolean hasPrevious()
            {
                // Check if lastIndex is in array border

                int testIndex = lastIndex;

                // If index in the prev position is in the array border
                if (inBorder(testIndex))
                    return true;
                return false;
            }

            @Override
            public T previous()
            {
                // Return element on the lastIndex and decrease it

                if (hasPrevious())
                {
                    T toReturn = get(lastIndex);
                    lastIndex--;
                    return toReturn;
                }
                else
                    throw new NoSuchElementException();
            }

            @Override
            public int nextIndex()
            {
                // Next index is lastIndex+1

                if (hasNext())
                    return lastIndex + 1;
                else
                    throw new NoSuchElementException();
            }

            @Override
            public int previousIndex()
            {
                // Previous index is just the lastIndex

                if (hasPrevious())
                    return lastIndex;
                else
                    throw new NoSuchElementException();
            }

            @Override
            public void remove()
            {
                // Remove last element if index was in border
                // Decrease lastIndex

                if (inBorder(lastIndex))
                {
                    ExtendedOneWayLinkedListWithHead.this.remove(lastIndex);
                    lastIndex--;
                }
                else
                    throw new IllegalStateException();
            }

            @Override
            public void set(T t)
            {
                // If lastIndex is in array border change this element

                if (inBorder(lastIndex))
                {
                    ExtendedOneWayLinkedListWithHead.this.set(lastIndex, t);
                }
                else
                    throw new IllegalStateException();
            }

            @Override
            public void add(T t)
            {
                // Just add new element on the next index (and increase last +1)

                ExtendedOneWayLinkedListWithHead.this.add(lastIndex+1, t);
                lastIndex++;
            }

            boolean inBorder(int index)
            {
                if (index >= 0 && index < size())
                    return true;
                return false;
            }
        };
    }




    @Override
    public boolean add(T t)
    {
        return innerList.add(t);
    }

    @Override
    public void add(int index, T element)
    {
        innerList.add(index, element);
    }

    @Override
    public void clear()
    {
        innerList.clear();
    }

    @Override
    public boolean contains(T element)
    {
        return innerList.contains(element);
    }

    @Override
    public T get(int index)
    {
        return innerList.get(index);
    }

    @Override
    public T set(int index, T element)
    {
        return innerList.set(index, element);
    }

    @Override
    public int indexOf(T element)
    {
        return innerList.indexOf(element);
    }

    @Override
    public boolean isEmpty()
    {
        return innerList.isEmpty();
    }

    @Override
    public T remove(int index)
    {
        return innerList.remove(index);
    }

    @Override
    public boolean remove(T element)
    {
        return innerList.remove(element);
    }

    @Override
    public int size()
    {
        return innerList.size();
    }
}
