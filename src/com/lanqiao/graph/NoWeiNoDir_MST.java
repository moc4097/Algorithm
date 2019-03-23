package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 无权无向图的最小生成树
 * @author Moc
 *
 */
public class NoWeiNoDir_MST {
	private MST_Vertex[] vertexList;
	private int nVerts;
	private int[][] adjMat;
	private Stack<String> stack;
	private int n;
	
	public NoWeiNoDir_MST(int nVerts) {
		vertexList = new MST_Vertex[nVerts];
		this.nVerts = nVerts;
		stack = new Stack<String>();
		adjMat = new int[nVerts][nVerts];
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				adjMat[i][j] = 0;
			}
		}
		this.n = 0;
	} 
	
	public void addVertex(char ver) {
		vertexList[n++] = new MST_Vertex(ver);
	}
	
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	
	public void mst_dfs(int v) {
		vertexList[v].wasVisited = true;
		for(int i = 0; i < nVerts; i++) {
			if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
				mst_dfs(i);
				stack.push(vertexList[v].ver + "->" + vertexList[i].ver);
			}
		}
	}
	
	public void mst_bfs(int v) {
		vertexList[v].wasVisited = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(v);
		while(!q.isEmpty()) {
			int p = q.peek();
			for(int i = 0; i < nVerts; i++) {
				if(adjMat[p][i] == 1 && vertexList[i].wasVisited == false) {
					q.offer(i);
					vertexList[i].wasVisited = true;
					stack.push(vertexList[p].ver + "->" + vertexList[i].ver);
				}
			}
			q.poll();
		}
	}
	
	public void displayMst() {
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	public static void main(String[] args) {
		NoWeiNoDir_MST mst = new NoWeiNoDir_MST(5);
		mst.addVertex('A');
		mst.addVertex('B');
		mst.addVertex('C');
		mst.addVertex('D');
		mst.addVertex('E');
		
		mst.addEdge(0, 1);
		mst.addEdge(0, 2);
		mst.addEdge(0, 3);
		mst.addEdge(0, 4);
		mst.addEdge(1, 2);
		mst.addEdge(1, 3);
		mst.addEdge(1, 4);
		mst.addEdge(2, 3);
		mst.addEdge(2, 4);
		mst.addEdge(3, 4);
		
		//mst.mst_dfs(0);
		mst.mst_bfs(4);
		mst.displayMst();
	}
}


class MST_Vertex{
	char ver;
	boolean wasVisited;
	
	public MST_Vertex(char ver) {
		this.ver = ver;
		wasVisited = false;
	}
}