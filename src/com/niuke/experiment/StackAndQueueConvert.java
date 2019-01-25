package com.niuke.experiment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 栈结构和队列结构互相转换
 * @author Moc
 *
 */
public class StackAndQueueConvert {
	
	/**
	 * 使用两个队列实现栈结构
	 * @author Moc
	 *
	 */
	public static class TwoQueueStack{
		private Queue<Integer> queue;
		private Queue<Integer> help;
		
		public TwoQueueStack() {
			queue=new LinkedList<Integer>();
			help=new LinkedList<Integer>();
		}
		
		public int peek() {
			if(queue.isEmpty()) {
				throw new RuntimeException("The Stack is empty");
			}
			while(queue.size()!=1) {
				help.offer(queue.poll());
			}
			int res=queue.poll();
			help.offer(res);
			swap();
			return res;
		}
		
		public void push(Integer num) {
			queue.offer(num);
		}
		
		public int pop() {
			if(queue.isEmpty()) {
				throw new RuntimeException("The Stack is empty");
			}
			while(queue.size()!=1) {
				help.offer(queue.poll());
			}
			int res=queue.poll();
			swap();
			return res;
		}
		
		public void swap() {
			Queue<Integer> temp=queue;
			queue=help;
			help=temp;
		}
	}
	
	public static class TwoStackQueue{
		private Stack<Integer> push;
		private Stack<Integer> pop;
		
		public TwoStackQueue() {
			push=new Stack<Integer>();
			pop=new Stack<Integer>();
		}
		
		public int peek() {
			if(push.empty()&&pop.empty()) {
				throw new RuntimeException("The Queue is empty");
			}
			dao();
			return pop.peek();
		}
		
		public void offer(int num) {
			push.push(num);
		}
		
		public int poll() {
			if(push.empty()&&pop.empty()) {
				throw new RuntimeException("The Queue is empty");
			}
			dao();
			return pop.pop();
		}
		
		public void dao() {
			if(pop.isEmpty()) {
				while(!push.isEmpty()) {
					pop.push(push.pop());
				}
			}
		}
	}
	
	public static void testStack() {
		TwoQueueStack stack=new TwoQueueStack();
		stack.push(3);
		stack.push(1);
		stack.push(2);
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
	}
	
	public static void main(String[] args) {
		TwoStackQueue queue=new TwoStackQueue();
		queue.offer(3);
		queue.offer(1);
		queue.offer(2);
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.peek());
	}
}
