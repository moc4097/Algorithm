package com.lanqiao.bitOperator;

import java.util.Scanner;

/**
 * һ������ж������ǲ���2�������η�
 * @author Moc
 *
 */
public class Demo04 {
	public static void main(String[] args) {
		/*Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		if (((n - 1) & n) == 0) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}*/
		test();
	}
	
	/**
	 * ����Ǹ��������
	 */
	public static void test() {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		//ȡ����ֵ
		n = n * (1 - ((n >>> 31) << 1));
		if (((n - 1) & n) == 0) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
}
