package com.kituri.tankmmdatabase.utils;

public class RandomUtils {

	static public int randomMinMax(int min, int max){
		//产生随机的1 - 10的数字 
		return (int)(Math.random() * max + min);
	}	
	
	static public String randomMinMax(String[] strings){
		//产生随机的1 - 10的数字 
		return strings[randomMinMax(0, strings.length - 1)];
	}	
	
	static public int randomMinMax(int[] integers){
		//产生随机的1 - 10的数字 
		return integers[randomMinMax(0, integers.length - 1)];
	}
	
}
