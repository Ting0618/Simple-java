package Algorithm.mycollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by: Ting
 * description: TODO
 * create time: 26/03/2024 15:47
 */
public class Test {
    public static void main(String[] args) {
        MyCollections myCollections = new MyCollections();
        String s = "anagram";
        String t = "nagaram";
//        System.out.println(myHash.isAnagram(s,t));
        int[] nums1 = {5,0,4,8,2};
        int[] nums2 = {0,0,0,0};
        int[] temp = myCollections.intersection(nums1,nums2);
//        System.out.println(Arrays.toString(temp));

//        boolean res = myCollections.isHappy(2);
//        int[] res = myCollections.twoSum2(nums1,5);
//        System.out.println(Arrays.toString(res));

//        boolean res = myCollections.canConstruct2("va","aab");
//        System.out.println(res);
        int[] nums3 = {-2,-1,-1,1,1,2,2};
        List<List<Integer>> res = myCollections.fourSum(nums3,0);
        System.out.println(res.toString());
    }
}
