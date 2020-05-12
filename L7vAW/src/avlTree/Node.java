package avlTree;

public class Node<T extends Comparable<? super T>> implements Visitable, Comparable<Node<T>>{
	private T value;
	private Node<T> parent;
	private Node<T> left;
	private Node<T> right;
	private int balanceFactor;
	private int height;
	
	public Node(T value) {
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.balanceFactor = 0;
		this.height = 0;
	}
	
	public Node(T value, Node<T> parent) {
		this.value = value;
		this.parent = parent;
		this.left = null;
		this.right = null;
		this.balanceFactor = 0;
		this.height = 0;
	}
	
	public void updateHeight() {
		if(this.left == null && this.right == null)
			this.height = 0;
		else if(this.left == null)
			this.height = this.right.getHeight() + 1;
		else if(this.right == null)
			this.height = this.left.getHeight() + 1;
		else if(this.left.getHeight() >= this.right.getHeight())
			this.height = this.left.getHeight() + 1;
		else this.height = this.right.getHeight() + 1;
	}
	
	public void updateBalanceFactor() {
		if(this.left == null && this.right == null) this.balanceFactor = 0;
		else if(this.left == null) this.balanceFactor = this.right.getHeight() + 1;
		else if(this.right == null) this.balanceFactor = -1 * this.left.getHeight() - 1;
		else this.balanceFactor = this.right.getHeight() - this.left.getHeight();		
	}
	
	@Override
	public String toString() {
		return value.toString() + " - " + balanceFactor + "/" + height +
				(parent != null ? " p: " + parent.getValue() : "") +
				(left != null ? " l: " + left.getValue() : "") +
				(right != null ? " r: " + right.getValue() : "");
	}
	
	@Override
	public int compareTo(Node<T> node) {
		return value.compareTo(node.value);
	}
	public void Accept(AVLTreeVisitor visitor) {
		visitor.visitNode(this);
	}

	public T getValue() {
		return value;
	}
	
	public Node<T> getParent() {
		return parent;
	}

	public Node<T> getLeft() {
		return left;
	}

	public Node<T> getRight() {
		return right;
	}
	
	public Node<T> getBrother() {
		if(parent == null) return null;
		if(parent.getLeft() != null && parent.getLeft().compareTo(this) == 0)
			return parent.getRight();
		else return parent.getLeft();
	}
	
	public boolean isRoot() {
		return parent == null;
	}

	public boolean isRight() {
		if(isRoot()) return false;
		return parent.getRight() != null && parent.getRight().compareTo(this) == 0;
	}
	public boolean isLeft() {
		if(isRoot()) return false;
		return parent.getLeft() != null && parent.getLeft().compareTo(this) == 0;
	}
	
	public int getBalanceFactor() {
		return balanceFactor;
	}

	public int getHeight() {
		return height;
	}
	
	public int getLevel() {
		if(isRoot()) return 0;
		return this.parent.getLevel() + 1;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
}
