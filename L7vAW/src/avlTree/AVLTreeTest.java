package avlTree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {
	private AVLTree<Integer> tree;
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private ArrayList<Integer> sortedList = new ArrayList<Integer>();

	@Before
	public void setUp() throws Exception {
		tree = new AVLTree<Integer>();
		Random random = new Random();
		int r;
		for(int i=0; i<1000; i++) {
			do {
				r = random.nextInt();
			} while(!tree.insert(r));
			list.add(r);
		}
	}

	@Test
	public final void sortTest() {
		TestVisitor visitor = new TestVisitor();
		tree.walk(WalkType.inOrder, visitor);
		System.out.println(tree.getHeight());
		System.out.println(list.size());
		Collections.sort(list);
		assertArrayEquals(list.toArray(), sortedList.toArray());
	}
	
	@Test
	public final void deleteTest1() {
		for(int i=0; i<1000; i++) {
			tree.delete(tree.upper(Integer.MIN_VALUE));
		}	
	}
	
	@Test
	public final void deleteTest2() {
		for(int i=0; i<1000; i++) {
			tree.delete(tree.lower(Integer.MAX_VALUE));
		}	
	}
	
	private class TestVisitor extends AVLTreeVisitor {
		@Override
		public void visitNode(Node<?> node) {
			sortedList.add((Integer)node.getValue());
		}	
	}
}
