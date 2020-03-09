package com.company;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyList<T> implements Iterable<T> {

    T[] array;
    int minCapacity=10;

    public MyList()
    {
        array=(T[])new Object[minCapacity];
    }
    public MyList(T[] array)
    {
        int j=0;
        T[] tmp=(T[])new Object[array.length];
        for(int i = 0; i<array.length; i++)
        {
            if(array[i]!=null)
            {
                tmp[j]=array[i];
                j++;
            }
        }
        this.ensureCapacity(j);
        this.array=(T[])new Object[minCapacity];
        for(int i = 0; i<=j; i++)
        {
            this.add(tmp[i]);
        }
    }
    public MyList(int minCapacity)
    {
        this.ensureCapacity(minCapacity);
        array=(T[])new Object[minCapacity];
    }


    public void add(T element)
    {
        if(element!=null)
        {
            this.ensureCapacity(this.size()+1);
            T[] tmp=(T[])new Object[minCapacity];
            int i=0;
            for(Iterator<T> it = iterator();it.hasNext(); tmp[i++]=it.next()){}
            tmp[this.size()]=element;
            array=tmp;
        }

    }
    public void add(int index, T element)
    {
        if(index>this.size())
        {
            throw new IndexOutOfBoundsException();
        }
        if(element!=null)
        {
            if(index==this.size())
            {
                this.add(element);
            }
            else
            {
                this.ensureCapacity(minCapacity+1);
                T[] tmp=(T[])new Object[minCapacity];
                int i=0;
                for(Iterator<T> it = iterator();it.hasNext(); tmp[i++]=it.next())
                {
                    if(index==i)
                    {
                        tmp[i++]=element;
                    }
                }
                array=tmp;
            }
        }
    }
    public void clear()
    {
        array= (T[])new Object[minCapacity];
    }
    public boolean contains(T element)
    {
        for(Iterator<T> it = iterator();it.hasNext();)
        {
            if(it.next()==element)
            {
                return true;
            }
        }
        return false;
    }
    public void ensureCapacity(int minCapacity)
    {
        if(minCapacity>this.minCapacity)
        {
            this.minCapacity=minCapacity;
        }
    }
    public T get(int index)
    {
        return array[index];
    }
    public int indexOf(T element)
    {
        int i=0;
        for(Iterator<T> it = iterator();it.hasNext();i++)
        {
            if(it.next().equals(element))
            {
                return (i);
            }
        }
        throw new NoSuchElementException();
    }
    public void set(int index, T element)
    {
        if(index<this.size())
        {
            array[index]=element;
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    public void remove(int index)
    {
        if(index<this.size())
        {
            int i=0;
            for(Iterator<T> it = iterator();it.hasNext(); it.next())
            {
                if(i==index)
                {
                    it.remove();
                    break;
                }
                i++;
            }
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    public int size()
    {
        int i=0;
        for(Iterator<T> it = iterator(); it.hasNext(); it.next())
        {
            i++;
        }
        return i;
    }

    @Override
    public Iterator iterator()   {
        return  new Iterator<T>()
        {
            int current=0;

            @Override
            public boolean hasNext() {
                if(array[current]!=null && current<array.length) {
                    return true;
                }
                else {
                    return false;
                }
            }

            @Override
            public T next() throws NoSuchElementException {
                if(this.hasNext()) {
                    return array[current++];
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if(this.hasNext()) {
                    T[] tmp=(T[])new Object[array.length-1];
                    for(int i = 0; i<current; i++)
                    {
                        tmp[i]=array[i];
                    }
                    for(int i = current; i<array.length-2; i++)
                    {
                        tmp[i]=array[i+1];
                    }
                    array=tmp;
                }
                else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
