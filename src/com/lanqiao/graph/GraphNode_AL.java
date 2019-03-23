package com.lanqiao.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode_AL {
	char vertex;
	List<GraphNode_AL> neighborNode;
	boolean visited = false;
	
	public GraphNode_AL(char vertex) {
		this.vertex = vertex;
	}
	
	public void add(GraphNode_AL node) {
		if(null == neighborNode) {
			neighborNode = new ArrayList<GraphNode_AL>();
		}
		neighborNode.add(node);
	}
	
	public GraphNode_AL getNeighbor(int i) {
		return neighborNode.get(i);
	}
	
	public int getSize() {
		return neighborNode.size();
	}
}
