package algorithm.two;

import java.util.Arrays;

public class CreateRandomSort {
	public static void main(String[] args) {
		sort(5,10);
	}
	
	/**
	 * 返回在[0,n-1]范围内选出m个元素不重复的、元素有序的数组
	 * @param m
	 * @param n
	 * @return
	 */
	public static int[] sort(int m, int n){
		int[] result = new int[m];
		for(int i=0;i<n;i++){
			int random = (int)(Math.random()*1000);
			System.out.println(random);
			int value = random%n;
			System.out.println("value:"+value+",m:"+m+",n:"+n);
			if(value<m){
				result[result.length-m] = n;
				System.out.println(Arrays.toString(result));
				m--;
				n--;
			}
		}
		return result; 
	}
}
