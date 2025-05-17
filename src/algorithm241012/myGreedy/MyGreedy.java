package algorithm241012.myGreedy;

import java.util.*;

/**
 * @author tingwong
 */
public class MyGreedy {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int maxReach = 0, i = 0;
        while(i < len){

            // Update the maximum reachable position
            maxReach = Math.max(maxReach, i + nums[i]);
            if(maxReach >= len - 1){
                return true;
            }
            // If we can't reach the current position, return false
            if(maxReach == i){
                return false;
            }
            i = maxReach;
        }
        return true;
    }

    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0, furthest = 0;
        // 记录当前能跳跃到的位置的边界下标
        int border = 0;
        for(int i = 0; i < len - 1; i++){
            furthest = Math.max(furthest, i + nums[i]);
            // 如果到达了边界，那么一定要跳了，下一跳的边界下标就是之前统计的最优情况 furthest，并且步数加1
            if(i == border){
                step++;
                border = furthest;
            }
        }
        return step;
    }

    /**
     * @Description TODO g是第i人饥饿值，s是能量值，求能满足多少个人的饥饿值
     * @Date 2025/3/10 11:27
     **/
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int i = 0, j = 0;
        while (i < g.length && j < s.length){
            // 如果能量值大于等于当前的饥饿值，发下一个
            if(s[j] >= g[i]){
                i++;
                j++;
                count++;
            } else if(s[j] < g[i]){
                // 如果能量值小于饥饿值，去找下一个更大的能量值
                j++;
            }
        }
        return count;
    }

    public boolean wiggleMaxLength(int[] nums) {
        if(nums.length == 1){
            return false;
        }
        // 代码存在的问题：可以得到正确的数量，但是无法得到正确的数字，如：{1,17,5,10,13,15,10,5,16,8}，会有两个10
        // 初始的时候因为不知道是增还是减，就设为0，当为1时表示增，为-1时表示减
        int reverse = 0;
        for(int i = 1; i < nums.length; i++){
            // 大于0，趋势是增，那么前一个趋势应该是减，但是考虑初始情况，就是当上一个趋势不是增的时候
            if(nums[i] - nums[i - 1] > 0 && reverse != 1){
                reverse = 1;
                System.out.print(nums[i] + ",");
            } else if(nums[i] - nums[i - 1] < 0 && reverse != -1){
                reverse = -1;
                System.out.print(nums[i] + ",");
            } else {
                System.out.println(nums[i]);
//                return false;
            }
        }
        return true;
    }


    public int maxSubArray(int[] nums) {
        // 当sum变成负数时，就重新累加
        int sum = 0, res = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            res = Math.max(res, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        int sum = 0;
        int[] prof = new int[prices.length - 1];
        for(int i = 1; i < prices.length; i++){
            prof[i - 1] = prices[i] - prices[i - 1];
        }
        for(int m: prof){
            if(m > 0){
                sum += m;
            }
        }
        return sum;
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 0 && count < k){
                nums[i] = -nums[i];
                count++;
            }
        }
        // 如果还有反转次数，就反转最小的那个数
        if(count < k){
            Arrays.sort(nums);
            while(count < k){
                nums[0] = -nums[0];
                count++;
            }
        }
        int sum = 0;
        // 没有翻转次数了，计算和
        for(int num: nums){
            sum += num;
        }
        return sum;
    }


    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int bill: bills){
            // 需要找零
            if(bill == 10){
                // 没有零钱或者没有5块的返回false
                if(map.isEmpty() || !map.containsKey(5) || map.get(5) == 0){
                    return false;
                }
                // 有5块的零钱
                if(map.get(5) >= 1){
                    map.put(10, map.getOrDefault(10, 0) + 1);
                    map.put(5, map.get(5) - 1);
                }
            } else if(bill == 20){
                // 需要的找零
                int need = 15;
                if(map.isEmpty()){
                    return false;
                }
                if(map.containsKey(10) && map.get(10) > 0){
                    need = 5;
                    map.put(10, map.get(10) - 1);
                }
                if(map.containsKey(5) && map.get(5) > 0){
                    int have = map.get(5);
                    if(need == 15 && have < 3){
                        return false;
                    }
                    if(need == 15 && have >= 3){
                        map.put(20, map.getOrDefault(20, 0) + 1);
                        map.put(5, map.get(5) - 3);
                    }
                    if(need == 5 && have < 1){
                        return false;
                    }
                    if(need == 5 && have >= 1){
                        map.put(20, map.getOrDefault(20, 0) + 1);
                        map.put(5, map.get(5) - 1);
                    }
                }else{
                    return false;
                }
            } else {
                map.put(5, map.getOrDefault(5, 0) + 1);
            }
        }
        return true;
    }


    public List<Integer> partitionLabels(String s) {
        // 找到该字母在字符串中的最远的位置
        int[] farest = new int[26];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            farest[c - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            int far = farest[s.charAt(i) - 'a'];
            if(i == farest[s.charAt(i) - 'a']){
                res.add(i + 1);
            }
        }
        if(res.isEmpty()){
            res.add(s.length());
        }
        return res;
    }


}
