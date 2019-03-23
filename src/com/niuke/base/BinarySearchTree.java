package com.niuke.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<K extends Comparable<K>,V> {
	private int size;
	private Node<K,V> root;
	
	public BinarySearchTree() {
	}
	
	public Node<K,V> insert(K key, V value){
		if(!(key instanceof Comparable)) {
			throw new ClassCastException();
		}
		
		Node<K,V> parent = null;
		Node<K,V> curr = root;
		while(curr != null) {
			parent = curr;
			if(compare(key, curr.key) < 0) {
				curr = curr.left;
			}else if(compare(key, curr.key) > 0) {
				curr = curr.right;
			}else {
				curr.value = value;
				return curr;
			}
		}
		curr = new Node<K,V>(key, value, null, null, null);
		curr.parent = parent;
		if(null == parent) {
			root = curr;
		}else if(compare(key, parent.key) < 0) {
			parent.left = curr;
			curr.isLeftChild = true;
		}else {
			parent.right = curr;
			curr.isLeftChild = false;
		}
		
		size++;
		updateHeight(curr);
		return curr;
	}
	
	private void updateHeight(Node<K, V> node) {
		if(null == node.parent) {
			return;
		}
		Node<K, V> parent = node.parent;
		if(parent.height == node.height) {
			parent.height++;
			updateHeight(parent);
		}		
	}
	
	private int compare(K key1, K key2) {
		return key1.compareTo(key2);
	}
	
	public void inorder() {
		if(root != null) {
			inorder(root);
		}
	}
	
	protected void inorder(Node<K, V> n) {
		if(n != null) {
			inorder(n.left);
			System.out.println(n.key);
			inorder(n.right);
		}
	}
	
	public V lookupValue(K key) {
		Node<K, V> lookupnode = lookupNode(key);
		return lookupnode == null ? null : lookupnode.value;
	}
	
	protected Node<K, V> lookupNode(K key){
		Node<K, V> n = root;
		while(n != null && compare(key, n.key) != 0) {
			if(compare(key, n.key) < 0) {
				n = n.left;
			}else {
				n = n.right;
			}
		}
		return n;
	}
	
	public K min() {
		return minNode(root).key;
	}
	
	protected Node<K, V> minNode(Node<K, V> n){
		while(n.left != null) {
			n = n.left;
		}
		return n;
	}
	public K max() {
		return maxNode(root).key;
	}
	
	protected Node<K, V> maxNode(Node<K, V> n){
		while(n.right != null) {
			n = n.right;
		}
		return n;
	}
	public void remove(K key) {
		Node<K, V> n = lookupNode(key);
		removeNode(n);
		size--;
	}
	
	protected void removeNode(Node<K, V> n) {
		if(n != null) {
			if(n.left == null && n.right == null) {
				if(n.isLeftChild) {
					n.parent.left = null;
				}else {
					if(n.parent != null) {
						n.parent.right = null;
					}else {
						root = null;
					}
				}
				n = null;
			}else if(n.left == null) {
				Node<K, V> parent = n.parent;
				Node<K, V> child = n.right;
				if(n.isLeftChild) {
					parent.left = child;
					child.parent = parent;
					child.isLeftChild = true;
				}else {
					if(n.parent != null) {
						parent.right = child;
						child.parent = parent;
					}else {
						root = child;
					}
				}
				n = null;
			}else if(n.right == null) {
				Node<K, V> parent = n.parent;
				Node<K, V> child = n.left;
				if(n.isLeftChild) {
					parent.left = child;
					child.parent = parent;
				}else {
					if(n.parent != null) {
						parent.right = child;
						child.parent = parent;
						child.isLeftChild = false;
					}else {
						root = child;
					}
				}
				n = null;
			}else {
				Node<K, V> minOfRight = minNode(n.right);
				n.key = minOfRight.key;
				n.value = minOfRight.value;
				removeNode(minOfRight);
			}
		}
	} 
	
	/**
	 * ºó¼Ì
	 * @param key
	 * @return
	 */
	public K successor(K key) {
		Node<K, V> n = lookupNode(key);
		if(n == null) {
			return null;
		}
		if(n.right != null) {
			return minNode(n.right).key;
		}else {
			while(!n.isLeftChild) {
				n = n.parent;
			}
			return n.parent == null ? null : n.parent.key;
		}
	}
	public K predecessor(K key) {
		Node<K, V> n = lookupNode(key);
		if(n == null) {
			return null;
		}
		if(n.left != null) {
			return maxNode(n.left).key;
		}else {
			while(n.isLeftChild) {
				n = n.parent;
			}
			return n.parent == null ? null : n.parent.key;
		}
	}
	
	public List<List<Node<K,V>>> levelOrder(){
		List<List<Node<K, V>>> res = new ArrayList<List<Node<K, V>>>();
		Queue<Node<K, V>> q = new LinkedList<Node<K,V>>();
		List<Node<K, V>> l = new ArrayList<Node<K,V>>();
		q.add(root);
		res.add(l);
		Node<K, V> last = root;
		Node<K, V> nlast = null;
		while(!q.isEmpty()) {
			Node<K, V> p = q.peek();
			if(p.left != null) {
				q.add(p.left);
				nlast = p.left;
			}
			if(p.right != null) {
				q.add(p.right);
				nlast = p.right;
			}
			l.add(q.poll());
			if(!q.isEmpty() && last == p) {
				l = new ArrayList<Node<K,V>>();
				res.add(l);
				last = nlast;
			}
		}
		return res;
	}
	
	public int getSize() {
		return this.size;
	}
	public int getHeight() {
		if(null != root) {
			return getHeight(root);
		}
		return 0;
	}
	
	public int getHeight(Node<K, V> n) {
		if(null == n) {
			return 0;
		}
		int left = getHeight(n.left);
		int right = getHeight(n.right);
		return Math.max(left, right) + 1;
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
		bst.insert(4, "4");
		bst.insert(1, "1");
		bst.insert(10, "10");
		bst.insert(14, "14");
		bst.insert(7, "7");
		bst.insert(16, "16");
		bst.insert(9, "9");
		bst.insert(3, "3");
		bst.insert(5, "5");
		bst.insert(2, "2");
		bst.insert(20, "20");
		bst.insert(25, "25");
		//System.out.println(bst.getHeight());
		//System.out.println(bst.getSize());
		/*bst.inorder();
		System.out.println("===============================");
		//System.out.println(bst.lookupValue(2));
		//System.out.println(bst.max());
		//System.out.println(bst.getHeight());
		bst.remove(10);
		bst.inorder();
		System.out.println("===============================");
		bst.remove(4);
		bst.inorder();*/
		
		/*System.out.println(bst.successor(9));
		System.out.println(bst.predecessor(5));*/
		
		for(List<Node<Integer, String>> list : bst.levelOrder()) {
			for(Node<Integer, String> node : list) {
				System.out.print(node.key+"\t");
			}
			System.out.println();
		}
	}
}

class Node<K,V>{
	public K key;
	public V value;
	public Node<K,V> parent;
	public Node<K,V> left;
	public Node<K,V> right;
	public boolean isLeftChild;
	public int height;
	public int num;
	
	public Node(K key, V value, Node<K, V> parent, Node<K, V> left, Node<K, V> right) {
		super();
		this.key = key;
		this.value = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public boolean isLeft() {
		return isLeftChild;
	}
	
	public boolean isRight() {
		return !isLeftChild;
	}

	@Override
	public String toString() {
		return "Node [key=" + key + "]";
	}

}
