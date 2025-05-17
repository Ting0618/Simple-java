package algorithm241012.myGreedy;

import java.util.List;

/**
 * @author tingwong
 */
public class Test {

    public static void main(String[] args) {
        MyGreedy myGreedy = new MyGreedy();
        int[] nums = {5,5,10,10,20};
        int[] nums2 = {-1};
        String str = "ababcbacadefegdehijhklij";
        List<Integer> jump = myGreedy.partitionLabels(str);
        System.out.println(jump);
    }
}
