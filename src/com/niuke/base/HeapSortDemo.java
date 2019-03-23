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
		int parentIndex = (index - 1) / 2;
		//temp保存插入的节点值，用于最后的赋值
		int temp = arr[index];
		while(index > 0 && temp > arr[parentIndex]) {
			//无需真正交换，单向赋值即可
			arr[index] = arr[parentIndex];
			index = parentIndex;
			parentIndex = (parentIndex - 1) / 2;
		}
		arr[index] = temp; 
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
	
	public static void downAdjust(int[] arr, int parentIndex, int length) {
		int temp = arr[parentIndex];
		int childIndex = parentIndex * 2 + 1;
		while(childIndex < length) {
			if(childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
				childIndex++;
			}
			if(temp >= arr[childIndex]) {
				break;
			}
			arr[parentIndex] = arr[childIndex];
			parentIndex = childIndex;
			childIndex= childIndex * 2 + 1;
		}
		arr[parentIndex] = temp;
	}
	
	public static void buildHeap(int[] arr) {
		for(int i = ((arr.length - 2) / 2); i >= 0; i--) {
			downAdjust(arr, i, arr.length - 1);
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
		
		/*int[] arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
		buildHeap(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("====================================");
		
		arr = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
		heapSort(arr);*/
	}
}
