import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinomialHeapTest {

    BinomialHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new BinomialHeap<Integer>();
        heap.insert(10);
        heap.insert(7);
        heap.insert(12);
        heap.insert(8);
        heap.insert(7);
        heap.insert(11);
        heap.insert(5);
        heap.insert(6);
        heap.insert(14);
        heap.insert(13);
        assertArrayEquals(new Integer[]{5, 13}, heap.arrayOfRoots());
    }

    @Test
    void insertTest() { }

    @Test
    void minimumTest(){
        assertEquals(5 ,heap.minimum());
        heap.insert(3);
        assertEquals(3 ,heap.minimum());
        heap.insert(2);
        assertEquals(2 ,heap.minimum());
    }

    @Test
    void extractMinTest(){
        heap.extractMin();
        heap.extractMin();
    }

    @Test
    void deleteTest(){
        heap.delete(7);
        assertArrayEquals(new Integer[]{5, 6}, heap.arrayOfRoots());
        heap.delete(10);
        assertArrayEquals(new Integer[]{5}, heap.arrayOfRoots());
    }

    @Test
    void decreaseKeyTest(){
        heap.decreaseKey(11, 4);
        assertArrayEquals(new Integer[]{4, 13}, heap.arrayOfRoots());
        heap.decreaseKey(10, 3);
        assertArrayEquals(new Integer[]{3, 13}, heap.arrayOfRoots());
    }

    @Test
    void unionTest(){
        BinomialHeap<Integer> anotherHeap = new BinomialHeap<Integer>();
        anotherHeap.insert(8);
        anotherHeap.insert(15);
        anotherHeap.insert(1);
        anotherHeap.insert(20);
        anotherHeap.insert(9);
        anotherHeap.insert(2);
        anotherHeap.insert(8);
        assertArrayEquals(new Integer[]{1, 2, 8}, anotherHeap.arrayOfRoots());
        heap.union(anotherHeap);
        assertArrayEquals(new Integer[]{1, 8}, heap.arrayOfRoots());
    }
}