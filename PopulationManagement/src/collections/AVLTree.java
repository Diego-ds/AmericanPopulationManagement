package collections;

public class AVLTree<K extends Comparable<K>,V> extends BinarySearchTree<K,V>{
	
	public AVLTree() {
		super();
	}
	
	
	public void insert(K key,V value){
		super.insert(key, value);
		Node<K,V> node = super.searchValue(key);
		node= balanceCases(node);
	}
	public int height(Node<K,V> node) {
		if(node==null) {
			return 0;
		}else {
			return node.getHeight();
		}
		
	}
	public int balanceFactor(Node<K,V> node) {
		if(node==null) {
			return 0;
		}else {
			return height(node.getRight()) - height(node.getLeft());
		}
	}
	
	public Node<K,V> balanceCases(Node<K,V> node) {
		System.out.println(node.getKey());
		System.out.println(node.getHeight());
		int balanceFactor = balanceFactor(node);
		System.out.println("balance "+balanceFactor);
		//Right imbalance
		if (balanceFactor > 1) {
			
			Node<K,V> right = node.getRight();
			int rightBalanceFactor = balanceFactor(right);
			
			if (rightBalanceFactor == 1 || rightBalanceFactor == 0) {
				return leftRotate(node);
			}
			else if (rightBalanceFactor == -1) {
				Node<K,V> ref = node.getRight();
				ref = rightRotate(right);
				return leftRotate(node);
			}
		}
		
		//Left Imbalance
		else if (balanceFactor < 1) {
			
			Node<K,V> left = node.getLeft();
			int leftBalanceFactor = balanceFactor(left);
			
			if (leftBalanceFactor == -1 || leftBalanceFactor == 0) {
				return rightRotate(node);
			}
			else if (leftBalanceFactor == -1) {
				Node<K,V> ref = node.getLeft();
				ref = leftRotate(left);
				return rightRotate(node);
			} 
		}
		
		return node;
	}
	
	public Node<K,V> rightRotate(Node<K,V> node) {
		System.out.println(node.getKey());
		System.out.println(node.getHeight());
		Node<K,V> left = node.getLeft();
		Node<K,V> leftRight = left.getRight();
		
		left.setRight(node);
		node.setLeft(leftRight);
		
		left.setHeight(Math.max(height(left.getRight()), height(left.getLeft())) + 1 );
		node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1 );
		
		return left;
	}
	
	public Node<K,V> leftRotate(Node<K,V> node) {
		Node<K,V> right = node.getRight();
		Node<K,V> rightLeft = right.getLeft();
		
		right.setLeft(node);
		node.setRight(rightLeft);
		
		right.setHeight(Math.max(height(right.getRight()), height(right.getLeft())) + 1 );
		node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1 );
		
		return right;
	}
	
	
	
	
	
	
	/*
	@Override
	public void insert(K key,V value) {
		super.insert(key,value);
		Node<K,V> n = searchValue(key);
		balance(n);
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
		while(node!=null) {
			int balanceFactor = balanceFactor(node);
			
			if(balanceFactor>1) {
				rightCases(node.getRight());
			}else if(balanceFactor<-1) {
				leftCases(node.getLeft());
			}
			node=node.getFather();
		}
	
	}
	
	public void rightCases(Node<K,V> nodeRight) {
		int balanceFactor = balanceFactor(nodeRight);
		if(balanceFactor==1 || balanceFactor==0) {
			leftRotate(nodeRight);
		}else{
			Node<K,V> parent = nodeRight.getFather();
			rightRotate(nodeRight);
			leftRotate(parent);
		}
	}
	
	public void leftCases(Node<K,V> nodeLeft) {
		int balanceFactor = balanceFactor(nodeLeft);
		if(balanceFactor==-1 || balanceFactor==0) {
			rightRotate(nodeLeft.getFather());
		}else{
			Node<K,V> parent = nodeLeft.getFather();
			leftRotate(nodeLeft);
			rightRotate(parent);
		}
	}

	//atributo para la altura bien melo B)
	public void rightRotate(Node <K,V> node) {	
		Node<K,V> parent =node.getFather();
		Node <K,V> left = node.getLeft();
		if(left.getRight()!=null) {
			Node <K,V> leftRightTree = left.getRight();
			node.setLeft(leftRightTree);
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
		Node <K,V> right = node.getRight();
		Node <K,V> parent = node.getFather();
		if(parent!=null) {
			if(parent.getRight().equals(node)) {
				parent.setRight(right);
				right.setFather(parent);
			}else {
				parent.setLeft(right);
				right.setFather(parent);
			}
		}else{
			setRoot(right);
		}
		if(right.getLeft()!=null) {
			node.setRight(right.getLeft());
			right.getLeft().setFather(node);
		}
		right.setLeft(node);
		node.setFather(right);
	}
	
*/
	
}
