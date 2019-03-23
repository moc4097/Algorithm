package com.niuke.experiment;

import java.util.Arrays;


/**
 * ÄæĞò¶Ô
 * 
 * @author Moc
 *
 */
public class Merge_Inversion {

	
	public static int inversion(int[] arr) {
		if(arr==null||arr.length<2) {
			return 0;
		}
		return mergeSort(arr,0,arr.length-1);
	}
	
	public static int mergeSort(int[] arr,int L,int R) {
		if(L==R) {
			return 0;
		}
		int mid=L+((R-L)>>1);
		return mergeSort(arr,L,mid)+
				mergeSort(arr,mid+1,R)+
				merge2(arr,L,mid,R);
	}
	
	public static int merge(int[] arr,int L,int mid,int R) {
		int[] help=new int[R-L+1];
		int p1=L;
		int p2=mid+1;
		int j=0;
		int num=0;
		while(p1<=mid&&p2<=R) {
			num+=arr[p1]>arr[p2]?(R-p2+1):0;
			help[j++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
		}
		
		while(p1<=mid) {
			help[j++]=arr[p1++];	
		}
		while(p2<=R) {
			help[j++]=arr[p2++];
		}
		for(int i=0;i<help.length;i++) {
			arr[L+i]=help[i];
		}
		return num;
	}
	
	public static int merge2(int[] arr,int L,int mid,int R) {
		int[] help=new int[R-L+1];
		int p1=L;
		int p2=mid+1;
		int j=0;
		int num=0;
		while(p1<=mid&&p2<=R) {
			num+=arr[p1]>arr[p2]?(mid-p1+1):0;
			help[j++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
		}
		
		while(p1<=mid) {
			help[j++]=arr[p1++];	
		}
		while(p2<=R) {
			help[j++]=arr[p2++];
		}
		for(int i=0;i<help.length;i++) {
			arr[L+i]=help[i];
		}
		return num;
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
			int num=inversion(arr);
			rightSort(arr2);
			if(!isEqual(arr,arr2)) {
				success=false;
				break;
			}
		}
		System.out.println(success?"Nice":"fuck");
		
		int[] arr=generateRandomArray(size, value);
		//int[] arr= {0,1,5,9,2,3,6};
		int inversion_num=inversion(arr);
		System.out.println(Arrays.toString(arr)+"\nsamllNum:"+inversion_num);
	}
}
