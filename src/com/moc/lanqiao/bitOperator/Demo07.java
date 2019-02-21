package com.moc.lanqiao.bitOperator;

/**
 * 出现k次与出现1次(采用不进位相加法)
 * 2个相同的二进制数不进位相加，结果为0
 * 10个相同的十进制数不进位相加，结果为0
 * k个相同的k进制数不进位相加，结果为0
 * @author Moc
 *
 */
public class Demo07 {
	public static void main(String[] args) {
		int[] arr = {2, 2, 2, 9, 7, 7, 7, 3, 3, 3, 6, 6, 6, 0, 0, 0};
		int len = arr.length;
		char[][] kRadix = new char[len][];
		int k = 3;
		int maxLength = 0;  //kRadix二维数组最长那一行个数
		for(int i = 0; i < len; i++) {
			//将每个数转为k进制数，再反向存储在kRadix中
			kRadix[i] = new StringBuilder(Integer.toString(arr[i], k)).reverse().toString().toCharArray();
			if (kRadix[i].length > maxLength) {
				maxLength = kRadix[i].length;
			}
		}
		
		int[] resArr = new int[maxLength];
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < maxLength; j++) {
				if (j >= kRadix[i].length) {
					resArr[j] += 0; 
				}else {
					resArr[j] += (kRadix[i][j] - '0');
				}
			}
		}
		
		int res = 0;
		for(int i = 0; i < maxLength; i++) {
			res += (resArr[i] % k) * (int)(Math.pow(k, i));
		}
		System.out.println(res);
	}
}
