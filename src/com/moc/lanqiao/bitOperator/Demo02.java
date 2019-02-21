package com.moc.lanqiao.bitOperator;

/**
 * 找出落单的数
 * @author Moc
 *
 */
public class Demo02 {
	public static void main(String[] args) {
		int[] arr = {1, 1, 2, 2, 3, 4, 5, 5, 4, 6, 7, 6, 7};
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			result ^= arr[i];
		}
		System.out.println(result);
	}
}
