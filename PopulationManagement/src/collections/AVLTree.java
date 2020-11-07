package collections;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AVLTree<K extends Comparable<K>,V> extends BinarySearchTree<K,V> 
	implements Serializable {
	
	private int counter;
	
	public AVLTree(){
		super();
		counter = 0;
	}
	
	/* ARREGLAR EL DESGRACIADO ARBOL
	 * AAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	 */
	
	@Override
	public boolean deleteValue(K key) {
		boolean val = false;
		Node<K,V> n = searchValue(key);
		
		if (n != null) {
		Node<K,V> right =n.getRight();
		Node<K,V> left =n.getLeft();
		Node<K,V> father=null;
		if(n.getFather()!=null) {
			father=n.getFather();
		}
		
		val= super.deleteValue(key);
		if(father!=null) {
			if(father.getRight() != null || father.getLeft() != null) {
				if(n.getRight()!=null) {
					balance(father.getRight());
				}else {
					balance(father.getLeft());
				}
			}else {
				balance(father);
			}
		}else {
			if(right !=null || left != null) {
				if(right!=null && left!=null) {
					balance(right);
					balance(left);
				}else if(right!=null) {
					balance(right);
				}else {
					balance(left);
				}
			}
		}
		counter--;
		}
		return val;
	}


	public void insertAVL(K key,V value) {
		Node<K,V> n =super.insert(key,value);
		balance(n);
		counter++;
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
	
	public void balance (Node<K,V> node) {
		while(node!=null) {
			int balanceFactor = balanceFactor(node);
			if(balanceFactor>1) {
				rightCases(node);
			}else if(balanceFactor<-1) {
				leftCases(node);
			}
			node = node.getFather();
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
			if(node.getFather()!=null) {
				updateHeight(node.getFather());
			}else {
				updateHeight(node);
			}
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
			if(node.getFather()!=null) {
				updateHeight(node.getFather());
			}else {
				updateHeight(node);
			}
			
		}
	}

	//atributo para la altura bien melo B)
	public void rightRotate(Node <K,V> node) {	
		Node<K,V> parent =node.getFather();
		Node <K,V> left= node.getLeft();
		if(left.getRight()!=null) {
			Node <K,V> leftRightTree = left.getRight();
			leftRightTree.setFather(node);
			node.setLeft(leftRightTree);
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
		if(right.getLeft() != null) {
			Node <K,V> rightLeft = right.getLeft();
			rightLeft.setFather(node);
			node.setRight(rightLeft);
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

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
