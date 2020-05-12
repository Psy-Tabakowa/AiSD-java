package avlTree;

public class AVLTree<T extends Comparable<? super T>> {
	protected Node<T> root;
	
	public AVLTree() {
		root = null;
	}
	
	public AVLTree(T value) {
		root = new Node<T>(value);
	}
	
	public void walk(WalkType order, AVLTreeVisitor visitor) {
		if(visitor == null || order == null) return;
		walkRec(order, visitor, this.root);
	}
	
	private void walkRec(WalkType order, AVLTreeVisitor visitor, Node<T> node) {
		if(node == null) return;
		switch(order) {
		case preOrder:
			node.Accept(visitor);
			walkRec(order, visitor, node.getLeft());
			walkRec(order, visitor, node.getRight());
			break;
		case inOrder:
			walkRec(order, visitor, node.getLeft());
			node.Accept(visitor);
			walkRec(order, visitor, node.getRight());
			break;
		case invertedInOrder:
			walkRec(order, visitor, node.getRight());
			node.Accept(visitor);
			walkRec(order, visitor, node.getLeft());
			break;
			case postOrder:
			walkRec(order, visitor, node.getLeft());
			walkRec(order, visitor, node.getRight());
			node.Accept(visitor);
			break;
		}
		return;
	}
	
	public void rotate(Node<T> parent, Node<T> node) {
		if(parent == null || node == null) return;
		if(node.getParent() == null || node.getParent().compareTo(parent) != 0) return;	
		
		if(parent.isRoot()) this.root = node;
		else if(parent.isLeft()) parent.getParent().setLeft(node);
		else if(parent.isRight()) parent.getParent().setRight(node);
		
		if(node.isLeft()) {
			parent.setLeft(node.getRight());
			if(node.getRight() != null) node.getRight().setParent(parent);
			node.setRight(parent);
		}
		else {
			parent.setRight(node.getLeft());
			if(node.getLeft() != null) node.getLeft().setParent(parent);
			node.setLeft(parent);
		}
		node.setParent(parent.getParent());
		parent.setParent(node);
	}
	
	
	public void mendAVL(Node<T> parent, OperationType opType) {
		if(parent == null) return;
		do {	
			parent.updateHeight();
			parent.updateBalanceFactor();
			
			if(parent.getBalanceFactor() == 0 && opType == OperationType.insertion) break;
			if((parent.getBalanceFactor() == 1 || parent.getBalanceFactor() == -1) &&
					opType == OperationType.deletion) break;
			if(parent.getBalanceFactor() == 2 || parent.getBalanceFactor() == -2) {
				if(parent.getBalanceFactor() == 2 && parent.getRight().getBalanceFactor() >= 0 ||
					parent.getBalanceFactor() == -2 && parent.getLeft().getBalanceFactor() >= 0	) {
					//przypadek I
					if(parent.getBalanceFactor() == 2) rotate(parent, parent.getRight());
					else rotate(parent, parent.getLeft());
				}
				else {
					//przypadek II
					Node<T> z;
					if(parent.getBalanceFactor() == 2) {
						z = parent.getRight().getLeft();
						rotate(parent.getRight(), z);
					}
					else {
						z = parent.getLeft().getRight();
						rotate(parent.getLeft(), z);
					}
					
					rotate(parent, z);
					if(parent.getBrother() != null) {
						parent.getBrother().updateHeight();
						parent.getBrother().updateBalanceFactor();						
					}
				}
				
				parent.updateHeight();
				parent.updateBalanceFactor();
				if(parent.getParent() != null) {
					parent.getParent().updateBalanceFactor();
					parent.getParent().updateHeight();					
				}
			}
			parent = parent.getParent();
		} while(parent != null);
	}
	
	public boolean insert(T value) {
		if(value == null) throw new NullPointerException("Argument 'value' cannot be null");
		if(root == null) {
			root = new Node<T>(value);
			return true;
		}
		
		Node<T> node = root;
		Node<T> parent = null;
		do {
			parent = node;
			if(node.getValue().compareTo(value) == 0) return false;
			if(node.getValue().compareTo(value) < 0) node = node.getRight();
			else node = node.getLeft();
		} while(node != null);
		
		node = new Node<T>(value, parent);
		
		if(parent.getValue().compareTo(value) < 0)
			parent.setRight(node);
		else parent.setLeft(node);
		
		mendAVL(parent, OperationType.insertion);
		
		return true;
	}
	
