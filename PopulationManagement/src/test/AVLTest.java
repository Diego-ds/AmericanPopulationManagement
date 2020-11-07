package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import collections.AVLTree;
import collections.Node;
import model.Manager;
import model.Record;

class AVLTest {
	
	private AVLTree<Integer,Integer> avltree;
	
	public void setup1() {
		avltree = new AVLTree<Integer,Integer>();
		Integer[] num = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		for (int i = 0; i < num.length; i++) {
			avltree.insertAVL(num[i], num[i]);
		}
	}
	
	public void setup2() {
		avltree = new AVLTree<Integer,Integer>();
		Integer[] num = new Integer[] {50,100,0,25,-25,125,75,30};
		for (int i = 0; i < num.length; i++) {
			avltree.insertAVL(num[i], num[i]);
		}
	}
	
	public void setup3() {
		avltree = new AVLTree<Integer,Integer>();
	}
	
	@Test
	public void insertAVLTest () {
		setup1();
		Node<Integer, Integer> node = avltree.getRoot(); //Root should be node with key 4
		assertTrue(node.getKey() == 4 && node.getHeight() == 3);
		node = avltree.searchValue(9);
		assertTrue(node.getKey() == 9 && node.getHeight() == 1);
		node = avltree.searchValue(3);
		assertTrue(node.getKey() == 3 && node.getHeight() == 0);
		assertNull(avltree.searchValue(13));
	}
	
	@Test
	public void balanceTest1 () {
		setup3();
		avltree.insertAVL(5, 5);
		avltree.insertAVL(3, 3);
		avltree.insertAVL(8, 8);
		avltree.insertAVL(6, 6);
		avltree.insertAVL(9, 9);
		avltree.insertAVL(10, 10);
		
		//Root
		assertTrue(avltree.getRoot().getKey() == 8);
		//Root left child
		assertTrue(avltree.getRoot().getLeft().getKey() == 5);
		//Root right child
		assertTrue(avltree.getRoot().getRight().getKey() == 9);
		//Root left child right child
		assertTrue(avltree.getRoot().getLeft().getRight().getKey() == 6);
	}
	
	@Test
	public void balanceTest2() {
		setup3();
		avltree.insertAVL(5, 5);
		avltree.insertAVL(10, 10);
		avltree.insertAVL(3, 3);
		avltree.insertAVL(2, 2);
		avltree.insertAVL(7, 7);
		avltree.insertAVL(11, 11);
		avltree.insertAVL(12, 12);
		avltree.insertAVL(6, 6);
		avltree.insertAVL(8, 8);
		avltree.insertAVL(9, 9);
		
		//Root
		assertTrue(avltree.getRoot().getKey() == 7);
		//Root left child
		assertTrue(avltree.getRoot().getLeft().getKey() == 5);
		//Root right child
		assertTrue(avltree.getRoot().getRight().getKey() == 10);
		//Root left child right child
		assertTrue(avltree.getRoot().getLeft().getRight().getKey() == 6);
		//Root right child left child
		assertTrue(avltree.getRoot().getRight().getLeft().getKey() == 8);
	}
	
	@Test
	public void balanceTest3() {
		setup3();
		avltree.insertAVL(10, 10);
		avltree.insertAVL(11, 11);
		avltree.insertAVL(7, 7);
		avltree.insertAVL(6, 6);
		avltree.insertAVL(8, 8);
		avltree.insertAVL(5, 5);
		
		//Root
		assertTrue(avltree.getRoot().getKey() == 7);
		//Root left child
		assertTrue(avltree.getRoot().getLeft().getKey() == 6);
		//Root right child
		assertTrue(avltree.getRoot().getRight().getKey() == 10);
		//Root right child left child
		assertTrue(avltree.getRoot().getRight().getLeft().getKey() == 8);
	}
	
	@Test
	public void balanceTest4() {
		setup3();
		avltree.insertAVL(10, 10);
		avltree.insertAVL(11, 11);
		avltree.insertAVL(4, 4);
		avltree.insertAVL(3, 3);
		avltree.insertAVL(6, 6);
		avltree.insertAVL(12, 12);
		avltree.insertAVL(5, 5);
		avltree.insertAVL(7, 7);
		avltree.insertAVL(2, 2);
		avltree.insertAVL(9, 9);
		
		//Root
		assertTrue(avltree.getRoot().getKey() == 6);
		//Root left child
		assertTrue(avltree.getRoot().getLeft().getKey() == 4);
		//Root right child
		assertTrue(avltree.getRoot().getRight().getKey() == 10);
		//Root left child right child
		assertTrue(avltree.getRoot().getLeft().getRight().getKey() == 5);
		//Root right child left child
		assertTrue(avltree.getRoot().getRight().getLeft().getKey() == 7);
	}
	
	@Test
	public void searchTest () {
		//First test
		setup1();
		avltree.insertAVL(1, 1);
		avltree.insertAVL(2, 2);
		avltree.insertAVL(3, 3);
		avltree.insertAVL(-1, -1);
		avltree.insertAVL(-2, -2);
		avltree.insertAVL(-3, -3);
		Assertions.assertTrue(avltree.searchValue(1).getValue()==1);
		Assertions.assertTrue(avltree.searchValue(3).getValue()==3);
		Assertions.assertTrue(avltree.searchValue(2).getValue()==2);
		Assertions.assertTrue(avltree.searchValue(-2).getValue()==-2);
		Assertions.assertTrue(avltree.searchValue(-3).getValue()==-3);
		Assertions.assertTrue(avltree.searchValue(-1).getValue()==-1);
	}
	
	@Test
	public void deleteValueTest () {
		setup2();
		
		Assertions.assertTrue(avltree.deleteValue(50));
		Assertions.assertNull(avltree.searchValue(50));
		
		Assertions.assertTrue(avltree.deleteValue(25));
		Assertions.assertNull(avltree.searchValue(25));
		
		Assertions.assertTrue(avltree.deleteValue(-25));
		Assertions.assertNull(avltree.searchValue(-25));
		
		Assertions.assertFalse(avltree.deleteValue(13));
	}
	
	@Test
	public void generatorTest() throws IOException {
		setup3();
		Manager manager = new Manager();
		Record[] record = new Record[100000];
		manager.loadData();
		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
			record[i] = manager.generateRecord();
		}
		manager.setRecords(record);
		manager.saveData();
	}
	
	@Test
	public void generatorTest2() throws IOException {
		AVLTree<String, String> tree = new AVLTree<>();
		tree.insertAVL("A8119072638749931979", "A8");
		tree.insertAVL("K7875554718347284512", "K7");
		tree.insertAVL("I9061786276594817152", "I9"); 
		tree.insertAVL("F6437870050708688863" , "F6");
		tree.insertAVL("A1755217046139629481" , "A1");
		tree.insertAVL("P7270701816894334697" , "P7");
		tree.insertAVL("M4851340702606442530" , "M4");
		tree.insertAVL("Y7335136876768043289" , "Y7");
	}
}