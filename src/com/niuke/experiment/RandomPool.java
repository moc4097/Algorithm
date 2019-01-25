package com.niuke.experiment;

import java.util.HashMap;
import java.util.Map;

public class RandomPool {
	private static Map<String,Integer> map1;
	private static Map<Integer,String> map2;
	private static int index=0;
	
	public RandomPool() {
		map1=new HashMap<String, Integer>();
		map2=new HashMap<Integer, String>();
	}
	
	public static void insert(String key) {
		if(!map1.containsKey(key)) {
			map1.put(key, index);
			map2.put(index, key);
			index++;
		}else {
			throw new RuntimeException("exist the key");
		}
		
	}
	
	public static void delete(String key) {
		if(map1.containsKey(key)) {
			String lastKey=map2.get(map2.size()-1);
			int deleteIndex=map1.get(key);
			map1.remove(key);
			map1.put(lastKey, deleteIndex);
			map2.remove(map2.size()-1);
			map2.put(deleteIndex, lastKey);
		}else {
			throw new RuntimeException("not exist the key");
		}
	}
	
	public static String getRandom() {
		int size=map2.size();
		//System.out.println(map2.get(2));
		int random=(int)(size*Math.random());
		return map2.get(random);
	}
	
	public static void main(String[] args) {
		RandomPool rp=new RandomPool();
		rp.insert("key1");
		rp.insert("key2");
		rp.insert("key3");
		
		System.out.println(getRandom());
	}
}
