package com.lanqiao.bitOperator;

import java.util.Arrays;
import java.util.Random;

/**
 * ����������Ψһ�ɶԵ��Ǹ���
 * @author Moc
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		int n = 1001;
		int[] arr = new int[n];
		for(int i = 0; i < arr.length - 1; i++) {
			arr[i] = i + 1;
		}
		//����һ������ظ�����
		arr[arr.length - 1] = new Random().nextInt(n-1)+1;
		//����±�
		int index = new Random().nextInt(n);
		swap(arr, index, arr.length-1);
		System.out.println(Arrays.toString(arr));
		
		int q = 0;
		for(int i=1; i < n; i++) {
			q ^= i;
		}
		for(int i = 0; i < n; i++) {
			q ^= arr[i];
		}
		System.out.println(q);
		System.out.println("==================");
		int[] helper = new int[n];
		for(int i = 0; i < n; i++) {
			helper[arr[i]]++;
		}
		for(int i = 0; i < n; i++) {
			if (helper[i] == 2) {
				System.out.println(i);
				break;
			}
		}
	}
	
	public static void swap(int[] arr, int a, int b) {
		arr[a] = arr[a] ^ arr[b]; 
		arr[b] = arr[a] ^ arr[b];
		arr[a] = arr[a] ^ arr[b];
	}
}
