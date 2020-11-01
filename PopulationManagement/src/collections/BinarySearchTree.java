package collections;



public class BinarySearchTree<K extends Comparable<K>,V> implements IBinarySearchTree<K,V> {
	
	private Node<K,V> root;
	
	@Override
	public void insert(K key,V value) {
		if (root == null) {
			root = new Node<K,V>(key, value);
		}else {
			insert(key,value,root);
		}	
	}
	
	private void insert(K key,V value, Node<K,V> actual) {
			if (key.compareTo(actual.getKey()) <= 0) {
				if (actual.getLeft() == null) {
					actual.setLeft(new Node<K,V>(key, value));
					actual.getLeft().setFather(actual);
				}
				else {
					insert (key, value, actual.getLeft());
				}
			}
			//Right
			else {
				if (actual.getRight() == null) {
					actual.setRight(new Node<K,V>(key, value));
					actual.getRight().setFather(actual);
				}
				else {
					insert (key, value, actual.getRight());
				}					
			}
		}
	
	@Override
	public Node<K,V> searchValue(K key) {
		if(root==null) {
			return null;
		}else {
			return searchValue(key,root);
		}
	}
	
	private Node<K,V> searchValue (K toSearch,Node<K,V> actual) {
		if (actual.getKey().compareTo(toSearch) == 0) {
			return actual;
		}
		else if (toSearch.compareTo(actual.getKey()) < 0) {
			if (actual.getLeft()!=null) {
				return searchValue (toSearch, actual.getLeft());
			}
		}
		else {
			if (actual.getRight() != null) {
				return searchValue (toSearch, actual.getRight());
			}
		}
		return null;
	}
	
	@Override
	public boolean deleteValue(K toRemove) {
		if(root!=null) {
			return deleteValue(toRemove,root);
		}else {
			return false;
		}
	}
	private boolean deleteValue (K toRemove, Node<K,V> actual) {
		boolean removed = false;
		if (actual.getKey().compareTo(toRemove) == 0) {
			//If it has two children
			if (actual.getLeft() != null && actual.getRight() != null) {
				removeBinaryTreeTwoChildren (actual);
			}
			//If it has 1 children
			else if (actual.getLeft() != null || actual.getRight() != null){
				removeBinaryTreeOneChild (actual);
			}
			//If it doesn't have children
			else if (actual.getLeft() == null && actual.getRight() == null) {
				removeBinaryTreeNoChildren (actual);
			}
			removed = true;
		}
		else if (toRemove.compareTo(actual.getKey()) < 0) {
			if (actual.getLeft() != null) {
				removed = deleteValue (toRemove, actual.getLeft());
			}
		}
		else {
			if (actual.getRight() != null) {
				removed = deleteValue (toRemove, actual.getRight());
			}
		}
		return removed;
	}
	
	private void removeBinaryTreeNoChildren (Node<K,V> goner) {
		if (goner != root) {
			Node<K,V> father = goner.getFather();

			if (father.getLeft() == goner) {
				father.setLeft(null);
			}
			else {
				father.setRight(null);
			}
		}
		else {
			root = null;
		}
	}
	private void removeBinaryTreeOneChild (Node<K,V> goner) {
		Node<K,V> child;
		if (goner.getLeft() != null) {
			child = goner.getLeft();
		}
		else {
			child = goner.getRight();
		}

		if (goner != root) {
			Node<K,V> father = goner.getFather();
			child.setFather(father);
			
			if (father.getLeft() == goner) {
				father.setLeft(child);
			}
			else {
				father.setRight(child);
			}
		}
		else {
			root = child;
		}
	}
	
	private void removeBinaryTreeTwoChildren (Node<K,V> goner) {
		Node<K,V> minor = searchMinor (goner);
		
			//Minor has no child
			if (minor.getRight() == null) {
				removeBinaryTreeNoChildren(minor);
			}
			//Minor has a child
			else {
				removeBinaryTreeOneChild(minor);
			}
			//New references to minor
			Node<K,V> leftChild = goner.getLeft();
			Node<K,V> rightChild = goner.getRight();
			Node<K,V> father = goner.getFather();
			
			minor.setLeft(leftChild);
			minor.setRight(rightChild);
			minor.setFather(father);
			//References around minor 
			if (leftChild != null) {
				leftChild.setFather(minor);
			}
			
			if (rightChild != null) {
				rightChild.setFather(minor);
			}

			if(goner != root && goner == father.getLeft()) {
				father.setLeft(minor);
			}
			else if (goner != root) {
				father.setRight(minor);
			}
			if (goner == root) {
				root = minor;
			}
	}
	
	private Node<K,V> searchMinor(Node<K,V> father) {
		Node<K,V> minor = father.getRight();
		
		while (minor.getLeft() != null) {
			
			minor = minor.getLeft();	
		}
		return minor;
	}
	
	public String postOrder (Node<K,V> actual, String temp) {
		
        String toString = temp;
        
        if (actual.getLeft() != null) {
        	toString = postOrder(actual.getLeft(), toString);  
        }
        
        if (actual.getRight() != null) {
        	toString = postOrder(actual.getRight(), toString);  
        }
        
        return toString += actual.getKey() + " ";  
	}
	
	@Override
	public int height(Node<K,V> currentNode) {
		
		if(currentNode == null) {
			return 0;
		}
		
		int left = height(currentNode.getLeft());
		int right = height(currentNode.getRight());
		
		if(left > right) {
			return left + 1;
		}else {
			return right + 1;
		}
	}
	
	public Node<K,V> getRoot() {
		return root;
	}
	
	public void setRoot(Node<K,V> root) {
		this.root = root;
	}
}
