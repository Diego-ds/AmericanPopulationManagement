package collections;

public class Node<K extends Comparable<K>,V> {

	private K key;
	private V value;
	private Node<K,V> left;
	private Node<K,V> right;
	private Node<K,V> father;

	public Node (K key,V value) {
		this.value=value;
		this.key=key;
		left = null;
		right = null;
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
}

