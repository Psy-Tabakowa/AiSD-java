package Program;
//Zadanie: Brackets
import java.util.NoSuchElementException;

public class Solution {

    MyStack<Character> stack;

    public int solution(String s){
        stack = new MyStack<>();
        if(s.length()>200000)
        {
            return 0;
        }
        for(int i=0; i<s.length(); i++)
        {
            if(s.charAt(i)=='{' || s.charAt(i)=='[' || s.charAt(i)=='(')
            {
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i)=='}' || s.charAt(i)==']' || s.charAt(i)==')')
            {
                try
                {
                    if((stack.peek()!='{' && s.charAt(i)=='}') || (stack.peek()!='[' && s.charAt(i)==']') || (stack.peek()!='(' && s.charAt(i)==')'))
                    {
                        return 0;
                    }
                    stack.pop();
                }catch(NoSuchElementException e)
                {
                    return 0;
                }
            }
            else
            {
                return 0;
            }
        }
        if(stack.empty())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    private class MyStack<T>
    {
        MyObject<T> peak=null;
        MyObject<T> tmp;

        void push(T value)
        {

            tmp=new MyObject(value, peak);
            peak=tmp;
            tmp=null;

        }

        T pop() throws NoSuchElementException
        {
            if(!this.empty())
            {
                tmp=peak;
                peak=peak.getNext();
                return tmp.getValue();
            }
            throw new NoSuchElementException();
        }

        boolean empty()
        {
            if(peak==null)
            {
                return true;
            }
            return false;
        }

        T peek() throws NoSuchElementException
        {
            if(!this.empty())
            {
                return peak.getValue();
            }
            throw new NoSuchElementException();
        }

        private class MyObject<T>
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
        }
    }
}