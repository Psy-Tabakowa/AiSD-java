import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class Zadanie_1Test {

    ArrayList<Double> list;
    Zadanie_1 algorithm;
    @BeforeEach
    void setUp() {
        list=new ArrayList<Double>();
        algorithm=new Zadanie_1();
    }

    @Test
    void testEvenLength()
    {
        double[] tab = {1, 5, 3, 8, 4, 10, 7, 6, 2, 9};
        for (int i=0; i<tab.length; i++) {
            list.add(tab[i]);
        }
        assertEquals(3, algorithm.Element_K(list, 3));
        assertEquals(5, algorithm.Element_K(list, 5));
        assertEquals(9, algorithm.Element_K(list, 9));
        assertEquals(1, algorithm.Element_K(list, 1));
        assertEquals(10, algorithm.Element_K(list, 10));
    }

    @Test
    void testOddLength()
    {
        double[] tab = {1, 3, 4, 10, 7, 16, 17, 20, 6, 9, 15};
        for (int i=0; i<tab.length; i++) {
            list.add(tab[i]);
        }
        assertEquals(6, algorithm.Element_K(list, 4));
        assertEquals(9, algorithm.Element_K(list, 6));
        assertEquals(17, algorithm.Element_K(list, 10));
    }

    @Test
    void testDifferentElements()
    {
        double[] tab = {-1, 0, 4.5, 10.1325, 10.1, -16, -17, 20, 656, 96, -15.5653, 1110000, 99999.3};
        for (int i=0; i<tab.length; i++) {
            list.add(tab[i]);
        }
        assertEquals(-15.5653, algorithm.Element_K(list, 3));
        assertEquals(4.5, algorithm.Element_K(list, 6));
        assertEquals(0, algorithm.Element_K(list, 5));
        assertEquals(99999.3, algorithm.Element_K(list, 12));
        assertEquals(10.1, algorithm.Element_K(list, 7));
        assertEquals(10.1325, algorithm.Element_K(list, 8));
    }

    @Test
    void testExceptions()
    {
        assertThrows(NullPointerException.class, ()-> algorithm.Element_K(null, 134));
        assertThrows(IllegalArgumentException.class, ()-> algorithm.Element_K(list, 13));
        double[] tab = {1, 3, 4, 10, 7, 16, 17, 20, 6, 9, 15};
        for (int i=0; i<tab.length; i++) {
            list.add(tab[i]);
        }
        assertThrows(IndexOutOfBoundsException.class, ()-> algorithm.Element_K(list, 27));
        assertThrows(IndexOutOfBoundsException.class, ()-> algorithm.Element_K(list, 0));
        assertThrows(IndexOutOfBoundsException.class, ()-> algorithm.Element_K(list, 12));
        assertThrows(IndexOutOfBoundsException.class, ()-> algorithm.Element_K(list, -3));
    }

    @Test
    void testManyElements()
    {
        for (int i=1; i<=210; i++)
            list.add((double)i%7);

        assertEquals(0, algorithm.Element_K(list, 1));
        assertEquals(6, algorithm.Element_K(list, list.size()));
        assertEquals(3, algorithm.Element_K(list, 105));
        assertEquals(4, algorithm.Element_K(list, 121));
    }

    /*@Test
    void testThrow1()
    {
        algorithm.Element_K(null, 134);
    }

    @Test
    void testThrow2()
    {
        algorithm.Element_K(list, 13);
    }
    @Test
    void testThrow3()
    {
        list.add(1.0);
        algorithm.Element_K(list, 0);
    }*/
}