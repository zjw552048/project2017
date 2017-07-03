package algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
	/**
	 * 冒泡排序
	 * 比较两个相邻的元素，将值大的元素交换至右端。重复比较直至有序。
	 * @param nums
	 * @return
	 */
	public static int[] sort(int[] nums){
		int max;
		//外层循环控制排序趟数
		for(int i=0;i<nums.length-1;i++){
			//内层循环控制每一趟排序多少次
			for(int j=0;j<nums.length-1-i;j++){
				if(nums[j]>nums[j+1]){
					max = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = max; 
				}
			}
		}
		return nums;
	}
	
	public static void main(String[] args) {
    	int[] nums = new int[2000];
    	for(int i=0;i<2000;i++){
    		nums[i] = (int) (Math.random()*2000);
    	}

    	long start = System.currentTimeMillis();
    	BubbleSort.sort(nums);
        System.out.println(Arrays.toString(nums));
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }
}
