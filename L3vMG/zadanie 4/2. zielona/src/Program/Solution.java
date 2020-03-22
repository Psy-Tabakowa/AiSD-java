package Program;

import java.util.NoSuchElementException;

public class Solution {

    public int solution(String s){
        MyStack<Character> stack = new MyStack<Character>();
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
            else if(s.charAt(i)=='}')
            {
                if(stack.empty())
                {
                    return 0;
                }
                char character=stack.pop();
                if(character!='{')
                {
                    return 0;
                }
            }
            else if(s.charAt(i)==']')
            {
                if(stack.empty())
                {
                    return 0;
                }
                char character=stack.pop();
                if(character!='[')
                {
                    return 0;
                }
            }
            else if(s.charAt(i)==')')
            {
                if(stack.empty())
                {
                    return 0;
                }
                char character=stack.pop();
                if(character!='(')
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
    private class MyStack<Character>
    {
        MyObject<Character> peak=null;
        MyObject<Character> tmp;

        void push(Character value)
        {

            tmp=new MyObject(value, peak);
            peak=tmp;
            tmp=null;

        }

        Character pop()
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

        private class MyObject<Character>
        {
            MyObject<Character> next;
            Character value;

            MyObject(Character value, MyObject<Character> next)
            {
                this.value=value;
                this.next=next;
            }

            public MyObject getNext() {
                return next;
            }

            public Character getValue() {
                return value;
            }
        }
    }
}

