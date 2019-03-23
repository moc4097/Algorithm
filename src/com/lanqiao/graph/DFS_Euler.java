package com.lanqiao.graph;

import java.util.Stack;

/**
 * 1���Ƿ����ŷ����·
 * 2����ӡŷ����·
 * @author Moc
 *
 */
public class DFS_Euler {
	private static Stack<String> path = new Stack<String>();
	private static final int[][] graph = {
			{0 , 1 , 2 , 1},	
			{1 , 0 , 0 , 0},
			{2 , 0 , 0 , 1},
			{1 , 0 , 1 , 0}
	};
	
	private static final int size = 4;   //������
	private static int[][] vis = new int[size][size];
	
	/**
	 * 
	 * @param u �������ڷ��ʵĶ���
	 */
	public static void euler(int u) {
		for(int v = 0; v < size; v++) {
			if(graph[u][v] > 0 && vis[u][v] < graph[u][v]) {
				vis[u][v]++;
				vis[v][u]++;
				euler(v);
				path.push((char)('A' + u) + "->" + (char)('A' + v));
			}
		}
	}
	
	public static boolean hasEuler() {
		int[] degree = new int[size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				degree[i] += graph[i][j];
			}
		}
		int cnt = 0; //������¼���ĸ���
		for(int i = 0; i < size; i++) {
			if((degree[i] & 1) == 1) {
				cnt++;
			}
		}
		
		if(cnt <= 2) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		System.out.println("���ͼ�Ƿ����ŷ����·��" + hasEuler());
		euler(0);
		while(!path.isEmpty()) {
			System.out.println(path.pop());
		}
	}
}
