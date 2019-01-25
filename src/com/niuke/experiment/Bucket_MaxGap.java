package com.niuke.experiment;

import java.util.Arrays;

public class Bucket_MaxGap {

	public static int maxGap(int[] arr) {
		if(arr==null||arr.length<2) {
			return 0;
		}
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int len=arr.length;
		for(int i=0;i<len;i++) {
			max=Math.max(arr[i], max);
			min=Math.min(arr[i], min);
		}
		if(min==max) {
			return 0;
		}
		boolean[] hasNum=new boolean[len+1];
		int[] maxs=new int[len+1];
		int[] mins=new int[len+1];
		for(int i=0;i<len;i++) {
			int position=bucket(arr[i],len,max,min);
			maxs[position]=hasNum[position]?Math.max(maxs[position], arr[i]):arr[i];
			mins[position]=hasNum[position]?Math.min(mins[position], arr[i]):arr[i];
			hasNum[position]=true;
		}
		int lastMax=maxs[0];
		int res=0;
		for(int i=1;i<hasNum.length;i++) {
			if(hasNum[i]) {
				int gap=mins[i]-lastMax;
				res=res<gap?gap:res;
				lastMax=maxs[i];
			}
		}
		return res;
	}
	
	public static int bucket(int num,int len,int max,int min) {
		return (int)(((num-min)*len)/(max-min));
	}
	
	public static int[] generateRandomArray(int size,int value) {
		int[] arr=new int[(int)((size+1)*Math.random())];
		for(int i=0;i<arr.length;i++) {
			arr[i]=(int)((value+1)*Math.random());
		}
		return arr;
	}
	
	
	public static void main(String[] args) {
		int size=20;
		int value=200;
		
		int[] arr=generateRandomArray(size, value);
		System.out.println(Arrays.toString(arr));
		System.out.println(maxGap(arr));
		
	}
}
