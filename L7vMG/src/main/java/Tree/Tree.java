package Tree;

import Visitator.Visitator;
import com.google.common.base.Preconditions;

import java.util.NoSuchElementException;

public class Tree<T extends Comparable> {

    public class Node{
        private T value;
        private Node up, left=null, right=null;
        private int balance=0, height=1;
        Node(T value, Node up) {
            this.value=value;
            this.up=up;
        }

        public int getHeight() { return height; }

        public void setHeight(int height) { this.height = height; }

        public T getValue() { return value; }

        public Node getUp() { return up; }

        public void setUp(Node up) { this.up = up; }

        public Node getLeft() { return left; }

        public void setLeft(Node left) { this.left = left; }

        public Node getRight() { return right; }

        public void setRight(Node right) { this.right = right; }

        public int getBalance() { return balance; }

        public void setBalance(int balance) { this.balance = balance; }

        public void Accept(Visitator visitator)
        {
            visitator.VisitNode(this);
        }
    }

    Node root=null;
    public void Insert(T value) {
        Node current = Presearch(value);
        if(current==null) {
            root = new Node(value, null);
        }
        else{
            int comprasion =value.compareTo(current.getValue());
            if(comprasion<0)
                current.setLeft(new Node(value, current));
            else if(comprasion>0)
                current.setRight(new Node(value, current));
            CheckBalance(current);
        }
    }

    public void Delete(T value) {
        Node current=Search(value), removed=current;
        if(removed.getLeft()!=null) {
            current=current.getLeft();
            if(current.getRight()!=null) {
                while(current.getRight()!=null)
                    current=current.getRight();
                current.getUp().setRight(current.getLeft());
            }
            else
                current.getUp().setLeft(current.getLeft());
            if(current.getLeft()!=null)
                current.getLeft().setUp(current.getUp());
            Node nodeToStart;
            if(current.getUp()!=removed)
                nodeToStart=current.getUp();
            else
                nodeToStart=current;
            current.setUp(removed.getUp());
            if(removed.getUp()!=null) {
                if(removed.getUp().getLeft()==removed)
                    removed.getUp().setLeft(current);
                else
                    removed.getUp().setRight(current);
            }

            current.setLeft(removed.getLeft());
            if(removed.getLeft()!=null)
                removed.getLeft().setUp(current);
            current.setRight(removed.getRight());
            if(removed.getRight()!=null)
                removed.getRight().setUp(current);
            CheckBalance(nodeToStart);
        }
        else {
            if(removed.getUp()!=null) {
                if (removed.getUp().getLeft() == removed) {
                    removed.getUp().setLeft(removed.getRight());
                    if(removed.getRight()!=null)
                        removed.getRight().setUp(removed.getUp());
                }
                else
                    removed.getUp().setRight(removed.getRight());
            }
            if(removed.getRight()!=null)
                removed.getRight().setUp(removed.getUp());
            CheckBalance(removed.getUp());
        }
        if(removed==root)
            root=current;
    }

    public T Upper(T value) {
        Node current=Search(value);
        if(current.getRight()!=null) {
            current=current.getRight();
            while(current.getLeft()!=null)
                current=current.getLeft();
            return current.getValue();
        }
        else {
            while(current.getUp()!=null) {
                if(current.getUp().getLeft()==current) {
                    current=current.getUp();
                    return current.getValue();
                }
                current=current.getUp();
            }
            throw new NoSuchElementException();
        }
    }

    public T Lower(T value) {
        Node current=Search(value);
        if(current.getLeft()!=null) {
            current=current.getLeft();
            while(current.getRight()!=null)
                current=current.getRight();
            return current.getValue();
        }
        else {
            while(current.getUp()!=null) {
                if(current.getUp().getRight()==current) {
                    current=current.getUp();
                    return current.getValue();
                }
                current=current.getUp();
            }
            throw new NoSuchElementException();
        }
    }

    private void CheckBalance(Node current) {
        UpdateBalance(current);
        if(Math.abs(current.getBalance())>=2)
            Swap(current);
        if(current.getUp()!=null)
            CheckBalance(current.getUp());
    }

