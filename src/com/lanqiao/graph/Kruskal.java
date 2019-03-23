package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lanqiao.graph.UnionFind.UFNode;

public class Kruskal {
	private List<Edge> edgeList;
	private int nVerts;
	private Set<Edge> T;
	private Map ptnAndNode;
	private int sum;
	
	private int[] d;
	
	public Kruskal(int nVerts) {
		this.nVerts = nVerts;
		this.edgeList = new ArrayList<Edge>();
		this.T = new HashSet<Edge>();
		this.d = new int[nVerts+1];
		this.ptnAndNode = new HashMap(nVerts);
		
		for(int i = 0; i <= nVerts; i++) {
			d[i] = i;
		}
		
		this.sum = 0;
	}
	
	public void addEdge(Edge e) {
		edgeList.add(e);
	}
	
	public Set<Edge> getT(){
		mstW();
		return T;
	}
	
	/*public void mstW() {
		Collections.sort(edgeList);
		
		for(Edge e : edgeList) {
			ptnAndNode.put(e.getStart(), new UnionFind.UFNode());
			ptnAndNode.put(e.getEnd(), new UnionFind.UFNode());
		}
		
		for(Edge e : edgeList) {
			if(!ok(e)) {
				continue;
			}
			
			T.add(e);
			
			if(T.size() == nVerts - 1) {
				return;
			}
		}
	}
	
	//并集集中查询e的起点和终点是否在一个集中
	private boolean ok(Edge e) {
		UnionFind.UFNode x = (UnionFind.UFNode) ptnAndNode.get(e.getStart());
		UnionFind.UFNode y = (UnionFind.UFNode) ptnAndNode.get(e.getEnd());
		
		if(UnionFind.find(x) != UnionFind.find(y)) {
			UnionFind.union(x, y);
			return true;
		}
		
		return false;
	}*/
	
	//并查集
	
	private int find(int x) {
		return d[x] == x ? x : (d[x] = find(d[x]));
	}
	
	private boolean union(int u, int v, int w) {
		u = find(u);
		v = find(v);
		if(u == v) {
			return false;
		}
		d[u] = v;
		sum += w;
		return true;
	}
	
	public int mstW() {
		Collections.sort(edgeList);
		
		for(Edge e : edgeList) {
			boolean ok = union((Integer)e.getStart(), (Integer)e.getEnd(), e.getDistance());
			if(!ok) {
				continue;
			}
			T.add(e);
			if(T.size() == nVerts-1) {
				break;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		/*Kruskal k =new Kruskal(6);
		k.addEdge(new Edge(0, 1, 1));
		k.addEdge(new Edge(0, 2, 5));
		k.addEdge(new Edge(0, 3, 6));
		k.addEdge(new Edge(1, 2, 8));
		k.addEdge(new Edge(1, 4, 4));
		k.addEdge(new Edge(2, 4, 2));
		k.addEdge(new Edge(2, 3, 7));
		k.addEdge(new Edge(3, 5, 3));
		k.addEdge(new Edge(4, 5, 9));
		
		int s = k.mstW();
		System.out.println(s);
		Set<Edge> path = k.getT();
		for(Edge e : path) {
			System.out.println(e);
		}*/
		
		Kruskal k = new Kruskal(6);
		k.addEdge(new Edge(0, 1, 6));
		k.addEdge(new Edge(0, 3, 4));
		k.addEdge(new Edge(1, 3, 7));
		k.addEdge(new Edge(1, 2, 10));
		k.addEdge(new Edge(1, 4, 7));
		k.addEdge(new Edge(2, 3, 8));
		k.addEdge(new Edge(2, 4, 5));
		k.addEdge(new Edge(2, 5, 6));
		k.addEdge(new Edge(3, 4, 12));
		k.addEdge(new Edge(4, 5, 7));
		
		int s = k.mstW();
		System.out.println(s);
		Set<Edge> path = k.getT();
		for(Edge e : path) {
			System.out.println(e);
		}
	}
}

class UnionFind{
	
	public static UFNode find(UFNode x) {
		UFNode p = x;
		Set<UFNode> path = new HashSet<UFNode>();
		
		while(p.parent != null) {
			path.add(p);
			p = p.parent;
		}
		
		for(UFNode n : path) {
			n.parent = p;
		}
		
		return p;
	}
	
	public static void union(UFNode x, UFNode y) {
		find(y).parent = find(x);
	}
	
	public static class UFNode{
		UFNode parent;
	}
}
