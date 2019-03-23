package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dijkstra {
	private List<Dij_Vertex> vertexList;
	private int nVerts;
	private int[][] adjMat;
	private List<Path> pathList;
	private int nTree;
	
	public Dijkstra(int nVerts) {
		this.nVerts = nVerts;
		this.vertexList = new ArrayList<Dij_Vertex>(nVerts);
		this.pathList = new ArrayList<Path>(nVerts);
		this.adjMat = new int[nVerts][nVerts];
		
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				adjMat[i][j] = 0;
			}
		}
		
		this.nTree = 0;
	}
	
	public void addVertex(char ver) {
		vertexList.add(new Dij_Vertex(ver));
	}
	
	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;
	}
	
	public void displayAdjMat() {
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				System.out.print(adjMat[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public List<Path> shortestPath(int s) {
		//初始化
		vertexList.get(s).isInTree = true;
		for(int i = 0; i < nVerts; i++) {
			if(i == s) {
				pathList.add(new Path(s, 0));
			}
			if(i != s && adjMat[s][i] == 0) {
				pathList.add(new Path(s, Integer.MAX_VALUE));
			}
			if(i != s && adjMat[s][i] > 0){
				pathList.add(new Path(s, adjMat[s][i]));
			}
		}
		nTree++;
		
		while(nTree < nVerts) {
			//获取最小值所在下标
			int minIndex = getMinIndex();
			//判断是否存在到达不了的顶点
			if(minIndex == -1) {
				System.out.println("There are unreachable vertices");
				break;
			}else {
				nTree++;
				vertexList.get(minIndex).isInTree = true;
				
				//更新path，以获得最短路径
				for(int neighbor = 0; neighbor < nVerts; neighbor++) {
					int cost = adjMat[minIndex][neighbor];
					if(cost > 0 && pathList.get(neighbor).distance > (pathList.get(minIndex).distance + cost)) {
						pathList.get(neighbor).distance = pathList.get(minIndex).distance + cost;
						pathList.get(neighbor).parentVer = minIndex;
					}
				}
				
			}
			
		}
		
		return pathList;
	}
	
	public int getMinIndex() {
		int index = -1;
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < nVerts; i++) {
			if(!vertexList.get(i).isInTree && pathList.get(i).distance < min) {
				min = pathList.get(i).distance;
				index = i;
			}
		}
		
		return index;
	}
	
	public static void main(String[] args) {
		Dijkstra dij = new Dijkstra(5);
		dij.addVertex('A');
		dij.addVertex('B');
		dij.addVertex('C');
		dij.addVertex('D');
		dij.addVertex('E');
		
		dij.addEdge(0, 1, 50);
		dij.addEdge(0, 3, 80);
		dij.addEdge(1, 2, 60);
		dij.addEdge(1, 3, 90);
		dij.addEdge(2, 4, 40);
		dij.addEdge(3, 2, 20);
		dij.addEdge(3, 4, 70);
		dij.addEdge(4, 1, 50);
		
		dij.displayAdjMat();
		
		List<Path> path = dij.shortestPath(0);
		System.out.println(path);
		Stack<Character> s = new Stack<Character>();
		int n = 2;
		Path p = path.get(n);
		s.push(dij.vertexList.get(n).ver);
		while(p.parentVer != 0) {
			s.push(dij.vertexList.get(p.parentVer).ver);
			p = path.get(p.parentVer);	
		}
		s.push(dij.vertexList.get(0).ver);
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}
}

class Dij_Vertex{
	char ver;
	boolean isInTree;
	
	public Dij_Vertex(char ver) {
		this.ver = ver;
		this.isInTree = false;
	}
}

class Path{
	int parentVer;
	int distance;
	
	public Path(int parentVer, int distance) {
		this.parentVer = parentVer;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Path [distance=" + distance + "]";
	}
	
}