    private void UpdateBalance(Node current) {
        int left=0, right=0;
        if(current.getLeft()!=null)
            left=current.getLeft().getHeight();
        if(current.getRight()!=null)
            right=current.getRight().getHeight();
        current.setHeight(Math.max(left, right)+1);
        current.setBalance(right-left);
    }

    private boolean CompareBalances(Node left, Node right) {
        if(left==null)
            return false;
        if(right==null)
            return true;
        return Math.abs(left.getBalance())>Math.abs(right.getBalance());
    }

    private void Swap(Node node) {
        Node node1, node2;
        if(CompareBalances(node.getLeft(), node.getRight())) {
            node1=node.getLeft();
            if(CompareBalances(node1.getLeft(), node1.getRight())) {
                if(node==root)
                    root=node1;
                node2=node1.getLeft();
                node1.setUp(node.getUp());
                if(node.getUp()!=null) {
                    if(node.getUp().getLeft()==node)
                        node.getUp().setLeft(node1);
                    else
                        node.getUp().setRight(node1);
                }
                node.setLeft(node1.getRight());
                if(node.getRight()!=null)
                    node1.getRight().setUp(node);
                node.setUp(node1);
                node1.setRight(node);
                UpdateBalance(node2);
                UpdateBalance(node);
                UpdateBalance(node1);
            }
            else {
                node2=node1.getRight();
                if(node==root)
                    root=node2;
                node2.setUp(node.getUp());
                if(node.getUp()!=null) {
                    if(node.getUp().getLeft()==node)
                        node.getUp().setLeft(node2);
                    else
                        node.getUp().setRight(node2);
                }
                node.setLeft(node2.getRight());
                if(node2.getRight()!=null)
                    node2.getRight().setUp(node);
                node1.setRight(node2.getLeft());
                if(node2.getLeft()!=null)
                    node2.getLeft().setUp(node1);
                node2.setLeft(node1);
                node1.setUp(node2);
                node2.setRight(node);
                node.setUp(node2);
                UpdateBalance(node1);
                UpdateBalance(node);
                UpdateBalance(node2);
            }
        }
        else {
            node1=node.getRight();
            if(CompareBalances(node1.getLeft(), node1.getRight())) {
                if(node==root)
                    root=node1;
                node2=node1.getLeft();
                node2.setUp(node.getUp());
                if(node.getUp()!=null) {
                    if(node.getUp().getLeft()==node)
                        node.getUp().setLeft(node2);
                    else
                        node.getUp().setRight(node2);
                }
                node.setRight(node2.getLeft());
                if(node2.getLeft()!=null)
                    node2.getLeft().setUp(node);
                node1.setLeft(node2.getRight());
                if(node2.getRight()!=null)
                    node2.getRight().setUp(node1);
                node2.setRight(node1);
                node1.setUp(node2);
                node2.setLeft(node);
                node.setUp(node2);
                UpdateBalance(node1);
                UpdateBalance(node);
                UpdateBalance(node2);
            }
            else {
                if(node==root)
                    root=node1;
                node2=node1.getRight();
                node1.setUp(node.getUp());
                if(node.getUp()!=null) {
                    if(node.getUp().getLeft()==node)
                        node.getUp().setLeft(node1);
                    else
                        node.getUp().setRight(node1);
                }
                node.setRight(node1.getLeft());
                if(node1.getLeft()!=null)
                    node1.getLeft().setUp(node);
                node.setUp(node1);
                node1.setLeft(node);
                UpdateBalance(node2);
                UpdateBalance(node);
                UpdateBalance(node1);
            }
        }
    }

    private Node Search(T value)
    {
        Node current=Presearch(value);
        if(current==null)
            throw new NoSuchElementException();
        else if(current.getValue().compareTo(value)!=0)
            throw new NoSuchElementException();
        return current;
    }

    private Node Presearch(T value) {
        Preconditions.checkNotNull(value, "Argument value was null");
        Node current=root;
        while(current!=null) {
            int comprasion = value.compareTo(current.getValue());
            if(comprasion==0)
                break;
            else if(comprasion<0) {
                if(current.getLeft()==null)
                    break;
                current=current.getLeft();
            }
            else {
                if(current.getRight()==null)
                    break;
                current=current.getRight();
            }
        }
        return current;
    }
}
