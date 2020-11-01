package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import collections.AVLTree;
import collections.Node;

class AVLTest {
	
	private AVLTree<Integer,Integer> avltree;
	
	public void setup1() {
		avltree= new AVLTree<Integer,Integer>();
	}
	
	@Test
	public void InsertTest () {
		//First test
		setup1();
		avltree.insert(1, 1);
		avltree.insert(2, 2);
		avltree.insert(3, 3);
		Node<Integer,Integer> node = avltree.getRoot();
		System.out.println(node.getValue());
	}
	

}
