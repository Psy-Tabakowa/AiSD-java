package Program;

public class MyStack<T> implements IStack<T>{

    int capacity=10, size;
    MyObject<T> head=null;
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(head!=null)
        {
            MyObject<T> object = head;
            head=head.getNext();
            size--;
            return object.getValue();
        }
        throw new EmptyStackException();
    }

    @Override
    public void push(T elem) throws FullStackException {
        head=new MyObject<T>(elem, head);
        size++;
        refreshCapacity();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T top() throws EmptyStackException {
        if(head!=null)
        {
            return head.getValue();
        }
        throw new EmptyStackException();
    }

    public int getCapacity()
    {
        return capacity;
    }
    public void setCapacity(int capacity)
    {
        if(capacity>=0)
        {
            this.capacity=capacity;
            refreshCapacity();
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }

    }
    private void refreshCapacity()
    {
        if(size>capacity && capacity!=0)
        {
            size=capacity;
            MyObject<T> object=head;
            for(int i=1; i<capacity; i++)
            {
                object=object.getNext();
            }
            object.setNext(null);
        }
        else if(capacity==0)
        {
            size=capacity;
            head=null;
        }
    }

    class MyObject<T>
    {
        MyObject<T> next;
        T value;

        MyObject(T value, MyObject<T> next)
        {
            this.value=value;
            this.next=next;
        }

        public MyObject getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void setNext(MyObject<T> next) {
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}

class EmptyStackException extends Exception{
}

class FullStackException extends Exception{
}

interface IStack<T>{
    boolean isEmpty();
    boolean isFull();
    T pop() throws EmptyStackException;
    void push(T elem) throws FullStackException;
    int size();
    T top() throws EmptyStackException;
}