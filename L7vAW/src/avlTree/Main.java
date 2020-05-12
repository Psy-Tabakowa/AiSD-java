package avlTree;

public class Main {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>(25);
		PrintVisitor visitor = new PrintVisitor();
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.insert(50);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.insert(-37);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.insert(15);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.insert(56);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.insert(69);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();

		tree.insert(55);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();

		tree.insert(58);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.delete(15);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.delete(56);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.delete(68);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.delete(69);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();
		
		tree.insert(26);
		tree.walk(WalkType.inOrder, visitor);
		System.out.println();

		CascadePrintVisitor visitor2 = new CascadePrintVisitor(tree.getHeight());
		tree.walk(WalkType.invertedInOrder, visitor2);
	}

}
