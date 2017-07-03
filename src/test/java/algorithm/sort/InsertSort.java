package algorithm.sort;

import java.util.Arrays;

public class InsertSort {
	/**
	 * 插入排序
	 * 通常，插入排序呈现出二次排序算法中的最佳性能。
	 * 对于具有较少元素（如n<=15）的列表来说，二次算法十分有效。
	 * 在列表已被排序时，插入排序是线性算法O(n)。
	 * 在列表“近似排序”时，插入排序仍然是线性算法。
	 * 在列表的许多元素已位于正确的位置上时，就会出现“近似排序”的条件。
	 * @param a
	 */
    public static void sort(int[] a) {
        int i, j, insertNote;// 要插入的数据
        for (i = 1; i < a.length; i++) {// 从数组的第二个元素开始循环将数组中的元素插入
            insertNote = a[i];// 设置数组中的第2个元素为第一次循环要插入的数据
            j = i;
            while (j > 0 && insertNote < a[j-1]) {
                a[j] = a[j-1];// 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                j--;
            }
            a[j] = insertNote;// 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
        }
    }

    public static void main(String[] args) {
    	int[] nums = new int[2000];
    	for(int i=0;i<2000;i++){
    		nums[i] = (int) (Math.random()*2000);
    	}

    	long start = System.currentTimeMillis();
    	InsertSort.sort(nums);
        System.out.println(Arrays.toString(nums));
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }
} 