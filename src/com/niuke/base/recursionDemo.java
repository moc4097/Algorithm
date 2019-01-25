package com.niuke.base;

public class recursionDemo {
	
	public static int getMax(int[] arr,int L,int R) {
		if(L==R) {      //base case
			return arr[L];
		}
		int mid=(L+R)/2;   //L+(R-L)/2   ·ÀÖ¹Òç³ö
		int maxleft=getMax(arr,L,mid);
		int maxRight=getMax(arr,mid+1,R);
		return Math.max(maxleft, maxRight);
	}
	
	public static void main(String[] args) {
		int[] arr= {3,5,7,1,0};
		int max=getMax(arr,0,arr.length-1);
		System.out.println(max);
	}
}
