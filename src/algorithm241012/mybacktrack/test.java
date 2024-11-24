package algorithm241012.mybacktrack;

import java.util.List;

/**
 * @author ting
 */
public class test {
    public static void main(String[] args) {
        MyBacktrack myBacktrack = new MyBacktrack();
        int[] nums = {1,2,1};
//        List<String> res =
        List<List<Integer>> res = myBacktrack.permuteUnique(nums);
        for(List<Integer> list: res){
            System.out.println(list.toString());
        }

//        for(String list: res){
//            System.out.println(list);
//        }

    }
}
