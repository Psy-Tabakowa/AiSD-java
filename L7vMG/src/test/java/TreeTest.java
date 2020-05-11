import Tree.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    Tree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree=new Tree<Integer>();
        tree.Insert(10);
        tree.Insert(8);
        tree.Insert(7);
        tree.Insert(9);
        tree.Insert(12);
        tree.Insert(11);
        tree.Insert(4);
        tree.Insert(5);
        tree.Insert(16);
        tree.Insert(13);
        tree.Insert(6);
        tree.Insert(10);
    }

    @Test
    void InsertTest() {}

    @Test
    void UpperTest()
    {
        assertEquals(8, tree.Upper(7));
        assertEquals(12, tree.Upper(11));
        assertEquals(10, tree.Upper(9));
        assertEquals(11, tree.Upper(10));
        assertEquals(5, tree.Upper(4));
        assertEquals(16, tree.Upper(13));
    }

    @Test
    void LowerTest()
    {
        assertEquals(7, tree.Lower(8));
        assertEquals(11, tree.Lower(12));
        assertEquals(9, tree.Lower(10));
        assertEquals(10, tree.Lower(11));
        assertEquals(4, tree.Lower(5));
        assertEquals(13, tree.Lower(16));
    }

    @Test
    void DeleteTest()
    {
        tree.Delete(8);
        tree.Delete(12);
        tree.Delete(10);
        tree.Delete(11);
        tree.Delete(5);
        tree.Delete(16);
        assertEquals(9, tree.Upper(7));
        assertEquals(13, tree.Upper(9));
        assertEquals(6, tree.Upper(4));
        assertEquals(7, tree.Upper(6));
        assertEquals(7, tree.Lower(9));
        assertEquals(6, tree.Lower(7));
        assertEquals(9, tree.Lower(13));
        assertEquals(4, tree.Lower(6));
    }

    @Test
    void ExceptionTest()
    {
        assertThrows(NullPointerException.class ,()->tree.Insert(null));
        assertThrows(NullPointerException.class ,()->tree.Upper(null));
        assertThrows(NullPointerException.class ,()->tree.Lower(null));
        assertThrows(NullPointerException.class ,()->tree.Delete(null));
        assertThrows(NoSuchElementException.class ,()->tree.Upper(16));
        assertThrows(NoSuchElementException.class ,()->tree.Upper(3));
        assertThrows(NoSuchElementException.class ,()->tree.Lower(4));
        assertThrows(NoSuchElementException.class ,()->tree.Lower(3));
        assertThrows(NoSuchElementException.class ,()->tree.Delete(3));
    }
}