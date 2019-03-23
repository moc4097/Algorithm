package com.lanqiao.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
...11111111111111111111111111111
11.111111........1111111111.1111
11.111111..111.11111111.....1111
11.11111111111.11111111111.11111
11.111111..................11111
11.111111.11111111111.11111.1111
11..........111111111.11111.1111
11111.111111111111111.11....1111
11111.111111111111111.11.11.1111
11111.111111111111111.11.11.1111
111...111111111111111.11.11.1111
111.11111111111111111....11.1111
111.11111111111111111111111.1111
111.1111.111111111111111......11
111.1111.......111111111.1111.11
111.1111.11111.111111111.1111.11
111......11111.111111111.1111111
11111111111111.111111111.111...1
11111111111111...............1.1
111111111111111111111111111111..

如上图的迷宫，入口、出口分别：左上角、右下角
“1”是墙壁，“.”是通路
求最短需要走多少步？
 * @author Moc
 *
 */
public class BFS_迷宫 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		int w = 32;
		int h = 20;
		char[][] graph = new char[h][w];
		boolean[][] vis = new boolean[h][w];
		
		for(int i = 0; i < h; i++) {
			graph[i] = scn.nextLine().toCharArray();
			for(int j = 0; j < w; j++) {
				vis[i][j] = false;
			}
		}
		
		bfs(graph, 0, 0, 19, 31, vis);
	}
	
	public static void bfs(char[][] graph, int startX, int startY, int endX, int endY, boolean[][] vis) {
		Graph_Node startNode = new Graph_Node(startX, startY, 0);
		Queue<Graph_Node> q = new LinkedList<Graph_Node>();
		
		int rowLen = graph.length;
		int colLen = graph[0].length;
		
		q.add(startNode);
		while(!q.isEmpty()) {
			Graph_Node cur = q.poll();
			
			int x = cur.i;
			int y = cur.j;
			int depth = cur.depth;
			vis[x][y] = true;
			
			if(x == endX && y == endY) {
				System.out.println(cur.depth);
				break;
			}
			
			//往下走
			if((x + 1) < rowLen && !vis[x + 1][y] && graph[x + 1][y] == '.') {
				q.add(new Graph_Node(x + 1, y, depth + 1));
			}
			
			//往右走
			if((y + 1) < colLen && !vis[x][y + 1] && graph[x][y + 1] == '.') {
				q.add(new Graph_Node(x, y + 1, depth + 1));
			}
			
			//往左走
			if((y - 1) >= 0 && !vis[x][y - 1] && graph[x][y - 1] == '.') {
				q.add(new Graph_Node(x, y - 1, depth + 1));
			}
			
			//往上走
			if((x - 1) >= 0 && !vis[x - 1][y] && graph[x - 1][y] == '.') {
				q.add(new Graph_Node(x - 1, y, depth + 1));
			}
		}
	}
}

class Graph_Node{
	int i;
	int j;
	int depth;
	
	public Graph_Node(int i, int j, int depth) {
		this.i = i;
		this.j = j;
		this.depth = depth;
	}
}
