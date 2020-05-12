package avlTree;

public class CascadePrintVisitor extends AVLTreeVisitor{
	private int height;
	
	public CascadePrintVisitor() {
		height = 0;
	}
	
	public CascadePrintVisitor(int height) {
		this.height = height;
	}
	
	@Override
	public void visitNode(Node<?> node) {
		for(int i=0; i<node.getLevel(); i++)
			System.out.print("   ");
		System.out.println(node.getValue());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
