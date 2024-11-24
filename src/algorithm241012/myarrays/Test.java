package algorithm241012.myarrays;

public class Test {
    public static void main(String[] args) {
        MyArrays myArrays = new MyArrays();
        int[][] nums = {{2,3},{2,6},{7,9}};
        int[] nums2 = {5};
//        int[] resArr = myArrays.sortedSquares(nums);
//        for(int i = 0; i < resArr.length; i++){
//            System.out.println(resArr[i]);
//        }

//        myArrays.firstMissingPositive(nums2);
        int res = myArrays.solution(35);
        System.out.println(res);
    }
}
