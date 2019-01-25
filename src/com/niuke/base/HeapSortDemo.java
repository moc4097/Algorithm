package com.niuke.base;

import java.util.Arrays;

public class HeapSortDemo {
	public static void heapSort(int[] arr) {
		if(arr==null||arr.length<2) {
			return;
		}
		for(int i=0;i<arr.length;i++) {
			heapInsert(arr,i);
		}
		int heapSize=arr.length;
		swap(arr,0,--heapSize);
		while(heapSize>0) {
			heapify(arr,0,heapSize);
			swap(arr,0,--heapSize);
		}
	}
	
	public static void heapInsert(int[] arr,int index) {
		while(arr[index]>arr[(index-1)/2]) {
			swap(arr,index,(index-1)/2);
			index=(index-1)/2;
		}
	}
	
	public static void heapify(int[] arr,int index,int heapSize) {
		int left=index*2+1;
		while(left<heapSize) {
			int largest=(left+1)<heapSize&&arr[left]<arr[left+1]?left+1:left;
			largest=arr[largest]>arr[index]?largest:index;
			if(largest==index) {
				break;
			}
			swap(arr,index,largest);
			index=largest;
			left=index*2+1;
		}
	}
	
	public static void rightSort(int[] arr) {
		Arrays.sort(arr);
	}
	
	public static void swap(int[] arr,int i,int j) {
		int temp;
		temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
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
			heapSort(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice!":"fuck");
		
		int[] arr=generateRandomArray(size,value);
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
