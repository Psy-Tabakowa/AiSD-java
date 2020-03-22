package Test;

import static org.junit.jupiter.api.Assertions.*;

import Program.MyStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class MyStackTest {

    MyStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<>();
    }

    @Test
    void myStackTest()
    {
        assertTrue(stack.isEmpty());
        assertFalse(stack.isFull());
        assertThrows(Exception.class, () -> {stack.pop();});
        for(int i=0; i<10; i++)
        {
            try {
                stack.push(i);
            } catch (Exception e) {
                e.printStackTrace(); }
            if(i==4)
            {
                assertFalse(stack.isEmpty());
                assertFalse(stack.isFull());
            }
        }
        assertFalse(stack.isEmpty());
        assertTrue(stack.isFull());
        try {
            stack.pop();
            stack.pop();
        } catch (Exception e) {
            e.printStackTrace(); }
        for(int i=10; i<15; i++)
        {
            try {
                stack.push(i);
            } catch (Exception e) {
                e.printStackTrace(); }
        }
        try {
            assertEquals(14, stack.top());
        } catch (Exception e) {
            e.printStackTrace(); }
        ArrayList tab=new ArrayList();
        while(!stack.isEmpty())
        {
            try {
                tab.add(stack.pop());
            } catch (Exception e) {
                e.printStackTrace(); }
        }
        assertEquals(Arrays.asList(14, 13, 12, 11, 10, 7, 6, 5, 4, 3), tab);
        assertEquals(10, stack.getCapacity());
        stack.setCapacity(20);
        for(int i=0; i<15; i++)
        {

            try {
                stack.push(3);
            } catch (Exception e) {
                e.printStackTrace(); }
            if(i==12)
            {
                assertEquals(13, stack.size());
            }
        }
        tab=new ArrayList();
        while(!stack.isEmpty())
        {
            try {
                tab.add(stack.pop());
            } catch (Exception e) {
                e.printStackTrace(); }
        }
        assertFalse(stack.isFull());
        assertEquals(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3), tab);
    }
}