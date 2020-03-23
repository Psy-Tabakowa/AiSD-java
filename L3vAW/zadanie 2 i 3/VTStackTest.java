package zadanie2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VTStackTest {
    private VTStack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new VTStack<String>(3);
        try {
        	stack.push("Ala");
        	stack.push("ma");
        	stack.push("kota");
        } catch(Exception e) { }
    }

    @Test
    void peekTest() {
    	try {
        	stack.down();
        	stack.down();
            assertEquals("Ala", stack.peek());
            stack.toTop();
            stack.down();
            assertEquals("ma", stack.peek());
            stack.toTop();
            assertEquals("kota", stack.peek());
    	} catch(Exception e) { }        
    }
	
	@Test
	void downTest() {
		assertEquals(2,stack.cursor);
		try {
			stack.down();
			assertEquals(1,stack.cursor);
			stack.down();
			assertEquals(0,stack.cursor);
			assertThrows(BottomOfStackException.class, stack::down);
		} catch(Exception e) { }
	}
	
	@Test
	void toTopTest() {
		try {
			VTStack<Integer> stack2 = new VTStack<Integer>(100);
			assertEquals(99,stack2.cursor);
			for(int i=0; i<100; i++) {
				stack2.push(i*2 + 1);
				assertEquals(i,stack2.cursor);
			}
			
			Random random = new Random();
			int randomNumber;
			for(int i=0; i<20; i++) {
				randomNumber = random.nextInt()%100;
				for(int j=0; j<randomNumber; j++) stack2.down();
				stack2.toTop();
				assertEquals(99, stack2.cursor);
			}
			
		} catch(Exception e) { }
	}
}