package com.niuke.base;

import java.util.Arrays;

public class MergeSortDemo {

	public static void mergeSort(int[] arr) {
		if(arr==null||arr.length<2) {
			return;
		}
		sortProcess(arr,0,arr.length-1);
	}
	
	public static void mergeSort2(int[] arr) {
		if(arr==null&&arr.length<2) {
			return;
		}
		int i=2;
		int mid = 0;
		while(i<=arr.length) {
			int j;
			mid=0;
			for(j=0;j<arr.length;j=j+i) {
				if(j+i-1>=arr.length) {
					/*//mid=j+((arr.length-1-j)>>1);
					mid=j-1;
					System.out.println(mid+" "+(arr.length-1));
					merge(arr,j,mid,arr.length-1);*/
					break;
				}
				mid=j+((i-1)>>1);
				merge(arr,j,mid,j+i-1);
			}
			i=i*2;
		}
		if(i>arr.length) {
			mid=i/2-1;
			merge(arr,0,mid,arr.length-1);
		}
		
	}
	
	public static void sortProcess(int[] arr,int l,int r) {
		if(l==r) {
			return;
		}
		int mid=l+((r-l)>>1);
		sortProcess(arr,l,mid);
		sortProcess(arr,mid+1,r);
		merge(arr,l,mid,r);
	}
	
	public static void merge(int[] arr,int L,int mid,int R) {
		int[] help=new int[R-L+1];
		int p1=L;
		int p2=mid+1;
		int i=0;
		while(p1<=mid&&p2<=R) {
			help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
		}
		while(p1<=mid) {
			help[i++]=arr[p1++];
		}
		while(p2<=R) {
			help[i++]=arr[p2++];
		}
		for(i=0;i<help.length;i++) {
			arr[L+i]=help[i];
		}
	}
	
	public static void rightSort(int[] arr) {
		Arrays.sort(arr);
	}
	
	public static int[] generateRandomArray(int size,int value) {
		int[] arr=new int[(int)((size+1)*Math.random())];
		for(int i=0;i<arr.length;i++) {
			arr[i]=(int)((value+1)*Math.random())-(int)(value*Math.random());
		}
		return arr;
	}
	
	public static boolean isEqual(int[] arr,int[] arr2) {
		if(arr==null&&arr2==null) {
			return true;
		}
		if((arr==null&&arr2!=null)&&(arr!=null&&arr2==null)) {
			return false;
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
	
	public static void test1() {
		int testTime=500000;
		int size=100;
		int value=200;
		boolean success=true;
		for(int i=0;i<testTime;i++) {
			int[] arr=generateRandomArray(size, value);
			int[] arr2=new int[arr.length];
			System.arraycopy(arr, 0, arr2, 0, arr.length);
			mergeSort(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice":"fuck");
		
		//int[] arr=generateRandomArray(size, value);
		int[] arr= {55,58,39,18,90,150,140,38,174};
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		/*int testTime=50000;
		int size=100;
		int value=200;
		boolean success=true;
		for(int i=0;i<testTime;i++) {
			int[] arr=generateRandomArray(size, value);
			int[] arr2=new int[arr.length];
			System.arraycopy(arr, 0, arr2, 0, arr.length);
			mergeSort2(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice":"fuck");
		
		//int[] arr=generateRandomArray(size, value);
		int[] arr= {5,1,4,3,2,0,9,7,-5,6};
		mergeSort2(arr);
		System.out.println(Arrays.toString(arr));*/
		test1();
	}
}
