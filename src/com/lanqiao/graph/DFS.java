package com.lanqiao.graph;

import java.util.Stack;

public class DFS {
	private Vertex[] vertexList;
	private static final int MAX_SIZE = 20;
	private int nVerts;
	private int[][] adjMat;  //adjacency matrix
	private Stack<Integer> stack;
	
	public DFS() {
		vertexList = new Vertex[MAX_SIZE];
		nVerts = 0;
		adjMat = new int[MAX_SIZE][MAX_SIZE];
		for(int i = 0; i < MAX_SIZE; i++) {
			for(int j = 0; j < MAX_SIZE; j++) {
				adjMat[i][j] = 0;
			}
		}
		stack = new Stack<Integer>();
	}
	
	public void addVertex(char ver) {
		vertexList[nVerts++] = new Vertex(ver);
	}
	
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	
	public void displayVertex(int i) {
		System.out.print(vertexList[i].ver);
	}
	
	public void dfs(int v) {
		displayVertex(v);
		vertexList[v].visited = true;
		for(int j = 0; j < nVerts; j++) {
			if(adjMat[v][j] == 1 && vertexList[j].visited == false) {
				dfs(j);
			}
		}
	}
	
	public void dfs() {
		displayVertex(0);
		vertexList[0].visited = true;
		stack.push(0);
		while(!stack.isEmpty()) {
			int v = getAdjUnvisitedVertex(stack.peek());
			if(v == -1) {
				stack.pop();
			}else {
				vertexList[v].visited = true;
				displayVertex(v);
				stack.push(v);
			}
			
		}
		//initial attribute visited
		for(int i = 0; i < nVerts; i++) {
			vertexList[i].visited = false;
		}
	}
	
	private int getAdjUnvisitedVertex(int v) {
		for(int j = 0; j < nVerts; j++) {
			if(adjMat[v][j] == 1 && vertexList[j].visited == false) {
				return j;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		DFS dfs= new DFS();
		dfs.addVertex('A');
		dfs.addVertex('B');
		dfs.addVertex('C');
		dfs.addVertex('D');
		dfs.addVertex('E');
		
		dfs.addEdge(0, 1);
		dfs.addEdge(1, 2);
		dfs.addEdge(0, 3);
		dfs.addEdge(3, 4);
		
		System.out.print("visit:");
		//dfs.dfs(0);
		dfs.dfs();
	}
	
}


class Vertex{
	public char ver;
	public boolean visited;
	
	public Vertex(char ver) {
		this.ver = ver;
		visited = false;
	}
	
	
}