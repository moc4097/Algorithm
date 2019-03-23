package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * 城市建设
 * 
 * 解题思路:

        本题的思路就是处理好了码头，就是求最小生成树：
        1.处理码头：虚拟一个城市0，0与i城市建立码头的价值就等于w[i]

        2.建立最小生成树MST

注意事项:
        1.处理码头的时候，如果w[i]=-1，则不要加入这条边，或者设置w[i]=inf

        2.建立最小生成树的时候，如果a,b两条边的价值w[a][b]的小于0，则不管是否连通，都要将这条边加入MST（题中求最小价值）

        3.如果与城市0连接只有一条边，则不需要城市0，答案需要减去这条边的价值
 * @author Moc
 *
 */
public class 城市建设 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		while(scn.hasNextInt()) {
			int n = scn.nextInt();
			int m = scn.nextInt();
			
			int maxEdges = m + n; 
			Build_City city = new Build_City(n, maxEdges);
			
			for(int i = 0; i < m; i++) {
				int start = scn.nextInt();
				int end = scn.nextInt();
				int distance = scn.nextInt();
				city.addEdge(start, end, distance);
			}
			
			for(int i = 0; i < n; i++) {
				int matou = scn.nextInt();
				if(matou != -1) {
					city.addEdge(0, i + 1, matou);
				}
			}
			
			int sum = city.kruskal();
			System.out.println(sum);
			
		}
	}
}

class Build_City{
	private List<City_Edge> edgeList;
	private int nEdges;
	private int nVerts;
	private Set<City_Edge> T;
	private Set<City_Edge> maDegree;
	private int sum;
	private int[] d;
	
	public Build_City(int nVerts, int nEdges) {
		this.nVerts = nVerts + 1;
		this.nEdges = nEdges;
		this.edgeList = new ArrayList<City_Edge>(nEdges);
		this.T = new HashSet<City_Edge>(nEdges);
		this.maDegree = new HashSet<City_Edge>(this.nVerts);
		this.sum = 0;
		
		this.d = new int[this.nVerts + 1];
		for(int i = 0; i < this.nVerts + 1; i++) {
			d[i] = i;
		}
	}
	
	public void addEdge(int start, int end, int distance) {
		edgeList.add(new City_Edge(start, end, distance));
	}
	
	public void displayT() {
		System.out.println(edgeList.size());
		System.out.println(T.size());
		for(City_Edge e : T) {
			System.out.println(e);
		}
	}
	
	public int kruskal() {
		Collections.sort(edgeList);
		
		for(City_Edge e : edgeList) {
			boolean ok = union(e);
			if(!ok) {
				continue;
			}
			T.add(e);
			if(T.size() == nVerts - 1) {
				break;
			}
		}
		
		if(maDegree.size() == 1) {
			sum -= (maDegree.iterator().next().distance);
		}
		
		return sum;
	}
	
	public int find(int x) {
		return d[x] == x ? x : (d[x] = find(d[x]));
	}
	
	public boolean union(City_Edge e) {
		int u = e.start;
		int v = e.end;
		int w = e.distance;
		
		int x = find(u);
		int y = find(v);
		
		if(x != y || w <= 0) {
			if(u == 0) {
				maDegree.add(e);
			}
			d[x] = y;
			sum += w;
			return true;
		}
		
		return false;
	}
}


class City_Edge implements Comparable<City_Edge>{
	int start;
	int end;
	int distance;
	
	public City_Edge(int start, int end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "City_Edge [start=" + start + ", end=" + end + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(City_Edge e) {
		int res = distance - e.distance;
		return res;
	}
}