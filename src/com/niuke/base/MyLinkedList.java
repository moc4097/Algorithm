package com.niuke.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList {
	
	private Node first;
	private Node last;
	private int size;
	
	private void checkElementIndex(int index) {
		if (notElementIndex(index)) {
			throw new ArrayIndexOutOfBoundsException("越界或该链表为空！！！");
		}
	}
	
	private boolean notElementIndex(int index) {
		return index < 0 || index >= size;
	}
	
	private void checkPositionIndex(int index) {
		if (notPositionIndex(index)) {
			throw new ArrayIndexOutOfBoundsException("插入位置非法！！！");
		}
	}
	
	private boolean notPositionIndex(int index) {
		return index < 0 || index > size;
	}
	
	private Node node(int index) {
		Node n = null;
		if (index < (size >> 1)) {
			n = first;
			for(int i = 0 ; i < index ; i++) {
				n = n.next;
			}
			return n;
		}else {
			n=last;
			for(int i = size - 1; i > index; i--) {
				n = n.pre;
			}
			return n;
		}
	}
	
	private void linkLast(Object obj) {
		Node l = last;
		Node newNode = new Node(l,obj,null);
		last=newNode;
		
		if (l == null) {
			first = newNode;
		}else {
			l.next = newNode;
		}
		
		size++;
	}
	
	private void linkBefore(Object obj, Node node) {
		Node prev = node.pre;
		Node newNode = new Node(prev, obj, node);
		node.pre = newNode;
		if (prev == null) {
			first = newNode;
		}else {
			prev.next = newNode;
		}
		size++;
	}
	
	private Object unlink(Node n) {
		Object obj = n.obj;
		Node prev = n.pre;
		Node next = n.next;
		
		if (prev == null) {
			first = next;
		}else {
			prev.next = next;
			n.pre = null;
		}
		if (next == null) {
			last = prev;
		}else {
			next.pre = prev;
			n.next = null;
		}
		size--;
		n.obj = null;
		
		return obj;
	}
	
	public boolean add(Object obj) {
		linkLast(obj);
		return true;
	}
	
	public int size() {
		return this.size;
	}
	
	public Object get(int index) {
		checkElementIndex(index);
		
		Node n = node(index);
		return n.obj;
	}
	
	public void addFirst(Object obj) {
		Node f = first;
		Node newNode = new Node(null, obj, f);
		first = newNode;
		if (f == null) {
			last = newNode;
		}else {
			f.pre = newNode;
		}
		size++;
	}
	
	public void addLast(Object obj) {
		linkLast(obj);
	}
	
	public void add(int index, Object obj) {
		checkPositionIndex(index);
		if (index == size) {
			linkLast(obj);
		}else {
			linkBefore(obj, node(index));
		}
	}
	
	public Object removeFirst() {
		Node f = first;
		if (f == null) {
			throw new NoSuchElementException();
		}
		Object obj = f.obj;
		Node next = f.next;
		f.next =null;
		f.obj =null;
		first = next;
		if (next == null) {
			last = null;
		}else {
			next.pre = null;
		}
		size--;
		return obj;
	}
	
	public Object removeLast() {
		Node l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		Object obj = l.obj;
		Node prev = l.pre;
		l.pre = null;
		l.obj = null;
		last = prev;
		if (prev == null) {
			first = null;
		}else {
			prev.next = null;
		}
		size --;
		return obj;
	}
	
	public Object remove(int index) {
		checkElementIndex(index);
		
		Node n = node(index);
		Object obj = unlink(n); 
		
		return obj;
	}
	
	public boolean remove(Object obj) {
		
		if (obj == null) {
			for(Node f = first; f != null; f = f.next) {
				if (f.obj == obj) {
					unlink(f);
					return true;
				}
			}
		}else {
			for(Node f = first; f != null; f = f.next) {
				if(obj.equals(f.obj)) {
					unlink(f);
					return true;
				}
			}
		}
		return false;
	}
	
	public Object getFirst() {
		Node f = first;
		if (f == null) {
			throw new NoSuchElementException();
		}
		return f.obj;
	}
	
	public Object getLast() {
		Node l = last;
		if (l == null) {
			throw new NoSuchElementException();
		}
		return l.obj;
	}
	
	public int indexOf(Object obj) {
		int index = 0;
		if (obj == null) {
			for(Node f = first; f != null; f = f.next) {
				if (obj == f.obj) {
					return index;
				}
				index++;
			}
		}else {
			for(Node f = first; f != null; f = f.next) {
				if (obj.equals(f.obj)) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}
	
	public int lastIndexOf(Object obj) {
		int index = size;
		if (null == obj) {
			for(Node l = last; l != null; l = l.pre) {
				index--;
				if (obj == l.obj) {
					return index;
				}
			}
		}else {
			for(Node l = last; l != null; l = l.pre) {
				index--;
				if (obj.equals(l.obj)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	public Object set(int index, Object obj) {
		checkElementIndex(index);
		Node n = node(index);
		Object o = n.obj; 
		n.obj = obj;
		return o;
	}
	
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	public Iterator iterator() {
		return new Iterator() {

			private Node f = first;
			
			@Override
			public boolean hasNext() {
				return f != null;
			}

			@Override
			public Object next() {
				Object obj = f.obj;
				f = f.next;
				return obj;
			}
			
		};
	}
	
	
	
	private static class Node {
		Node pre;
		Object obj;
		Node next;
		
		public Node() {}
		
		public Node(Node pre, Object obj, Node next) {
			this.pre = pre;
			this.obj = obj;
			this.next = next;
		}
		
	}
	
	
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.add("adsa");
		list.add("qwe");
		list.add("zcxz");
		list.addFirst("first");
		list.add(1, "charu1");
		list.addLast("last");
		list.removeLast();
		list.remove(0);
		list.remove("zcxz");
		System.out.println(list.size());
		Iterator iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}
}


