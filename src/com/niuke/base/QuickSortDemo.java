package com.niuke.base;

import java.util.Arrays;

public class QuickSortDemo {
	public static void quickSort(int[] arr) {
		if(arr.length<2||arr==null) {
			return;
		}
		sortProcess(arr,0,arr.length-1);
	}
	
	public static void sortProcess(int[] arr,int L,int R) {
		if(L<R) {
			swap(arr,(int)(L+(R-L+1)*Math.random()),R);
			int[] index=patition1(arr,L,R);
			sortProcess(arr,L,index[0]-1);
			sortProcess(arr,index[1]+1,R);
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
	
	public static int patition(int[] arr,int L,int R) {
		int less=L-1;
		int P=arr[R];
		for(int i=L;i<=R;i++) {
			if(arr[i]<=P) {
				swap(arr,++less,i);
			}
		}
		return less;
	}
	
	public static int[] patition1(int[] arr,int L,int R) {
		int less=L-1;
		int more=R;
		while(L<more) {
			if(arr[L]<arr[R]) {
				swap(arr,++less,L++);
			}else if(arr[L]>arr[R]) {
				swap(arr,--more,L);
			}else {
				L++;
			}
		}
		swap(arr,more,R);
		return new int[] {less+1,more};
	}
	
	public static void main(String[] args) {
		int testTime=500000;
		int size=100;
		int value=100;
		boolean success=true;
		for(int i=0;i<testTime;i++) {
			int[] arr=generateRandomArray(size,value);
			//int[] arr2=Arrays.copyOf(arr, arr.length);
			int[] arr2=new int[arr.length];
			System.arraycopy(arr, 0, arr2, 0, arr.length);
			quickSort(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice!":"fuck");
		
		/*int[] arr= {0,5,3,1,4,6,4};
		int[] range=patition1(arr,0,arr.length-1);
		//int index=patition(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(range));
		//System.out.println(index);
*/	}
}
