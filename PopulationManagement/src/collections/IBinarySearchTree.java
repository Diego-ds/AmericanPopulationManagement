package collections;

public interface IBinarySearchTree<K extends Comparable<K>,V>{
	public Node<K,V> insert(K key, V value);
	public boolean deleteValue(K key);
	//public int height(Node <K,V> root);
	//public int weight();
	public Node<K,V> searchValue(K key);
	//public ArrayList<V> inOrder();
	//public ArrayList<V> postOrder();
	//public ArrayList<V> preOrder();
}
