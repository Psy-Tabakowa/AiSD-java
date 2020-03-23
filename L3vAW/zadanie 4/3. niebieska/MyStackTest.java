package zadanie2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackTest {
	private MyStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<Integer>(4);
    }

	@Test
    void isEmptyTest() {
		assertEquals(true,stack.isEmpty());
		try{
			stack.push(1);
			stack.push(2);
			stack.push(4);
			stack.push(8);
			assertEquals(false,stack.isEmpty());
			stack.push(16);
			assertEquals(false,stack.isEmpty());
			stack.pop();
			stack.pop();
			stack.pop();
			stack.pop();
		} catch(Exception e) { }
		
		assertEquals(true,stack.isEmpty());
    }
	
	@Test
    void isFullTest() {
		assertEquals(false,stack.isFull());
		try{
			stack.push(1);
			stack.push(2);
			stack.push(4);
			assertEquals(false,stack.isFull());
			stack.push(8);
			assertEquals(true,stack.isFull());
			stack.push(16);
			assertEquals(true,stack.isFull());
			stack.pop();
			assertEquals(false,stack.isFull());
			stack.pop();
			stack.pop();
			stack.pop();
		} catch(Exception e) { }
		
		assertEquals(false,stack.isFull());
	}
	
	@Test
    void popTest() {
		assertThrows(EmptyStackException.class, stack::pop);
		try{
			stack.push(1);
			stack.push(2);
			stack.push(4);
			stack.push(8);
			stack.push(16);
			assertEquals(16, stack.pop());
			assertEquals(8, stack.pop());
			assertEquals(4, stack.pop());
			assertEquals(2, stack.pop());
			assertThrows(EmptyStackException.class, stack::pop);
		} catch(Exception e) { }
	}
	
	@Test
    void pushTest() {
		try{
			stack.push(1);
			stack.push(2);
			stack.push(4);
			stack.push(8);
			assertArrayEquals(new Integer[] {1,2,4,8}, stack.array());
			stack.push(16);
			assertArrayEquals(new Integer[] {16,2,4,8}, stack.array());
			stack.pop();
			stack.pop();
			stack.pop();
			stack.push(32);
			assertArrayEquals(new Integer[] {16,2,32,8}, stack.array());
			stack.push(64);
			assertArrayEquals(new Integer[] {16,2,32,64}, stack.array());
		} catch(Exception e) { }
	}
	
	@Test
	void sizeTest() {
		assertEquals(0,stack.size());
		try{
			stack.push(1);
			stack.push(2);
			assertEquals(2,stack.size());
			stack.push(4);
			stack.push(8);
			assertEquals(4,stack.size());
			stack.push(16);
			assertEquals(4,stack.size());
			stack.pop();
			stack.pop();
			assertEquals(2,stack.size());
			stack.pop();
			stack.pop();
		} catch(Exception e) { }
		assertEquals(0,stack.size());
	}
	
	@Test
	void topTest() {
		assertThrows(EmptyStackException.class, stack::top);
		try{
			stack.push(1);
			stack.push(2);
			assertEquals(2,stack.top());
			stack.push(4);
			stack.push(8);
			assertEquals(8,stack.top());
			stack.push(16);
			assertEquals(16,stack.top());
			stack.pop();
			stack.pop();
			assertEquals(4,stack.top());
			stack.pop();
			stack.pop();
		} catch(Exception e) { }
		assertThrows(EmptyStackException.class, stack::top);
	}
}