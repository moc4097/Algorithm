package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.List;

public class Prime {
	private List<Pri_Vertex> vertexList;
	private int nVerts;
	private int[][] adjMat;
	private int sum;  //用来记录整棵树的权重
	private List<Pri_Path> pathList;
	
	public Prime(int nVerts) {
		this.nVerts = nVerts;
		this.vertexList = new ArrayList<Pri_Vertex>(nVerts);
		this.adjMat = new int[nVerts][nVerts];
		
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				adjMat[i][j] = 0;
			}
		}
		
		this.pathList = new ArrayList<Pri_Path>(nVerts);
	}
	
	public void addVertex(char ver) {
		vertexList.add(new Pri_Vertex(ver));
	}
	
	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
	}
	
	public int mstW(int s) {
		sum = 0;

		vertexList.get(s).isInTree = true;
		
		for(int i = 0; i < nVerts; i++) {
			if(i == s) {
				pathList.add(new Pri_Path(s, 0));
			}
			if(i != s && adjMat[s][i] == 0) {
				pathList.add(new Pri_Path(s, Integer.MAX_VALUE));
			}
			if(i != s && adjMat[s][i] > 0) {
				pathList.add(new Pri_Path(s, adjMat[s][i]));
			}
		}
		
		for(int i = 1; i < nVerts; i++) {  //将顶点加入数中的趟数
			int minIndex = getMinIndex();
			
			vertexList.get(minIndex).isInTree = true;
			sum += pathList.get(minIndex).distance;
			
			for(int j = 0; j < nVerts; j++) {
				int len = adjMat[minIndex][j];
				if(len > 0 && !vertexList.get(j).isInTree && pathList.get(j).distance > len) {
					pathList.get(j).distance = len;
					pathList.get(j).parentVer = minIndex;
				}
			}
		}
		
		System.out.println(pathList);
		return sum;
	}
	
	public int getMinIndex() {
		int index = -1;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < nVerts; i++) {
			if(!vertexList.get(i).isInTree  && min > pathList.get(i).distance) {
				min = pathList.get(i).distance;
				index = i;
			}
		}
		return index;
	}
	
	public void displayPath(){
		for(int i = 0; i < pathList.size(); i++) {
			int parent = pathList.get(i).parentVer;
			System.out.println("" + vertexList.get(parent).ver + vertexList.get(i).ver);
		}
	}
	
	public static void main(String[] args) {
		Prime p = new Prime(6);
		p.addVertex('A');
		p.addVertex('B');
		p.addVertex('C');
		p.addVertex('D');
		p.addVertex('E');
		p.addVertex('F');
		
		p.addEdge(0, 1, 6);
		p.addEdge(0, 3, 4);
		p.addEdge(1, 2, 10);
		p.addEdge(1, 3, 7);
		p.addEdge(1, 4, 7);
		p.addEdge(2, 3, 8);
		p.addEdge(2, 4, 5);
		p.addEdge(2, 5, 6);
		p.addEdge(3, 4, 12);
		p.addEdge(4, 5, 7);
		
		System.out.println(p.mstW(0));
		p.displayPath();
	}
}

class Pri_Vertex{
	char ver;
	boolean isInTree;
	
	public Pri_Vertex(char ver) {
		this.ver = ver;
		this.isInTree = false;
	}
}

class Pri_Path{
	int parentVer;
	int distance;
	
	public Pri_Path(int parentVer, int distance) {
		this.parentVer = parentVer;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Pri_Path [parentVer=" + parentVer + ", distance=" + distance + "]";
	}
	
	
}
