package Visitator;

import java.util.ArrayList;
import Tree.Tree;

public interface Visitator<T extends Comparable> {
    void VisitNode(Tree.Node node);
    ArrayList<T> GetElementsInOrder();
}
