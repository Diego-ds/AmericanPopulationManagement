package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.plaf.basic.BasicRootPaneUI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import collections.BinarySearchTree;
import collections.Node;

public class ABBTest {
	
	private BinarySearchTree<Integer, Integer> bst;
	
	public void setup1() {
		bst = new BinarySearchTree<Integer, Integer>();
		Integer[] num = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		for (int i = 0; i < num.length; i++) {
			bst.insert(num[i], num[i]);
		}
	}
	
	public void setup2() {
		bst = new BinarySearchTree<Integer, Integer>();
		Integer[] num = new Integer[] {50,100,0,25,-25,125,75,30};
		for (int i = 0; i < num.length; i++) {
			bst.insert(num[i], num[i]);
		}
	}
	
	public void setup3() {
		bst = new BinarySearchTree<Integer, Integer>();
	}
	
	@Test
	public void InsertTest () {
		//First test
		setup2();
		
		String expectedTree = "-25 30 25 0 75 125 100 50 ";
		String realTree = bst.postOrder(bst.getRoot(), "");
		
		Assertions.assertEquals(expectedTree, realTree);
		
		//Second test
		setup1();
		
		expectedTree = "10 9 8 7 6 5 4 3 2 1 ";
		realTree = bst.postOrder(bst.getRoot(), "");
		
		Assertions.assertEquals(expectedTree, realTree);
	}
	
	@Test
	public void searchValueTest () {
		setup1();
		
		Assertions.assertTrue(bst.searchValue(1).getValue() == 1);
		Assertions.assertTrue(bst.searchValue(5).getValue() == 5);
		Assertions.assertTrue(bst.searchValue(10).getValue() == 10);
		Assertions.assertNull(bst.searchValue(13));
	}
	
	@Test
	public void deleteValueTest () {
		setup2();
		
		Assertions.assertTrue(bst.deleteValue(50));
		Assertions.assertNull(bst.searchValue(50));
		
		Assertions.assertTrue(bst.deleteValue(25));
		Assertions.assertNull(bst.searchValue(25));
		
		Assertions.assertTrue(bst.deleteValue(-25));
		Assertions.assertNull(bst.searchValue(-25));
		
		Assertions.assertFalse(bst.deleteValue(13));
	}
	
	@Test
	public void heightTest() {
		setup2();
		
		Assertions.assertTrue(bst.height(bst.getRoot()) == 4);
		
		Assertions.assertFalse(bst.height(bst.getRoot()) == 2);
		
		Node<Integer, Integer> node = bst.searchValue(100);
		Assertions.assertTrue(bst.height(node) == 2);
		
		node = bst.searchValue(-25);
		System.out.println(bst.height(node));
		Assertions.assertTrue(bst.height(node) == 1);
	}
}
