package com.moc.lanqiao.bitOperator;

import java.util.Scanner;

/**
 * 将整数的奇偶位互换
 * @author Moc
 *
 */
public class Demo05 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		System.out.println(Integer.toString(n,2));
		int a = 0xaaaaaaaa;
		int b = 0x55555555;
		a &= n;
		b &= n;
		int result = (a >> 1) ^ (b << 1);
		System.out.println(Integer.toString(result, 2));
		
	}
}
