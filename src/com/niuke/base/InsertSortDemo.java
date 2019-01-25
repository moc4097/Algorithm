package com.niuke.base;

import java.util.Arrays;

public class InsertSortDemo {
	public static void insertSort(int[] arr) {
		if(arr.length<2||arr==null) {
			return;
		}
		for(int i=1;i<arr.length;i++) {
			for(int j=i-1;j>=0;j--) {
				if(arr[j]>arr[j+1]) {
					swap(arr,j,j+1);
				}
			}
		}
	}
	
	public static void rightSort(int[] arr) {
		Arrays.sort(arr);
	}
	
	public static void swap(int[] arr,int i,int j) {
		arr[i]=arr[i]^arr[j];
		arr[j]=arr[i]^arr[j];
		arr[i]=arr[i]^arr[j];
	}
	
	public static int[] generateRandomArray(int size,int value) {
		int[] arr=new int[(int)((size+1)*Math.random())];
		for(int i=0;i<arr.length;i++) {
			arr[i]=(int)((value+1)*Math.random())-(int)(value*Math.random());
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
		int value=100;
		boolean success=true;
		for(int i=0;i<testTime;i++) {
			int[] arr=generateRandomArray(size,value);
			int[] arr2=Arrays.copyOf(arr, arr.length);
			insertSort(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice!":"fuck");
	}
}
