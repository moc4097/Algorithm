package com.niuke.base;


public class Array_to_Stack_Queue {
	public static class ArrayStack{
		private Integer[] arr; 
		private Integer index;
		
		public ArrayStack(int initsize) {
			if(initsize<0) {
				throw new IllegalArgumentException("The init size less than 0");
			}
			arr=new Integer[initsize];
			index=0;
		}
		
		public Integer peek() {
			if(index==0) {
				return null;
			}
			return arr[index-1];
		}
		
		public void push(int num) {
			if(index==arr.length) {
				throw new ArrayIndexOutOfBoundsException("The Stack is full");
			}
			arr[index++]=num;
		} 
		
		public Integer pop() {
			if(index==0) {
				throw new ArrayIndexOutOfBoundsException("The Stack is empty");
			}
			return arr[--index];
		}
	}
	
	public static class ArrayQueue{
		private Integer size;
		private Integer start;
		private Integer end;
		private Integer[] arr;
		
		public ArrayQueue(int initsize) {
			if(initsize<0) {
				throw new IllegalArgumentException("The init size less than 0");
			}
			arr=new Integer[initsize];
			size=0;
			start=0;
			end=0;
		}
		
		public Integer peek() {
			if(size==0) {
				return null;
			}
			return arr[start];
		}
		
		public void push(int num) {
			if(size==arr.length) {
				throw new ArrayIndexOutOfBoundsException("The Queue is full");
			}
			size++;
			arr[end]=num;
			end=nextIndex(arr.length,end);
		}
		
		public Integer poll() {
			if(size==0) {
				throw new ArrayIndexOutOfBoundsException("The Queue is empty");
			}
			size--;
			int s=start;
			start=nextIndex(arr.length,start);
			return arr[s];
		}
		
		public Integer nextIndex(int size,int index) {
			return index==size-1?0:index+1;
		}
	}
	
	public static void testQueue() {
		ArrayQueue queue=new ArrayQueue(3);
		queue.push(3);
		queue.push(1);
		queue.push(2);
		System.out.println(queue.poll());
		System.out.println(queue.peek());
	}
	
	public static void main(String[] args) {
		ArrayStack stack=new ArrayStack(3);
		stack.push(3);
		stack.push(1);
		stack.push(2);
		//stack.push(4);
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		
	}
}