	public Node<T> getMin(Node<T> node) {
		if(node == null) return node;
		if(node.getLeft() == null) return node;
		return node.getLeft();
	}
	
	public boolean delete(T value) {
		Node<T> node = root;
		do {
			if(node.getValue().compareTo(value) == 0) break;
			if(node.getValue().compareTo(value) < 0) node = node.getRight();
			else node = node.getLeft();
		} while(node != null);
		if(node == null) return false;
		
		Node<T> parent = null;
		if(node.getLeft() != null && node.getRight() != null) {
			//usuwany element ma dwóch potomków
			Node<T> next = getMin(node.getRight());
			parent = next.getParent();
			node.setValue(next.getValue());
			if(next.isRight()) {
				//jest prawym potomkiem node'a
				if(next.getRight() != null) {
					node.setRight(next.getRight());
					next.getRight().setParent(node);				
				}
				else node.setRight(null);
			}
			else {
				//jest lewym potomkiem potomków node'a
				if(next.getRight() != null) {
					next.getParent().setLeft(next.getRight());
					next.getRight().setParent(next.getParent());				
				}
				else next.getParent().setLeft(null);
			}	
		}
		else if(node.getLeft() == null && node.getRight() != null) {
			//usuwany element ma tylko prawego potomka
			if(node.isRoot()) {
				root = node.getRight();
				node.getRight().setParent(null);
			}
			else if(node.isLeft()) {
				parent = node.getParent();
				node.getParent().setLeft(node.getRight());
				node.getRight().setParent(node.getParent());
			}
			else {
				parent = node.getParent();
				node.getParent().setRight(node.getRight());
				node.getRight().setParent(node.getParent());
			}
		}
		else if(node.getLeft() != null && node.getRight() == null) {
			//usuwany elemnt ma tylko lewego potomka
			if(node.isRoot()) {
				root = node.getLeft();
				node.getLeft().setParent(null);
			}
			else if(node.isLeft()) {
				parent = node.getParent();
				node.getParent().setLeft(node.getLeft());
				node.getLeft().setParent(node.getParent());
			}
			else {
				parent = node.getParent();
				node.getParent().setRight(node.getLeft());
				node.getLeft().setParent(node.getParent());
			}
		}
		else {
			//usuwany element nie ma potomków
			if(!node.isRoot()) parent = node.getParent();
			if(node.isRoot()) root = null;
			else if(node.isLeft()) node.getParent().setLeft(null);
			else node.getParent().setRight(null);
		}
		
		//parent rodzicem usuniêtego elementu/nexta; tu zaczniemy naprawianie AVL
		mendAVL(parent, OperationType.deletion);
		
		return true;
	}
	
	public T upper(T value) {
		if(value == null) throw new NullPointerException("Argument 'value' cannot be null");
		Node<T> min = upperRec(value, root);
		if(min != null) return min.getValue();
		else return null;
	}
	
	private Node<T> upperRec(T value, Node<T> node) {
		if(node.getValue().compareTo(value) == 0) return node;
		if(node.getValue().compareTo(value) < 0) {
			if(node.getRight() == null) return null;
			return upperRec(value, node.getRight());
		}
		else {
			if(node.getLeft() == null) return node;
			if(node.getLeft().getValue().compareTo(value) >= 0)
				return upperRec(value, node.getLeft());
			else {
				Node<T> minL = upperRec(value, node.getLeft());
				if(minL == null) return node;
				else return minL;
			}
		}
	}
	
	public T lower(T value) {
		if(value == null) throw new NullPointerException("Argument 'value' cannot be null");
		Node<T> max = lowerRec(value, root);
		if(max != null) return max.getValue();
		else return null;
	}
	
	private Node<T> lowerRec(T value, Node<T> node) {
		if(node.getValue().compareTo(value) == 0) return node;
		if(node.getValue().compareTo(value) < 0) {
			if(node.getRight() == null) return node;
			if(node.getRight().getValue().compareTo(value) <= 0)
					return lowerRec(value, node.getRight());
			else {
				Node<T> minR = lowerRec(value, node.getRight());
				if(minR == null) return node;
				else return minR;
			}
		}
		else {
			if(node.getLeft() == null) return null;
			return lowerRec(value, node.getLeft());
		}
	}
	
	public int getHeight() {
		if(root == null) return -1;
		return root.getHeight();
	}
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
}