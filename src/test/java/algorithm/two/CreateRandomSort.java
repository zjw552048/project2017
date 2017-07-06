package algorithm.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class CreateRandomSort {
	public static void main(String[] args) {
		//System.out.println(Math.pow(2, 32));
		int m = 100;
		int n = (int)Math.pow(2, 20);
		int times = 20;
		while(times>0){
			System.out.println(Arrays.toString(sort(m, n)));
			System.out.println(sort2(m, n));
			times--;
		}
	}
	
	/**
	 * 从0~n-1随机输出m个有序整数
	 * 且m>n
	 * @param m
	 * @param n
	 * @return
	 */
	public static int[] sort(int m, int n){
		long start = System.currentTimeMillis();
		
		int[] result = new int[m];
		int temp = n;
		for(int i=0;i<temp;i++){
			int random = (int)(Math.random()*(n-i));
			int value = random%(n-i);
			if(value<m){
				result[result.length-m] = i;
				m--;
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		return result; 
	}
	
	public static List<Integer> sort2(int m, int n){
		long start = System.currentTimeMillis();
		
		List<Integer> result = new ArrayList<Integer>();
		while(m>0){
			Integer random = (int)(Math.random()*n);
			boolean flag = result.contains(random);
			if(!flag){
				result.add(random);
				m--;
			}
		}
		Collections.sort(result);
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		return result;
	}
}
