package algorithm241012.myarrays;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyArrays myArrays = new MyArrays();
        int[][] nums = {{1,1}};
        int[] nums2 = {1,1,2};
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

        boolean res = myArrays.searchMatrix(nums, 2);
        System.out.println(res);
    }
}
