package algorithm241012.myhash;

public class Test {
    public static void main(String[] args) {
        MyHash myHash = new MyHash();
        int[] nums1 = {1,0,-1,0,-2,2};
        int[] nums2 = {2,2,2,2,2};
        int[] nums3 = {-1,1};
        int[] nums4 = {1,-1};
        System.out.println(myHash.fourSum(nums2, 8));
    }
}
