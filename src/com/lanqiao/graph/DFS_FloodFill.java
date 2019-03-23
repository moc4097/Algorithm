package com.lanqiao.graph;

/**
 * 八连块
 * 输入一个m行n列的字符矩阵，统计字符“@”组成多少个八连块。
 * 如果两个字符“@”所在的格子相邻（横、竖或者对角线方向），就说它们属于同一个八连块。
 * 
 * 示例：
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
	
	private static int cnt = 0; //记录区块数
	private static int len = graph.length;
	
	public static void dfs(int i, int j) {
		if(i < 0 || i >= len || j < 0 || j >= len) {
			return;
		}
		if(graph[i][j] == '*') {
			return;
		}
		graph[i][j] = '*';
		//分别向八个方向遍历
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
