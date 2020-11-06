package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import collections.AVLTree;
import collections.Node;

class AVLTest {
	
	private AVLTree<Integer,Integer> avltree;
	
	public void setup1() {
		avltree = new AVLTree<Integer,Integer>();
		Integer[] num = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		for (int i = 0; i < num.length; i++) {
			avltree.insert(num[i], num[i]);
		}
	}
	
	public void setup2() {
		avltree = new AVLTree<Integer,Integer>();
		Integer[] num = new Integer[] {50,100,0,25,-25,125,75,30};
		for (int i = 0; i < num.length; i++) {
			avltree.insert(num[i], num[i]);
		}
	}
	
	public void setup3() {
		avltree = new AVLTree<Integer,Integer>();
	}
	
	@Test
	public void InsertTest () {
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
	public void InsertTest2 () {
		//First test
		setup1();
		avltree.insert(10, 10);
		avltree.insert(5, 5);
		avltree.insert(6, 6);
		Node<Integer,Integer> node = avltree.getRoot();
		Assertions.assertTrue(node.getKey()==6);
		/*System.out.println("height "+node.getHeight());
		System.out.println("height "+node.getRight().getHeight());
		System.out.println("height "+node.getRight().getRight().getHeight());
		System.out.println(node.getValue());
		System.out.println(node.getRight());
		System.out.println(node.getLeft());*/
	}
	
	@Test
	public void InsertTest3 () {
		//First test
		setup1();
		avltree.insert(5, 5);
		avltree.insert(4, 4);
		avltree.insert(2, 2);
		Node<Integer,Integer> node = avltree.getRoot();
		Assertions.assertTrue(node.getKey()==4);
		/*System.out.println("height "+node.getHeight());
		System.out.println("height "+node.getRight().getHeight());
		System.out.println("height "+node.getRight().getRight().getHeight());
		System.out.println(node.getValue());
		System.out.println(node.getRight());
		System.out.println(node.getLeft());*/
	}
	
	
	@Test
	public void searchTest () {
		//First test
		setup1();
		avltree.insert(1, 1);
		avltree.insert(2, 2);
		avltree.insert(3, 3);
		avltree.insert(-1, -1);
		avltree.insert(-2, -2);
		avltree.insert(-3, -3);
		Assertions.assertTrue(avltree.searchValue(1).getValue()==1);
		Assertions.assertTrue(avltree.searchValue(3).getValue()==3);
		Assertions.assertTrue(avltree.searchValue(2).getValue()==2);
		Assertions.assertTrue(avltree.searchValue(-2).getValue()==-2);
		Assertions.assertTrue(avltree.searchValue(-3).getValue()==-3);
		Assertions.assertTrue(avltree.searchValue(-1).getValue()==-1);
	}
}
