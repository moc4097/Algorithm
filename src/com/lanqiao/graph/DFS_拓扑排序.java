package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.List;

public class DFS_拓扑排序 {
	private List<Topo_Vertex> vertexList;
	private int nVerts;
	private int[] topo;
	private int[][] adjMat;
	public int len;
	
	public DFS_拓扑排序(int nVerts) {
		this.vertexList = new ArrayList<Topo_Vertex>(nVerts);
		this.nVerts = nVerts;
		this.len = nVerts;
		this.topo = new int[nVerts];
		this.adjMat = new int[nVerts][nVerts];
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				adjMat[i][j] = 0;
			}
		}
	}
	
	public void displayAdjMat() {
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				System.out.print(adjMat[i][j]);
			}
			System.out.println();
		}
	}
	
	public void addVertex(char ver) {
		vertexList.add(new Topo_Vertex(ver));
	}
	
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
	}
	
	public void displayTopo() {
		for(int i = 0; i < len; i++) {
			System.out.print(vertexList.get(topo[i]).ver+"   ");
		}
		System.out.println();
	}
	
	public boolean topo(int v) {
		Topo_Vertex ver = vertexList.get(v);
		ver.visited = -1;
		for(int i = 0; i < len; i++ ) {
			if(adjMat[v][i] == 1) {
				if(vertexList.get(i).visited < 0) {
					return false;
				}
				if(vertexList.get(i).visited == 0 && !topo(i)) {
					return false;
				}
			}
		}
		ver.visited = 1;
		topo[--nVerts] = v;
		return true;
	}
	
	public static void main(String[] args) {
		DFS_拓扑排序 tp = new DFS_拓扑排序(5);
		tp.addVertex('A');
		tp.addVertex('B');
		tp.addVertex('C');
		tp.addVertex('D');
		tp.addVertex('E');
		
		
		tp.addEdge(0, 1);
		tp.addEdge(0, 2);
		tp.addEdge(1, 2);
		tp.addEdge(1, 3);
		tp.addEdge(2, 3);
		tp.addEdge(2, 4);
		
		for(int i = 0; i < tp.len; i++) {
			if(tp.vertexList.get(i).visited == 1) {
				continue;
			}
			if(!tp.topo(i)) {
				System.out.println(false);
				return;
			}
		}
		tp.displayTopo();
	}
}



class Topo_Vertex{
	char ver;
	int visited;  //标记顶点的访问状态：0 从未被访问  1 已经访问并且退出  -1 正在递归访问还未退出
	
	public Topo_Vertex(char ver) {
		this.ver = ver;
		visited = 0;
	}
}