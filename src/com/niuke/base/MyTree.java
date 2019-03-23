package com.niuke.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyTree<E> {
	private int size;
	private TreeNode<E> root;
	
	public MyTree(TreeNode<E> root) {
		this.root = root;
		size++;
	}
	
	public int getSize() {
		return size; 
	}
	
	public TreeNode<E> getRoot(){
		return this.root;
	}
	
	public TreeNode<E> getParent(TreeNode<E> x){
		return x.parent;
	}
	
	public TreeNode<E> getFirstChild(TreeNode<E> x){
		return x.childrens.get(0);
	}
	
	public TreeNode<E> getNextSibling(TreeNode<E> x){
		int i = x.parent.childrens.indexOf(x);
		int childSize = x.parent.childrens.size();
		if(i == (childSize - 1)) {
			return null;
		}else {
			return (TreeNode<E>) x.parent.childrens.get(i+1);
		}
	}
	
	public int getHeight(TreeNode<E> x) {
		List<TreeNode<E>> l = x.childrens;
		if(null == l) {
			return 0;
		}else {
			int h = 0;
			for(int i = 0; i < l.size(); i++) {
				h = Math.max(h, getHeight(l.get(i)));
			}
			return h + 1;
		}
	}
	
	public void insertChild(TreeNode<E> x, TreeNode<E> child) {
		if(null == x.childrens) {
			x.childrens = new ArrayList<TreeNode<E>>();
		}
		x.childrens.add(child);
		child.parent = x;
		size++;
	}
	
	public void deleteChild(TreeNode<E> x, int i) {
		x.childrens.remove(i);
		size--;
	}
	
	public void preOrder(TreeNode<E> x){
		if(null == x) {
			return ;
		}
		System.out.print(x.data+"    ");
		for(int i = 0; i < x.childrens.size(); i++) {
			preOrder(x.childrens.get(i));
		}
	}
	
	public void postOrder(TreeNode<E> x) {
		if(null == x) {
			return;
		}
		for(int i = 0; i < x.childrens.size(); i++) {
			postOrder(x.childrens.get(i));
		}
		System.out.println(x.data+"    ");
	}
	
	public List<List<TreeNode<E>>> levelOrder(TreeNode<E> x){
		List<List<TreeNode<E>>> res = new ArrayList<List<TreeNode<E>>>();
		Queue<TreeNode<E>> q = new LinkedList<TreeNode<E>>();
		q.add(x);
		List<TreeNode<E>> l = new ArrayList<TreeNode<E>>();
		TreeNode<E> last = x;
		TreeNode<E> nlast = null;
		res.add(l);
		while(!q.isEmpty()) {
			TreeNode<E> p = q.peek();
			if(p.childrens != null) {
				for(TreeNode<E> node : p.childrens) {
					q.add(node);
					nlast = node;   
				}
			}
			l.add(q.poll());
			if(!q.isEmpty() && last == p) {
				l = new ArrayList<TreeNode<E>>();
				res.add(l);
				last = nlast;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		MyTree<String> tree = new MyTree<String>(new TreeNode<String>("a"));
		TreeNode<String> root = tree.getRoot();
		
		TreeNode<String> b = new TreeNode<String>("b");
		tree.insertChild(root, b);
		TreeNode<String> c = new TreeNode<String>("c");
		tree.insertChild(root, c);
		TreeNode<String> d = new TreeNode<String>("d");
		tree.insertChild(root, d);
		
		TreeNode<String> e = new TreeNode<String>("e");
		tree.insertChild(b, e);
		TreeNode<String> f = new TreeNode<String>("f");
		tree.insertChild(b, f);
		TreeNode<String> g = new TreeNode<String>("g");
		tree.insertChild(c, g);
		TreeNode<String> h = new TreeNode<String>("h");
		tree.insertChild(d, h);
		
		TreeNode<String> i = new TreeNode<String>("i");
		tree.insertChild(e, i);
		tree.insertChild(i, new TreeNode("j"));
		
		System.out.println(tree.getSize());
		System.out.println(tree.getHeight(root));
		System.out.println(tree.getNextSibling(c));
		
		List<List<TreeNode<String>>> list = tree.levelOrder(root);
		System.out.println(list.size());
		for(List<TreeNode<String>> temp : list) {
			for(TreeNode<String> node : temp) {
				System.out.print(node.data+"    ");
			}
			System.out.println();
		}
	}
}


class TreeNode<E>{
	public E data;
	public TreeNode parent;
	public List<TreeNode<E>> childrens;
	
	public TreeNode(E data) {
		this.data = data;
	}

	public TreeNode(E data, TreeNode parent) {
		this.data = data;
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "TreeNode [data=" + data + "]";
	}
	
	
}