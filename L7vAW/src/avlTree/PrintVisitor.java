package avlTree;

public class PrintVisitor extends AVLTreeVisitor {	
	@Override
	public void visitNode(Node<?> node) {
		System.out.println(node);
	}
}
