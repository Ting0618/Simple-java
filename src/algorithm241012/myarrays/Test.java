package algorithm241012.myarrays;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyArrays myArrays = new MyArrays();
        int[][] nums = {{2,3},{2,6},{7,9}};
        int[] nums2 = {2,2,2,2,2};
//        int[] resArr = myArrays.sortedSquares(nums);
//        for(int i = 0; i < resArr.length; i++){
//            System.out.println(resArr[i]);
//        }
        String s1 = "adc";
        String s2 = "dcda";
        String[] T = {"PGP", "M"};
        int[] D = {2, 5};
        int[][] matrix = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        int res = myArrays.findMostOnes(matrix);
        System.out.println(res);
    }
}
