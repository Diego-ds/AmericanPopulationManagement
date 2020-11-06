package collections;


public class AVLTree<K extends Comparable<K>,V> extends BinarySearchTree<K,V>{
	
	public AVLTree() {
		super();
	}
	
	@Override
	public boolean deleteValue(K key) {
		boolean val=false;
		Node<K,V> n =searchValue(key).getFather();
		deleteValue(key);
		if(n.getRight()!=null || n.getLeft()!=null) {
			if(n.getRight()!=null) {
				balance(n.getRight());
			}else {
				balance(n.getLeft());
			}
		}else {
			balance(n);
		}
		return val;
	}
	
	@Override
	public void insert(K key,V value) {
		super.insert(key,value);
		Node<K,V> n =searchValue(key);
		balance(n);
	}
	

	public int height(Node<K,V> node) {
		if(node==null) {
			return -1;
		}else { 
			return node.getHeight();
		}

	}
	
	public int balanceFactor (Node<K,V> node) {
		if(node!=null) {
			
			int right = height(node.getRight());
			int left = height(node.getLeft());
			
			return right - left;
		}
		return 0;
	}
	
	public void balance(Node<K,V> node) {
		if(node!=null) {
			//System.out.println(node.getHeight()+" key "+node.getKey()); //print
			int balanceFactor = balanceFactor(node);
			if(balanceFactor>1) {
				rightCases(node);
			}else if(balanceFactor<-1) {
				leftCases(node);
			}
			balance(node.getFather());
		}
	}
	
	public void rightCases(Node<K,V> node) {
		int balanceFactor = balanceFactor(node.getRight());
		
		if(balanceFactor==0 || balanceFactor==1) {
			leftRotate(node);	
			updateHeight(node);
		}else{
			rightRotate(node.getRight());
			leftRotate(node);
			updateHeight(node);
			updateHeight(node.getFather());
		}
	}
	
	public void leftCases(Node<K,V> node) {
		int balanceFactor = balanceFactor(node.getLeft());
		if(balanceFactor==-1 || balanceFactor==0) {
			rightRotate(node);
			updateHeight(node);
		}else{
			leftRotate(node.getLeft());
			rightRotate(node);
			updateHeight(node);
			updateHeight(node.getFather());
		}
	}

	//atributo para la altura bien melo B)
	public void rightRotate(Node <K,V> node) {	
		Node<K,V> parent =node.getFather();
		Node <K,V> left = node.getLeft();
		if(left.getRight()!=null) {
			Node <K,V> leftRightTree = left.getRight();
			leftRightTree.setFather(node);
		}else {
			node.setLeft(null);
		}
		left.setRight(node);
		node.setFather(left);
		left.setFather(parent);
		if(parent!=null && node==parent.getLeft()) {
			parent.setLeft(left);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(left);
		}else {
			setRoot(left);
		}
	}
	
	public void leftRotate(Node <K,V> node) {
		Node<K,V> parent =node.getFather();
		Node <K,V> right = node.getRight();
		if(right.getLeft()!=null) {
			Node <K,V> rightLeft = right.getLeft();
			rightLeft.setFather(node);
		}else {
			node.setRight(null);
		}
		right.setLeft(node);
		node.setFather(right);
		right.setFather(parent);
		if(parent!=null && node==parent.getLeft()) {
			parent.setLeft(right);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(right);
		}else {
			setRoot(right);
		}
	}
	
	public Node<K,V> getRoot(){
		return super.getRoot();
		
	}
}
