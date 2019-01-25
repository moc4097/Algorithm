package com.niuke.experiment;

import java.util.Arrays;

public class Merge_SmallSum {

	public static int smallSum(int[] arr) {
		if(arr==null||arr.length<2) {
			return 0;
		}
		return mergeSort(arr,0,arr.length-1);
	}
	
	
	public static int mergeSort(int[] arr,int l,int r) {
		if(l==r) {
			return 0;
		}
		int mid=l+((r-l)>>1);
		return mergeSort(arr,l,mid)+mergeSort(arr,mid+1,r)+
				merge(arr,l,mid,r);
	}
	
	public static int merge(int[] arr,int L,int mid,int R) {
		int res=0;
		int[] help=new int[R-L+1];
		int p1=L;
		int p2=mid+1;
		int i=0;
		while(p1<=mid&&p2<=R) {
			res+=arr[p1]<arr[p2]?(arr[p1]*(R-p2+1)):0;
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
		return res;
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
	
	
	public static void main(String[] args) {
		int testTime=50000;
		int size=100;
		int value=200;
		boolean success=true;
		for(int i=0;i<testTime;i++) {
			int[] arr=generateRandomArray(size, value);
			int[] arr2=new int[arr.length];
			System.arraycopy(arr, 0, arr2, 0, arr.length);
			int samllNum=smallSum(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice":"fuck");
		
		//int[] arr=generateRandomArray(size, value);
		int[] arr= {0,1,5,9,2,3,6};
		int samllNum=smallSum(arr);
		System.out.println(Arrays.toString(arr)+"\nsamllNum:"+samllNum);
	}
}
