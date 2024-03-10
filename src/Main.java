import Algorithm.MyArrays;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] nums = {-345,-45,-2,4,24,453,3453,6548};
        int target = 43;
        MyArrays myArrays = new MyArrays();
        int result = -1;
        result = myArrays.normal(nums, target);
        System.out.println(result);
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