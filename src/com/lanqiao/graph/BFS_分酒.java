package com.lanqiao.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * ��4�����ƿ�ӣ����ǵ������ֱ��ǣ�9���� 7���� 4���� 2��
 * ��ʼ��״̬��[9, 0, 0, 0] Ҳ����˵����һ��ƿ�����ţ������Ķ����š�
 * 
 * ����Ѿƴ�һ��ƿ�ӵ�����һ��ƿ�ӣ���ֻ�ܰ�һ��ƿ�ӵ������һ��ƿ�ӵ��գ��������м�״̬��
 * ������һ�ε��ƶ�����Ϊ1�β�����
 * 
 * ����ƿ�ӵ������ͳ�ʼ״̬���䣬���ڸ�����Ŀ��״̬��������Ҫ���ٴβ�������ʵ�֣�
 * �������Ҫ������ʵ����С���������ļ��㡣
 * 
 * ���룺����״̬���ո�ָ���
 * ����� ��С�������������޷�ʵ�֣������-1��
 * 
 * ���磺
 * ���룺
 * 9 0 0 0 
 * Ӧ�������
 * 0
 * 
 * ���룺
 * 6 0 0 3
 * Ӧ�������
 * -1
 * 
 * ���룺
 * 7 2 0 0 
 * Ӧ�������
 * 2
 * @author Moc
 *
 */
public class BFS_�־� {
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
						//���j�ܽ�i�е����оƽ���
						if(j_jiesou > status[i]) {
							status[i] = 0;
							status[j] += temp;
							add(new Cup_Status(intArr2String(status), cur.depth + 1));
							status[i] = temp;
							status[j] -= temp;
						}
					
						//���j���ٽ��վƣ���j_jiesou����0��,��i�еľ��ܰ�j����
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
