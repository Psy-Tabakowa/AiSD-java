package com.company;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ExtendedOneWayLinkedListWithHead
        <T> extends AbstractList<T> implements Iterable<T> {
    private OneWayLinkedListWithHead<T> innerList = new
            OneWayLinkedListWithHead<>();
    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            Iterator<T> iterator = innerList.iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                if(this.hasNext())
                {
                    cursor++;
                    return iterator.next();
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                if(cursor>=0)
                {
                    return true;
                }
                return false;
            }

            @Override
            public T previous() {
                if(this.hasPrevious())
                {
                    int i=0;
                    for(Iterator<T> ite = innerList.iterator(); ite.hasNext() ; ite.next())
                    {
                        if(i==cursor)
                        {
                            iterator=ite;
                            break;
                        }
                        i++;
                    }
                    T value = iterator.next();
                    i=0;
                    for(Iterator<T> ite = innerList.iterator(); ite.hasNext() ; ite.next())
                    {
                        if(i==cursor)
                        {
                            iterator=ite;
                            break;
                        }
                        i++;
                    }
                    cursor--;
                    return value;
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public int nextIndex() {
                if(hasNext())
                {
                    return cursor+1;
                }
                throw new IndexOutOfBoundsException();
            }

            @Override
            public int previousIndex() {

                if(hasPrevious())
                {
                    return cursor;
                }
                throw new IndexOutOfBoundsException();
            }

            @Override
            public void remove() {
                if(cursor>=0) {
                    innerList.remove(cursor);
                    cursor--;
                }
                else
                {
                    throw new IllegalStateException();
                }
            }

            @Override
            public void set(T t) {
                if(cursor>=0) {
                    innerList.set(cursor, t);
                }
                else
                {
                    throw new IllegalStateException();
                }
            }

            @Override
            public void add(T t) {
                cursor++;
                innerList.add(cursor, t);

            }

            int cursor=-1;
            Boolean hasNext;
            Boolean hasPrevious;
            boolean canBeRemovedOrSet;
            T current;
            @Override
            public void forEachRemaining(Consumer<? super T> action){
                throw new UnsupportedOperationException();
            }
        };
    }
    @Override
    public boolean add(T value) {
        return innerList.add(value);
    }

    @Override
    public T get(int i) {
        return innerList.get(i);
    }

    @Override
    public Iterator<T> iterator() {
        return this.listIterator();
    }

    @Override
    public int size() {
        return innerList.size();
    }
    public boolean isEmpty() { return innerList.isEmpty(); }
    public void clear() { innerList.clear(); }
    public void add(int index, T data) { innerList.add(index, data); }
    public int indexOf(Object data) { return innerList.indexOf(data); }
    public boolean contains(Object data) { return innerList.contains(data);}
    public T set ( int index, T data){ return innerList.set(index, data); }
    public T remove(int index) { return innerList.remove(index); }
    public boolean remove(Object value) { return innerList.remove(value); }

};