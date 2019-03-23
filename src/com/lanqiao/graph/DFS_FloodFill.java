package com.lanqiao.graph;

/**
 * ������
 * ����һ��m��n�е��ַ�����ͳ���ַ���@����ɶ��ٸ������顣
 * ��������ַ���@�����ڵĸ������ڣ��ᡢ�����߶Խ��߷��򣩣���˵��������ͬһ�������顣
 * 
 * ʾ����
 * 	*@@*@
 * 	**@*@
 * 	****@
 * 	@@@*@
 * 	@@**@
 * @author Moc
 *
 */
public class DFS_FloodFill {
	private static char[][] graph = {
			"*@@*@".toCharArray(),
			"**@*@".toCharArray(),
			"****@".toCharArray(),
			"@@@*@".toCharArray(),
			"@@**@".toCharArray()
	};
	
	private static int cnt = 0; //��¼������
	private static int len = graph.length;
	
	public static void dfs(int i, int j) {
		if(i < 0 || i >= len || j < 0 || j >= len) {
			return;
		}
		if(graph[i][j] == '*') {
			return;
		}
		graph[i][j] = '*';
		//�ֱ���˸��������
		dfs(i, j - 1);
		dfs(i + 1, j - 1);
		dfs(i + 1, j);
		dfs(i + 1, j + 1);
		dfs(i , j + 1);
		dfs(i - 1, j + 1);
		dfs(i - 1, j);
		dfs(i - 1, j - 1);
	}
	
	public static void main(String[] args) {
		int len = graph.length;
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				if(graph[i][j] == '@') {
					dfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
