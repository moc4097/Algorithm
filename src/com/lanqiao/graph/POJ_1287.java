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
 * POJ_1287
 * @author Moc
 *
 */
public class POJ_1287 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		while(scn.hasNextInt()) {
			
			int vNum = scn.nextInt();
			if(vNum == 0) {
				break;
			}
			
			int eNum = scn.nextInt(); 
			NetWorking nw = new NetWorking(vNum, eNum);
			
			for(int i = 0; i < eNum; i++) {
					int start = scn.nextInt();
					int end = scn.nextInt();
					int distance = scn.nextInt();
					nw.addEdge(start, end, distance);
			}
			
			int distance = nw.kruskal();
			System.out.println(distance);
			
		}
	}
}

class NetWorking{
	private List<Net_Edge> edgeList;
	private int nVerts;
	private int nEdges;
	private int sum;
	private Set<Net_Edge> T;
	private Map<Integer,Net_UnionFind.UFNode> m;
	
	public NetWorking(int nVerts, int nEdges) {
		this.nVerts = nVerts;
		this.nEdges = nEdges;
		this.edgeList = new ArrayList<Net_Edge>(nEdges);
		this.sum = 0;
		this.T = new HashSet<Net_Edge>();
		this.m = new HashMap<Integer, Net_UnionFind.UFNode>();
	}
	
	public void addEdge(int start, int end, int distance) {
		edgeList.add(new Net_Edge(start, end, distance));
	}
	
	public int kruskal() {
		Collections.sort(edgeList);
		
		for(Net_Edge e : edgeList) {
			m.put(e.start, new Net_UnionFind.UFNode());
			m.put(e.end, new Net_UnionFind.UFNode());
		}
		
		for(Net_Edge e : edgeList) {
			if(!ok(e)) {
				continue;
			}
			T.add(e);
			if(T.size() == nVerts - 1) {
				break;
			}
		}
		
		return sum;
	}
	
	public boolean ok(Net_Edge e) {
		Net_UnionFind.UFNode x = m.get(e.start);
		Net_UnionFind.UFNode y = m.get(e.end);
		
		if(Net_UnionFind.find(x) == Net_UnionFind.find(y)) {
			return false;
		}
		Net_UnionFind.union(x, y);
		sum += e.distance;
		return true;
	}
}

class Net_UnionFind{
	public static UFNode find(UFNode n) {
		UFNode p = n;
		Set<UFNode> s = new HashSet<UFNode>();
		
		while(p.parentVer != null) {
			s.add(p);
			p = p.parentVer;
		}
		
		for(UFNode un : s) {
			un.parentVer = p;
		}
		
		return p;
	}
	
	public static void union(UFNode x, UFNode y) {
		find(y).parentVer = find(x);
	}
	
	public static class UFNode{
		UFNode parentVer;
	}
}

class Net_Edge implements Comparable<Net_Edge>{
	int start;
	int end;
	int distance;
	
	public Net_Edge(int start, int end, int distance) {
		this.start = start;
		this.end  = end;
		this.distance = distance;
	}
	
	

	@Override
	public String toString() {
		return "Net_Edge [start=" + start + ", end=" + end + ", distance=" + distance + "]";
	}



	@Override
	public int compareTo(Net_Edge e) {
		int res = distance - e.distance;
		return res;
	}
}



