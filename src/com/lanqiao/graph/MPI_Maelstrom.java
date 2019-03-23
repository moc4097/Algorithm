package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * POJ 1502
 * @author Moc
 *
 */
public class MPI_Maelstrom {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		while(scn.hasNextInt()) {
			int n = scn.nextInt();
			scn.nextLine();
			
			MPI mpi = new MPI(n);
			
			for(int i = 0; i < n; i++) {
				if(i != 0) {
					String str = scn.nextLine();
					String[] s = str.split(" ");
					for(int j = 0; j < s.length; j++) {
						if(s[j].equals("x")) {
							mpi.addEdge(i, j, 0);
						}else {
							mpi.addEdge(i, j, Integer.valueOf(s[j]));
						}
					}
				}
			}
						
			int[] d = mpi.shortestPath(0);
			
			int res = 0;
			for(int i = 0; i < n; i++) {
				if(res < d[i]) {
					res = d[i];
				}
			}
			System.out.println(res);
			
		}
	}
}

class MPI{
	private boolean[] vertexList;  //标记顶点是否被访问过
	private int nVerts;
	private int[][] adjMat;
	
	public MPI(int nVerts) {
		this.nVerts = nVerts;
		this.vertexList = new boolean[nVerts];
		this.adjMat = new int[nVerts][nVerts];
		
		for(int i = 0; i < nVerts; i++) {
			vertexList[i] = false;
		}
		
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				adjMat[i][j] = 0;
			}
		}
	}
	
	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
	}
	
	public void display() {
		for(int i = 0; i < nVerts; i++) {
			for(int j = 0; j < nVerts; j++) {
				System.out.print(adjMat[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public int[] shortestPath(int s){
		int nTree = 0;
		int[] d = new int[nVerts];
		
		vertexList[s] = true;
		d[s] = 0;
		nTree++;
		
		for(int i = 0; i < nVerts; i++) {
			if(i != s && adjMat[s][i] == 0) {
				d[i] = Integer.MAX_VALUE;
			}
			if(i != s && adjMat[s][i] > 0) {
				d[i] = adjMat[s][i];
			}
		}
		
		while(nTree < nVerts) {
			int minIndex = getMinIndex(d);
			if(minIndex == -1) {
				break;
			}
			vertexList[minIndex] = true;
			nTree++;
			
			for(int j = 0; j < nVerts; j++) {
				int cost = adjMat[minIndex][j];
				if(cost > 0 && d[j] > d[minIndex] + cost) {
					d[j] = d[minIndex] + cost;
				}
			}
		}
		
		return d;
	}
	
	private int getMinIndex(int[] d) {
		int index = -1;
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < nVerts; i++) {
			if(vertexList[i] == false && d[i] < min) {
				min = d[i];
				index = i;
			}
		}
		
		return index;
	}
}


