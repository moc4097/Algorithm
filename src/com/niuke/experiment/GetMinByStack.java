package com.niuke.experiment;

import java.util.Stack;

public class GetMinByStack {
	
	public static class MyStack{
		private Stack<Integer> stackData;
		private Stack<Integer> stackMins;
		
		public MyStack() {
			stackData=new Stack<Integer>();
			stackMins=new Stack<Integer>();
		}
		
		public void push(int num) {
			if(stackData.isEmpty()) {
				stackMins.push(num);
			}else if(num<stackMins.peek()) {
				stackMins.push(num);
			}else {
				stackMins.push(stackMins.peek());
			}
			stackData.push(num);
		}
		
		public Integer pop() {
			if(stackData.isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("The Stack is empty");
			}
			stackMins.pop();
			return stackData.pop();
		}
		
		public Integer getMin() {
			if(stackData.isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("The Stack is empty");
			}
			return stackMins.peek();
		}
	}
	
	/**
	 * ¼õÐ¡¿Õ¼ä¸´ÔÓ¶È
	 * @author Moc
	 *
	 */
	public static class MyStack2{
		private Stack<Integer> stackData;
		private Stack<Integer> stackMins;
		
		public MyStack2() {
			stackData=new Stack<Integer>();
			stackMins=new Stack<Integer>();
		}
		
		public void push(int num) {
			if(stackData.isEmpty()) {
				stackMins.push(num);
			}else if(num<=stackMins.peek()) {
				stackMins.push(num);
			}
			stackData.push(num);
		}
		
		public Integer pop() {
			if(stackData.isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("The Stack is empty");
			}
			if(stackData.peek()==stackMins.peek()) {
				stackMins.pop();
			}
			return stackData.pop();
		}
		
		public Integer getMin() {
			if(stackData.isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("The Stack is empty");
			}
			return stackMins.peek();
		}
	}
	
	public static void main(String[] args) {
		MyStack2 stack=new MyStack2();
		stack.push(3);
		stack.push(1);
		stack.push(2);
		stack.pop();
		//stack.pop();
		System.out.println(stack.getMin());
	}
}
