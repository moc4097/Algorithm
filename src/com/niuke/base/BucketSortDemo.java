package com.niuke.base;

import java.util.Arrays;

/**
 * 计数排序
 * @author Moc
 *
 */
public class BucketSortDemo {
	
	/**
	 * 适用0~max的排序
	 * @param arr
	 */
	public static void bucketSort(int[] arr) {
		if(arr.length<2||arr==null) {
			return;
		}
		int max=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			max=max<arr[i]?arr[i]:max;
		}
		int[] bucket=new int[max+1];
		for(int i=0;i<arr.length;i++) {
			bucket[arr[i]]++;
		}
		int j=0;
		for(int i=0;i<bucket.length;i++) {
			while(bucket[i]!=0) {
				arr[j++]=i;
				bucket[i]--;
			}
		}
	}
	
	/**
	 * 节省空间复杂度
	 * @param arr
	 */
	public static void bucketSort2(int[] arr) {
		if(arr.length<2||arr==null) {
			return;
		}
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<arr.length;i++) {
			max=max<arr[i]?arr[i]:max;
			min=min>arr[i]?arr[i]:min;
		}
		int[] bucket=new int[max-min+1];
		for(int i=0;i<arr.length;i++) {
			bucket[arr[i]-min]++;
		}
		int j=0;
		for(int i=0;i<bucket.length;i++) {
			while(bucket[i]!=0) {
				arr[j++]=i+min;
				bucket[i]--;
			}
		}
	}
	
	public static void rightSort(int[] arr) {
		Arrays.sort(arr);
	}
	
	public static int[] generateRandomArray(int size,int value) {
		int[] arr=new int[(int)((size+1)*Math.random())];
		for(int i=0;i<arr.length;i++) {
			arr[i]=(int)((value+1)*Math.random());
		}
		return arr;
	}
	
	public static boolean isEqual(int[] arr,int[] arr2) {
		if((arr==null&&arr2!=null)||(arr!=null&&arr2==null)) {
			return false;
		}
		if(arr==null&&arr2==null) {
			return true;
		}
		if(arr.length!=arr2.length) {
			return false;
		}
		for(int i=0;i<arr.length;i++) {
			if(arr[i]!=arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int testTime=500000;
		int size=100;
		int value=99;
		boolean success=true;
		for(int i=0;i<testTime;i++) {
			int[] arr=generateRandomArray(size,value);
			int[] arr2=Arrays.copyOf(arr, arr.length);
			bucketSort(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice!":"fuck");
		
		int[] arr=generateRandomArray(size, value);
		bucketSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
