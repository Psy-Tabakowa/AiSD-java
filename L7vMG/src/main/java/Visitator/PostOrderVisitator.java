package Visitator;

import Tree.Tree;

import java.util.ArrayList;

public class PostOrderVisitator <T extends Comparable> implements Visitator {

    ArrayList<T> elementsInOrder=new ArrayList<T>();

    public void VisitNode(Tree.Node node) {
        if(node.getLeft()!=null)
            VisitNode(node.getLeft());
        if(node.getRight()!=null)
            VisitNode(node.getRight());
        elementsInOrder.add((T) node.getValue());
    }

    public ArrayList GetElementsInOrder() {
        return elementsInOrder;
    }
}
