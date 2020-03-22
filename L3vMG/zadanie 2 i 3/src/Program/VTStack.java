package Program;


public class VTStack<T> extends MyStack<T>{
    MyObject<T> cursor=head;

    @Override
    public T pop() throws EmptyStackException {
        if(head!=null)
        {
            MyObject<T> object = head;
            head=head.getNext();
            size--;
            toTop();
            return object.getValue();
        }
        throw new EmptyStackException();
    }

    @Override
    public void push(T elem) throws FullStackException {
        super.push(elem);
        toTop();
    }

    @Override
    public void setCapacity(int capacity) {
        toTop();
        super.setCapacity(capacity);
    }

    public void toTop()
    {
        cursor=head;
    }

    public T peek() throws EmptyStackException
    {
        if(cursor!=null)
        {
            return cursor.getValue();
        }
        throw new EmptyStackException();
    }

    public void down() throws BottomOfStackException
    {
        if(cursor.getNext()!=null)
        {
            cursor=cursor.getNext();
        }
        else
        {
            throw new BottomOfStackException();
        }
    }
}

class BottomOfStackException extends Exception{
}
