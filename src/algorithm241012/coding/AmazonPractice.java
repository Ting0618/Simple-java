package algorithm241012.coding;

import java.util.*;

public class AmazonPractice {

    public int findMostOnes(int[][] matrix) {
        // how many rows
        int m = matrix[0].length;
        // how many columns
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[j][i] == 1) {
                    return j;
                }
            }
        }
        return -1;
    }


    /**
     * description: TODO lc347 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * create time: Jan 01 2025 14:59
     */
    public int[] topKFrequent(int[] nums, int k) {
        // calculate frequency of each element
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // increasing
                return o1[1] - o2[1];
            }
        };
        // use a priority queue to sort and find top k
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comparator);
        // traverse map, save k elements
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] item = new int[2];
            // element
            item[0] = entry.getKey();
            // frequency
            item[1] = entry.getValue();
            minHeap.offer(item);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i > -1; i--) {
            res[i] = Objects.requireNonNull(minHeap.poll())[0];
        }
        return res;
    }


    public List<int[]> findKCloset(int[][] points, int[] p, int k) {
        List<int[]> res = new ArrayList<>();
        Comparator<int[]> comparator = new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double distance1 = (p[0] - o1[0]) * (p[0] - o1[0]) + (p[1] - o1[1]) * (p[1] - o1[1]);
                double distance2 = (p[0] - o2[0]) * (p[0] - o2[0]) + (p[1] - o2[1]) * (p[1] - o2[1]);
                return Double.compare(distance1, distance2);
            }
        };
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comparator);
        // traverse points and calculate distance
        for (int[] point : points) {
            minHeap.offer(point);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // find k points
        while (!minHeap.isEmpty()) {
            List<int[]> item = new ArrayList<>();
            res.add(minHeap.poll());
        }
        return res;
    }

    public List<int[]> findTopApiUsers(int[][] apis) {
        List<int[]> res = new ArrayList<>();
        // use a map to record the sum transaction of each id
        // key is id, value is sum of transaction
        Map<Integer, Integer> map = new HashMap<>();
        int id;
        for (int[] api : apis) {
            id = api[0];
            map.put(id, map.getOrDefault(id, 0) + api[2]);
        }

        // use a priority queue to record all data (max heap)
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        };
        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] temp = new int[2];
            temp[0] = entry.getKey();
            temp[1] = entry.getValue();
            queue.offer(temp);
        }
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    /**
     * description: TODO Given an nxn grid of 1s and 0s, return the number of islands in the input. 1 means island
     * create time: Jan 02 2025 14:43
     */
    public int getNumberOfIslands(int[][] binaryMatrix) {
        int res = 0;
        // traverse matrix
        // columns
        int n = binaryMatrix[0].length;
        // rows
        int m = binaryMatrix.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (binaryMatrix[i][j] == 1) {
                    // to find islands
                    getNumberOfIslandsDfs(binaryMatrix, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void getNumberOfIslandsDfs(int[][] binaryMatrix, int i, int j) {
        int m = binaryMatrix.length;
        int n = binaryMatrix[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n){
            return;
        }
        if(binaryMatrix[i][j] == 0 || binaryMatrix[i][j] == 2){
            return;
        }
        if(binaryMatrix[i][j] == 1){
            binaryMatrix[i][j] = 2;
        }
        // traverse the top, bottom, left, right
        getNumberOfIslandsDfs(binaryMatrix, i - 1, j);
        getNumberOfIslandsDfs(binaryMatrix, i + 1, j);
        getNumberOfIslandsDfs(binaryMatrix, i, j - 1);
        getNumberOfIslandsDfs(binaryMatrix, i, j + 1);
    }

    /**
     * description: TODO calculate each element, by columns
     */
    public int trap(int[] height){
        int res = 0;
        int len = height.length;
        // max height of each element on the left
        int[] leftHeight = new int[len];
        leftHeight[0] = height[0];
        for(int i = 1; i < len; i++){
            leftHeight[i] = Math.max(leftHeight[i - 1], height[i]);
        }
        // max height of each element on the right
        int[] rightHeight = new int[len];
        rightHeight[len - 1] = height[len - 1];
        for(int i = len - 2; i >= 0; i--){
            rightHeight[i] = Math.max(rightHeight[i + 1], height[i]);
        }
        // calculate rainwater
        for(int i = 0; i < len; i++){
            int curHeight = Math.min(leftHeight[i], rightHeight[i]) - height[i];
            // when height[i] is the max value of the array, curHeight can be a negative value
            if(curHeight > 0){
                res += curHeight;
            }
        }
        return res;
    }

    /**
     * description: TODO using a decreasing monotonic stack
     */
    public int trap2(int[] height){
        int area = 0;
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < height.length; i++){
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]){
                if(height[stack.peek()] == height[i]){
                    stack.pop();
                    break;
                }
                // height[i] > stack.peek()
                int lowest = stack.pop();
                if (!stack.isEmpty()){
                    int left = stack.peek();
                    int width = i - left - 1;
                    int curHeight = Math.min(height[i], height[left]) - height[lowest];
                    area += width * curHeight;
                }
            }
            stack.push(i);
        }
        return area;
    }

    /**
     * description: TODO
     * create time: Jan 02 2025 20:11
     */
    public int[] nextGreaterElement(int[] nums){
        int len = nums.length;
        int[] res = new int[len];
        // 初始化结果数组为 -1（默认没有更大元素）
        Arrays.fill(res, -1);
        // use a decreasing monotonic stack
        Deque<Integer> deStack = new LinkedList<>();
        for(int i = 0; i < len; i++){
            while (!deStack.isEmpty() && nums[i] > nums[deStack.peek()]){
                int top = deStack.pop();
                res[top] = nums[i];
            }
            deStack.push(i);
        }
//        while (!deStack.isEmpty()){
//            int top = deStack.pop();
//            res[top] = -1;
//        }

        return res;
    }
}
