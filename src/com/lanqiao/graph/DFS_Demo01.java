package com.lanqiao.graph;

import java.util.Scanner;

/**
 * ��ͨ���
 * ����һ�����󣬶�����ͨ�������������ڣ�����ֵ��ͬ��
 * ���������һ�ŵ�ͼ����ͬ������Ϳ�Բ�ͬ��ɫ��
 * ���룺
 * ����N����N<50����ʾ�����������
 * ������N�У�ÿ��N���ַ����������е�Ԫ��
 * ������һ������M����M<1000����ʾѯ����
 * ������M�У�ÿ�д���һ��ѯ�ʣ�
 * ��ʽΪ4��������y1��x1��y2��x2
 * ��ʾѯ�ʣ���y1�У���x1�У��루��y2�У���x2�У��Ƿ����ӣ�
 * ��ͨ���true������false
 * 
 * ���磺
10
0010000000
0011100000
0000111110
0001100010
1111010010
0000010010
0000010011
0111111000
0000010000
0000000000
3
0 0 9 9
0 2 6 8
4 4 4 6
 * 
 * ����Ӧ�������
 * false
 * true
 * true
 * 
 * @author Moc
 *
 */
public class DFS_Demo01 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		scn.nextLine();
		char[][] graph = new char[n][n];
		int[][] label = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			graph[i] = scn.nextLine().toCharArray();
		}
		
		int m = scn.nextInt();
		int[][] query = new int[m][4];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < 4; j++) {
				query[i][j] = scn.nextInt();
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				label[i][j] =0;
			}
		}
		
		for(int i = 0; i < m; i++) {
			boolean ok = check(graph, label, query[i]);
			System.out.println(ok);
		}
		
	}
	
	public static boolean check(char[][] graph, int[][] label, int[] points) {
		int x1 = points[0];
		int y1 = points[1];
		int x2 = points[2];
		int y2 = points[3];
		
		char value = graph[x1][y1];
		
		boolean f1 = false;
		boolean f2 = false;
		boolean f3 = false; 
		boolean f4 = false;
		
		if(x1 == x2 && y1 == y2) {
			return true;
		}
		
		//������  (1�������߳�ȥ 2���ϱߵ�λ�û�û�����ʹ� 3���ϱ�λ���ϵ�ֵҪ�����ڵ�ֵ��ͬ)
		if((y1 - 1) >= 0 && label[x1][y1 - 1] == 0 && graph[x1][y1 - 1] == value) {
			label[x1][y1 - 1] = 1;
			points[1] = y1 - 1;
			f1 = check(graph, label, points);
			//����
			label[x1][y1 - 1] = 0;
			points[1] = y1;
		}
		
		//������
		if((x1 + 1) < graph.length && label[x1 + 1][y1] == 0 && graph[x1 + 1][y1] == value) {
			label[x1 + 1][y1] = 1;
			points[0] = x1 + 1;
			f2 = check(graph, label, points);
			points[0] = x1;
			label[x1 + 1][y1] = 0;
		}
		
		//������
		if((y1 + 1) < graph.length && label[x1][y1 + 1] == 0 && graph[x1][y1 + 1] == value) {
			label[x1][y1 + 1] = 1;
			points[1] = y1 + 1;
			f3 = check(graph, label, points);
			points[1] = y1;
			label[x1][y1 + 1] = 0;
		}
		
		//������
		if((x1 - 1) >= 0 && label[x1 - 1][y1] == 0 && graph[x1 - 1][y1] == value) {
			label[x1 - 1][y1] = 1;
			points[0] = x1 - 1;
			f4 = check(graph, label, points);
			points[0] = x1;
			label[x1 - 1][y1] = 0;
		}
		
		return f1 || f2 || f3 || f4;
	}
}
