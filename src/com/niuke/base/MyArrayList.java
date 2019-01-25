package com.niuke.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyArrayList {

	private Object[] elementData;
	private int size;
	private final static int DEFAULT_CAPACITY=10;
	private final static Object[] EMPTY_CAPACITY= {};
	
	public MyArrayList() {
		this(10);
	}
	
	public MyArrayList(int initialCapacity) {
		if(initialCapacity<0) {
			throw new IllegalArgumentException("illegal initialCapacity:"+initialCapacity);
		}else if(initialCapacity==0){
			this.elementData=EMPTY_CAPACITY;
		}else {
			this.elementData=new Object[initialCapacity];
		}
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(this.elementData, this.size);
	}
	
	public Iterator iterator() {
		class MyIterator implements Iterator{

			private int index=0;
			
			@Override
			public boolean hasNext() {
				return index<size;
			}

			@Override
			public Object next() {
				return elementData[index++];
			}
			
		}
		
		return new MyIterator();
	}
	
	public void add(Object obj) {
		ensureCapacity(size+1);
		this.elementData[size++]=obj;
	}
	
	public void add(int index,Object obj) {
		rangeCheck(index);
		ensureCapacity(size+1);
		
		System.arraycopy(this.elementData, index, this.elementData, index+1, this.size-index);
		this.elementData[index]=obj;
		size++;
	}
	
	public Object get(int index) {
		rangeCheck(index);
		return getElement(index);
	}
	
	public Object set(int index,Object obj) {
		rangeCheck(index);
		Object oldValue=get(index);
		this.elementData[index]=obj;
		return oldValue;
	}
	
	public Object remove(int index) {
		rangeCheck(index);
		Object oldValue=get(index);
		int numMoved=this.size-1-index;
		if(numMoved>0) {
			System.arraycopy(this.elementData, index+1, this.elementData, index, numMoved);
		}
		this.elementData[--this.size]=null;
		return oldValue;
	}
	
	public boolean remove(Object obj) {
		if(null==obj) {
			for(int i=0;i<size;i++) {
				if(this.elementData[i]==null) {
					remove(i);
					return true;
				}
			}
		}else {
			for(int i=0;i<size;i++) {
				if(obj.equals(this.elementData[i])) {
					remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public void clear() {
		for(int i=0;i<size;i++) {
			this.elementData[i]=null;
		}
		this.size=0;
	}
	
	public int indexOf(Object obj) {
		if(null==obj) {
			for(int i=0;i<size;i++) {
				if(this.elementData[i]==null) {
					return i;
				}
			}
		}else {
			for(int i=0;i<size;i++) {
				if(obj.equals(this.elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean contains(Object obj) {
		return indexOf(obj)==-1?false:true;
	}
	
	
	private Object getElement(int index) {
		return this.elementData[index];
	}
	
	private void rangeCheck(int index) {
		if(index<0||index>=size) {
			throw new ArrayIndexOutOfBoundsException("数组越界！");
		}
	}
	
	private void ensureCapacity(int miniCapacity) {
		if(this.elementData==EMPTY_CAPACITY) {
			miniCapacity=Math.max(DEFAULT_CAPACITY, miniCapacity);
		}
		if(miniCapacity>this.elementData.length) {
			grow(miniCapacity);
		}
	}
	
	private void grow(int miniCapacity) {
		int oldLength=this.elementData.length;
		int newLength=oldLength+(oldLength>>1);
		if(newLength<miniCapacity) {
			newLength=miniCapacity;
		}
		this.elementData=Arrays.copyOf(elementData, newLength);
	}
	
	public boolean isEmpty() {
		if(size==0) {
			return true;
		}
		return false;
	}
	
	public int size() {
		return this.size;
	}
	
	public static void main(String[] args) {
		MyArrayList list=new MyArrayList(3);
		list.add("111");
		list.add("222");
		list.add("333");
		System.out.println(list.size());
		System.out.println(list.remove(1));
		System.out.println(list.size());
		System.out.println(list.indexOf("333"));
		
	}
}
