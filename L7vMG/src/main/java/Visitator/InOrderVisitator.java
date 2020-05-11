package Visitator;

import Tree.Tree;

import java.util.ArrayList;

public class InOrderVisitator<T extends Comparable> implements Visitator {

    ArrayList<T> elementsInOrder=new ArrayList<T>();

    public void VisitNode(Tree.Node node) {
        if(node.getLeft()!=null)
            VisitNode(node.getLeft());
        elementsInOrder.add((T) node.getValue());
        if(node.getRight()!=null)
            VisitNode(node.getRight());
    }

    public ArrayList GetElementsInOrder() {
        return elementsInOrder;
    }
}
