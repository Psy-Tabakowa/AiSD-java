import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinomialHeap <T extends Comparable<? super T>> {
    public class Node {
        T value;
        int size=1;
        Node upper;
        ArrayList<Node> lower = new ArrayList<Node>();
        public Node(T value){
            this.value=value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void addLower(Node node) {
            lower.add(node);
            node.upper=this;
            size+=node.size;
        }

        public void swapWithUpper(){
            if(upper!=null){
                T tmp=value;
                value=upper.value;
                upper.value=tmp;
            }
            else
                throw new NullPointerException();
        }
    }

    private ArrayList<Node> listOfRoots=new ArrayList<Node>();

    public void insert(T value) {
        Preconditions.checkNotNull(value);
        BinomialHeap<T> heap = new BinomialHeap<T>();
        heap.listOfRoots.add(new Node(value));
        union(heap);
    }

    private Node minNode(){
        if(listOfRoots.size()==0)
            throw new NullPointerException();
        Node min=listOfRoots.get(0);
        for(Node node: listOfRoots)
            if(node.value.compareTo(min.value)<0)
                min=node;
        return min;
    }

    public T minimum() {
        return minNode().getValue();
    }

    public T extractMin() {
        Node min=minNode();
        removeRoot(min);
        return min.getValue();
    }

    private Node search(T value, ArrayList<Node> nodeList){
        for(Node node: nodeList){
            if(value.compareTo(node.getValue())==0)
                return node;
            else if(value.compareTo(node.getValue())>0) {
                try {
                    return search(value, node.lower);
                } catch (NoSuchElementException e) {}
            }
        }
        throw new NoSuchElementException();
    }

    public void delete(T value){
        Preconditions.checkNotNull(value);
        Node nodeToRemove=search(value, listOfRoots);
        while(nodeToRemove.upper!=null) {
            nodeToRemove.swapWithUpper();
            nodeToRemove=nodeToRemove.upper;
        }
        removeRoot(nodeToRemove);
    }

    private void removeRoot(Node nodeToRemove){
        for(Node node: nodeToRemove.lower)
            node.upper=null;
        listOfRoots.remove(nodeToRemove);
        BinomialHeap<T> heap = new BinomialHeap<T>();
        heap.listOfRoots=nodeToRemove.lower;
        union(heap);
    }

    public void decreaseKey(T value, T newValue){
        Preconditions.checkNotNull(value);
        Preconditions.checkNotNull(newValue);
        Preconditions.checkArgument(value.compareTo(newValue)>0, "New value must be smaller than old one");
        Node key=search(value, listOfRoots);
        key.setValue(newValue);
        while(true){
            if(key.upper!=null) {
                if (key.value.compareTo(key.upper.value) < 0) {
                    key.swapWithUpper();
                    key = key.upper;
                }
                else {
                    break;
                }
            }
            else {
                break;
            }
        }
    }

    public void union(BinomialHeap<T> heap){
        Preconditions.checkNotNull(heap);
        for(int i=heap.listOfRoots.size()-1; i>=0; i--) {
            addToRoots(heap.listOfRoots.get(i));
            updateRoots();
        }
    }

    private void addToRoots(Node node){
        for(int i=0; i<=listOfRoots.size(); i++){
            if(i==listOfRoots.size()) {
                listOfRoots.add(node);
                break;
            }
            else if(listOfRoots.get(i).size<node.size){
                listOfRoots.add(i, node);
                break;
            }
        }
    }

    private void updateRoots(){
        Node previousNode=null, nodeToRemove=null;
        for(Node node: listOfRoots){
            if(previousNode!=null)
                if(previousNode.size==node.size){
                    if(previousNode.getValue().compareTo(node.getValue())<=0){
                        previousNode.addLower(node);
                        nodeToRemove=node;
                        break;
                    }
                    else{
                        node.addLower(previousNode);
                        nodeToRemove=previousNode;
                        break;
                    }
                }
            previousNode=node;
        }
        if(nodeToRemove!=null){
            listOfRoots.remove(nodeToRemove);
            updateRoots();
        }
    }

    public T[] arrayOfRoots() {
        T[] array=(T[])new Comparable[listOfRoots.size()];
        for(int i=0; i<listOfRoots.size(); i++)
            array[i]=listOfRoots.get(i).getValue();
        return array;
    }
}
