package collections;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Node<K extends Comparable<K>,V> implements Serializable{
	
	private int height;
	private K key;
	private V value;
	private Node<K,V> left;
	private Node<K,V> right;
	private Node<K,V> father;

	public Node (K key,V value) {
		this.value = value;
		this.key = key;
		left = null;
		right = null;
		father = null;
		this.height = 0;
	}

	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	public Node<K,V> getLeft() {
		return left;
	}

	public Node<K,V> getRight() {
		return right;
	}

	public Node<K,V> getFather() {
		return father;
	}


	public void setLeft(Node<K,V> left) {
		this.left = left;
	}

	public void setRight(Node<K,V> right) {
		this.right = right;
	}

	public void setFather(Node<K,V> father) {
		this.father = father;
	}
	
	public void setHeight(int height) {
		this.height=height;
	}
	public int getHeight() {
		return this.height;
	}
}

