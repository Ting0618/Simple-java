package algorithm241012.mystrings;

import algorithm241012.myarrays.MyArrays;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyArrays myArrays = new MyArrays();
        MyString myString = new MyString();
        int[] nums = {5};
        int fin = myArrays.binarySearch(nums,5);
        String s = "abcdefghijklmnopq";
        String res = "";
        res = myString.reverseStr(s, 3);
        res = myString.reverseWords("  hello world  ");
        List<Integer> resList = myString.findAnagrams2("cbaebabacd","abc");
        for(int i: resList){
            System.out.println(i);
        }

    }
}
