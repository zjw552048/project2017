package algorithm.sort;

public class FastSort {
	/**
	 * 快速排序，
	 * 把整个序列看做一个数组，把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；
	 * 交换了以后再和小的那端比，比它小不交换，比他大交换。
	 * 这样循环往复，一趟排序完成，左边就是比中轴小的，右边就是比中轴大的，然后再用分治法，分别对这两个独立的数组进行排序。
	 * @param src
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMiddle(int[] src, int start, int end) {  
        int tmp = src[start];  
        while (start < end) {  
            while (start < end && src[end] >= tmp) {  
                end--;  
            }  
            src[start] = src[end];  
            while (start < end && src[start] <= tmp) {  
                start++;  
            }  
            src[end] = src[start];  
        }  
        src[start] = tmp;  
        return start;  
    }
	public static void sort(int[] list, int low, int high) {  
        if (low < high) {  
            int middle = getMiddle(list, low, high);  //将list数组进行一分为二  
            sort(list, low, middle - 1);        //对低字表进行递归排序  
            sort(list, middle + 1, high);       //对高字表进行递归排序  
        }  
    }  
}
