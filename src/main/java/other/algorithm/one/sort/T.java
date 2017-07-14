package other.algorithm.one.sort;

import java.util.Arrays;

public class T {
	public static void main(String[] args) {
//		System.out.println(Integer.toBinaryString(2));
//		System.out.println(Integer.toBinaryString((2 >> 1) & 1));
//		System.out.println(Integer.toBinaryString(8));
//		System.out.println(Integer.toBinaryString(8 | (1<<2)));
//		System.out.println(Integer.toBinaryString((8 >> 3) & 1));
//		System.out.println(Integer.toBinaryString(-2147483648 >> 31));
//		System.out.println();
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
//		System.out.println();
//		System.out.println(Integer.MIN_VALUE >> 2);
//		System.out.println(-2147483648/4);
//		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >> 2));
//		System.out.println();
//		System.out.println(Integer.toBinaryString((Integer.MIN_VALUE >> 31) & 1));
		int max = 10000000;
		int size = 32;
		double arrayLength = max/size + (max%size==0?0:1);
		System.out.println(arrayLength);
		System.out.println(arrayLength*4/1024/1024);
//		int[] orginal = new int[]{5,888,4,2,6,7,3,10,567,456,234,123,66666,55555,3333333,8908764};
//		System.out.println(Arrays.toString(sort(orginal)));
	}
	
	public static int[] sort(int[] orginal){
		int max = 10000000;
		int size = 32;
		int arrayLength = max/size + (max%size==0?0:1);
		int[] datas = new int[arrayLength];
		int[] result = new int[orginal.length];
		
		for(int i=0;i<orginal.length;i++){
			int index = orginal[i]/size + (orginal[i]%size==0?-1:0);
			int pos = (orginal[i]%size==0?32:orginal[i]%size);
			datas[index] = datas[index] | (1 << pos-1);
			//System.out.println(Arrays.toString(datas));
		}
		int k=0;
		for(int i=0;i<datas.length;i++){
			int num = datas[i];
			for(int j=1;j<=32;j++){
				int posValue = (num >> (j-1)) & 1;
				if(posValue == 1){
					System.out.println(i * size + j);
					result[k] = i * size + j;
					k++;
				}
			}
		}
		return result;
	}
}
