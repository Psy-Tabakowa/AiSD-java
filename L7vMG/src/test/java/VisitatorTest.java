import Tree.Tree;
import Visitator.Visitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VisitatorTest {

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
    void InOrderVisitatorTest() {
        Visitator<Integer> visitator=new InOrderVisitator<Integer>();
        tree.root.Accept(visitator);
        ArrayList<Integer> list=visitator.GetElementsInOrder();
        assertArrayEquals(new Integer[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16}, list.toArray());
    }

    @Test
    void PreOrderVisitatorTest() {
        Visitator<Integer> visitator=new PreOrderVisitator<Integer>();
        tree.root.Accept(visitator);
        ArrayList<Integer> list=visitator.GetElementsInOrder();
        assertArrayEquals(new Integer[]{10, 7, 5, 4, 6, 8, 9, 12, 11, 16, 13}, list.toArray());
    }

    @Test
    void PostOrderVisitatorTest() {
        Visitator<Integer> visitator=new PostOrderVisitator<Integer>();
        tree.root.Accept(visitator);
        ArrayList<Integer> list=visitator.GetElementsInOrder();
        assertArrayEquals(new Integer[]{4, 6, 5, 9, 8, 7, 11, 13, 16, 12, 10}, list.toArray());
    }
}