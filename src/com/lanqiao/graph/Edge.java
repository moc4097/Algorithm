package com.lanqiao.graph;

public class Edge<T> implements Comparable<Edge>{
	private T start;
	private T end;
	int distance;
	
	public Edge(T start, T end, int distance) {
		super();
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	public T getStart() {
		return start;
	}

	public void setStart(T start) {
		this.start = start;
	}

	public T getEnd() {
		return end;
	}

	public void setEnd(T end) {
		this.end = end;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return start + "->" + end + ":" + distance;
	}

	@Override
	public int compareTo(Edge e) {
		int result = distance - e.distance;
		return result;
	}

}
