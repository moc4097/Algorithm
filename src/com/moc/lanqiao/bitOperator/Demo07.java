package com.moc.lanqiao.bitOperator;

/**
 * ����k�������1��(���ò���λ��ӷ�)
 * 2����ͬ�Ķ�����������λ��ӣ����Ϊ0
 * 10����ͬ��ʮ����������λ��ӣ����Ϊ0
 * k����ͬ��k����������λ��ӣ����Ϊ0
 * @author Moc
 *
 */
public class Demo07 {
	public static void main(String[] args) {
		int[] arr = {2, 2, 2, 9, 7, 7, 7, 3, 3, 3, 6, 6, 6, 0, 0, 0};
		int len = arr.length;
		char[][] kRadix = new char[len][];
		int k = 3;
		int maxLength = 0;  //kRadix��ά�������һ�и���
		for(int i = 0; i < len; i++) {
			//��ÿ����תΪk���������ٷ���洢��kRadix��
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
