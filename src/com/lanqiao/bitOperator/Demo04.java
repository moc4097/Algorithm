package com.lanqiao.bitOperator;

import java.util.Scanner;

/**
 * 一条语句判断整数是不是2的整数次方
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
	 * 解决是负数的情况
	 */
	public static void test() {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		//取绝对值
		n = n * (1 - ((n >>> 31) << 1));
		if (((n - 1) & n) == 0) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}
	}
}
