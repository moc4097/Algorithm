package com.lanqiao.graph;

import java.util.Arrays;

/**
 * bellman-ford算法
 * 遍历所有的边，边有起点i和终点j，如果源点到顶点的最短距离d[i]已经算出来，就比较d[j]和d[i]+cost，如果前者比后者大，就可以更新d[j]
 * 如此往复，直到没有数据可以更新，这样源点到顶点的最短距离就算出来了
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

