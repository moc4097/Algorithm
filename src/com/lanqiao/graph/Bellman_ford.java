package com.lanqiao.graph;

import java.util.Arrays;

/**
 * bellman-ford�㷨
 * �������еıߣ��������i���յ�j�����Դ�㵽�������̾���d[i]�Ѿ���������ͱȽ�d[j]��d[i]+cost�����ǰ�߱Ⱥ��ߴ󣬾Ϳ��Ը���d[j]
 * ���������ֱ��û�����ݿ��Ը��£�����Դ�㵽�������̾�����������
 * @author Moc
 *
 */
public class Bellman_ford {
	private static int[][] graph = {
			{0, 2, 5, 0, 0, 0, 0},
			{2, 0, 4, 6, 10, 0, 0},
			{5, 4, 0, 2, 0, 0, 0},
			{0, 6, 2, 0, 0, 1, 0},
			{0, 10, 0, 0, 0, 3, 5},
			{0, 0, 0, 1, 3, 0, 9},
			{0, 0, 0, 0, 5, 9, 0}
	};
	private static int n = 7;
	
	public static int[] shortestPath(int u) {
		int[] d = new int[n];
		for(int i = 0; i < n; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		d[u] = 0;
		while(true) {
			boolean update = false;
			for(int i = 0; i < n; i++) {
				if(d[i] == Integer.MAX_VALUE) {
					continue;
				}
				for(int j = 0; j < n; j++) {
					int cost = graph[i][j];
					if(cost > 0 && d[j] > d[i] + cost) {
						update = true;
						d[j] = d[i] + cost;
					}
				}
			}
			
			if(!update) {
				break;
			}
		}
		return d;
	}
	
	public static void main(String[] args) {
		int[] d = shortestPath(0);
		System.out.println(Arrays.toString(d));
	}
}

