package com.lanqiao.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * 有4个红酒瓶子，它们的容量分别是：9升， 7升， 4升， 2升
 * 开始的状态是[9, 0, 0, 0] 也就是说：第一个瓶子满着，其他的都空着。
 * 
 * 允许把酒从一个瓶子倒入另一个瓶子，但只能把一个瓶子倒满或把一个瓶子倒空，不能有中间状态。
 * 这样的一次倒酒动作称为1次操作。
 * 
 * 假设瓶子的容量和初始状态不变，对于给定的目标状态，至少需要多少次操作才能实现？
 * 本题就是要求你编程实现最小操作次数的计算。
 * 
 * 输入：最终状态（空格分隔）
 * 输出： 最小操作次数（如无法实现，则输出-1）
 * 
 * 例如：
 * 输入：
 * 9 0 0 0 
 * 应该输出：
 * 0
 * 
 * 输入：
 * 6 0 0 3
 * 应该输出：
 * -1
 * 
 * 输入：
 * 7 2 0 0 
 * 应该输出：
 * 2
 * @author Moc
 *
 */
public class BFS_分酒 {
	public static void main(String[] args) {
		int[] v = {9, 7, 4, 2};
		
		String startVal = "9 0 0 0";
		Cup_Status startStatus = new Cup_Status(startVal, 0);
		
		Scanner scn = new Scanner(System.in);
		while(scn.hasNextLine()) {
			String finalVal = scn.nextLine();
			Cup_Status finalStatus = new Cup_Status(finalVal);
			
			int res = 0;
			boolean flag = true;
			int[] status = finalStatus.getStatus();
			
			for(int i = 0; i < v.length; i++) {
				if(v[i]  < status[i]) {
					flag = false;
					break;
				}
			}
			
			if(!flag) {
				System.out.println(-1);
			}else {
				Cup_Wine w = new Cup_Wine(startStatus, finalStatus, v);
				res = w.bfs();
				System.out.println(res);
			}
		}
	}
}

class Cup_Wine{
	private Cup_Status startStatus;
	private Cup_Status finalStatus;
	private Queue<Cup_Status> queue;
	private Set<Cup_Status> s;
	private int[] cup;
	
	
	public Cup_Wine(Cup_Status startStatus, Cup_Status finalStatus, int[] cup) {
		this.startStatus = startStatus;
		this.finalStatus = finalStatus;
		this.queue = new LinkedList<Cup_Status>();
		this.s = new HashSet<Cup_Status>();
		
		add(startStatus);
		
		this.cup = new int[cup.length];
		System.arraycopy(cup, 0, this.cup, 0, cup.length);
		
	}
	
	public void add(Cup_Status status) {
		if(!s.contains(status)) {
			s.add(status);
			queue.add(status);
		}
	}
	
	public int bfs() {
		while(!queue.isEmpty()) {
			Cup_Status cur = queue.poll();
			if(cur.equals(finalStatus)) {
				return cur.depth;
			}
			
			int[] status = cur.getStatus();
			for(int i = 0; i < status.length; i++) {
				if(status[i] > 0) {
					for(int j = 0; j < status.length; j++) {
						if(i == j) {
							continue;
						}
						int j_jiesou = cup[j] - status[j];
						int temp = status[i];
						//如果j能将i中的所有酒接收
						if(j_jiesou > status[i]) {
							status[i] = 0;
							status[j] += temp;
							add(new Cup_Status(intArr2String(status), cur.depth + 1));
							status[i] = temp;
							status[j] -= temp;
						}
					
						//如果j能再接收酒（即j_jiesou大于0）,且i中的酒能把j倒满
						if(j_jiesou > 0 && temp >= j_jiesou) {
							status[i] -= j_jiesou;
							status[j] += j_jiesou;
							add(new Cup_Status(intArr2String(status), cur.depth + 1));
							status[i] += j_jiesou;
							status[j] -= j_jiesou;
						}
					}
				}
			}
		}
		return -1;
	}
	
	public String intArr2String(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.length - 1; i++) {
			sb.append(arr[i] + " ");
		}
		sb.append(arr[arr.length - 1]);
		return sb.toString();
	}
}

class Cup_Status{
	String val;  //9 0 0 0
	int depth;
	
	public Cup_Status(String val) {
		this.val = val;
	}
	
	public Cup_Status(String val, int depth) {
		this.val = val;
		this.depth = depth;
	}
	
	public int[] getStatus() {
		String[] s = val.split(" ");
		int[] res = new int[s.length];
		for(int i = 0; i < s.length; i++) {
			res[i] = Integer.parseInt(s[i]);
		}
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		
		Cup_Status node = (Cup_Status) obj;
		return val.equals(node.val);
	}

	@Override
	public int hashCode() {
		return val.hashCode();
	}
	
	
}
