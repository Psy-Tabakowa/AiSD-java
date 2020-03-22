package Test;

import Program.VTStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class VTStackTest {

    VTStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new VTStack<>();
    }

    @Test
    void VTStackTest()
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
            assertEquals(14, stack.peek());
        } catch (Exception e) {
            e.printStackTrace(); }
        for(int i=1; i<stack.size(); i++)
        {
            try {
                stack.down();
            } catch (Exception e) {
                e.printStackTrace(); }
            if(i==3)
            {
                try {
                    assertEquals(11, stack.peek());
                } catch (Exception e) {
                    e.printStackTrace(); }
            }
        }
        try {
            assertEquals(3, stack.peek());
        } catch (Exception e) {
            e.printStackTrace(); }
        assertThrows(Exception.class, () -> {stack.down();});
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
        assertTrue(stack.isEmpty());
        assertThrows(Exception.class, () -> {stack.peek();});
        assertEquals(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3), tab);
    }
}