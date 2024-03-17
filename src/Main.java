import Algorithm.MyArrays;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        MyArrays myArrays = new MyArrays();
        int res = myArrays.minSubArrayLen(19,nums);
        System.out.println(res);
//        int[] re2 = myArrays.sortedSquares(nums);
//        System.out.println(Arrays.toString(re2));
    }

    static int nTimes(int x, int n){
        int result = 1;
        for(int i = 0; i < n; i++){
            result = result * x;
        }
        return result;
    }




    public static void function3(long n) {
        System.out.println("o(nlogn)算法");
        long k = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 1; j < n; j = j * 2) { // 注意这里j=1
                k++;
            }
        }
    }
}