package com.lanqiao.bitOperator;

import java.util.Scanner;

/**
 * 二进制中1的个数
 * @author Moc
 *
 */
public class Demo03 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		System.out.println(Integer.toString(n, 2));
		
		int count = 0;
		for(int i = 0; i < 32; i++) {
			if ((n & (1 << i)) == (1 << i)) {
				count++;
			}
		}
		System.out.println(count);
		System.out.println("=====================");
		
		count = 0;
		for(int i = 0; i < 32; i++) {
			if ((1 & (n >>> i)) == 1) {
				count++;
			}
		}
		System.out.println(count);
		System.out.println("=======================");
		
		count = 0;
		while(n != 0) {
			n &= (n - 1);
			count++;
		}
		System.out.println(count);
	}
	
}
