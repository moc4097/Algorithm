package com.lanqiao.graph;

public class 图着色_二分图 {
	public static boolean dfs(MyNode node, int c) {
		node.color = c; //既将该node染色，又标记了node以被访问
		for(int i = 0; i < node.getSize(); i++) {
			MyNode neighbor = (MyNode)node.getNeighbor(i);
			if(neighbor.color == c) {
				return false;
			}
			if(neighbor.color == 0) {
				boolean res = dfs(neighbor, -c);
				if(!res) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		MyNode A = new MyNode('A');
		MyNode B = new MyNode('B');
		MyNode C = new MyNode('C');
		MyNode D = new MyNode('D');
		
		A.add(B);
		A.add(D);
		B.add(A);
		B.add(C);
		C.add(B);
		C.add(D);
		D.add(A);
		D.add(C);
		
		System.out.println(dfs(A, 1));
		System.out.println("========================================");
		
		MyNode A1 = new MyNode('A');
		MyNode B1 = new MyNode('B');
		MyNode C1 = new MyNode('C');
		
		A1.add(B1);
		A1.add(C1);
		B1.add(A1);
		B1.add(C1);
		C1.add(A1);
		C1.add(B1);
		
		System.out.println(dfs(A1, 1));
	}
}

class MyNode extends GraphNode_AL{
	int color;
	
	public MyNode(char vertex) {
		super(vertex);
	}
	
	public MyNode(char vertex, int color) {
		super(vertex);
		this.color = color;
	}
}
