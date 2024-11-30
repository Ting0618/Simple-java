package algorithm241012.mygraph;

import java.util.*;

/**
 * @author ting
 */
public class MyGraph {

    private int[][] matrix;

    /**
     * description: TODO 103. traverse all node of a graph
     * create time: Nov 25 2024 09:22
     */
    public void traverse(Graph graph, int s, boolean[] visited) {
        if (graph == null) {
            return;
        }
        if (s < 0 || s >= graph.size()) {
            return;
        }
        // this node has already traversed
        if (visited[s]) {
            return;
        }
        // preorder place
        visited[s] = true;
        System.out.println("visit " + s);
        for (Edge e : graph.neighbors(s)) {
            traverse(graph, e.to, visited);
        }
    }

    /**
     * description: TODO 104 traverse all paths in the graph, find all paths from src to dest
     * create time: Nov 25 2024 10:09
     * boolean[] onPath: recording traversed paths, because there are cycles in a graph
     */
    public void traverse(Graph graph, int src, int dest, boolean[] onPath, List<Integer> path) {
        if (graph == null) {
            return;
        }
        if (src < 0 || src >= graph.size()) {
            return;
        }
        // it's a cycle
        if (onPath[src]) {
            return;
        }
        // 前序位置
        onPath[src] = true;
        path.add(src);
        if (src == dest) {
            System.out.println("find path: " + path);
        }
        for (Edge e : graph.neighbors(src)) {
            traverse(graph, e.to, dest, onPath, path);
        }
        // 后序位置
        path.removeLast();
        onPath[src] = false;

    }

    /**
     * description: TODO 105. lc797 All Paths From Source to Target, a directed acyclic graph
     * create time: Nov 25 2024 11:19
     */
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        allPathsSourceTarget2(graph, 0);
        return res;
    }

    public void allPathsSourceTarget2(int[][] graph, int id) {
        // add node to path
        path.add(id);
        // s is the last node
        int n = graph.length;
        if (id == n - 1) {
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        // Recursively visit all neighbors.
        for (int v : graph[id]) {
            allPathsSourceTarget2(graph, v);
            // path.removeLast(); this is wrong, it must after for loop,
            // if you put it here, every time it return to parents node,it will remove a node
        }
        // retrieve
        path.removeLast();
    }

    public void allPathsSourceTarget3(Graph graph, int id, List<Integer> path, List<List<Integer>> res) {
        path.add(id);
        // collect result
        if (id == graph.size() - 1) {
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        for (Edge e : graph.neighbors(id)) {
            allPathsSourceTarget3(graph, e.to, path, res);
        }
        path.removeLast();
    }

    /**
     * description: TODO  106. Traverse by Broad Search First, with weight
     * create time: Nov 26 2024 08:14
     */
    public void traverseBsf(Graph graph, int source) {
        // use to record if a node is visited
        boolean[] visited = new boolean[graph.size()];
        // use a queue
        Queue<State> queue = new LinkedList<>();
        // add the first node to queue, let weight = 0, because it's the first node
        queue.offer(new State(source, 0));
        visited[source] = true;
        while (!queue.isEmpty()) {
            State temp = queue.poll();
            int cur = temp.node;
            int weight = temp.weight;
            System.out.println("cur node: " + cur + ", weight: " + weight);
            List<Edge> neighbors = graph.neighbors(cur);
            for (Edge e : neighbors) {
                // only when a neighbor isn't be visited, it can be put into queue
                if (!visited[e.to]) {
                    queue.offer(new State(e.to, e.weight));
                    visited[e.to] = true;
                }
            }
        }
    }


    /**
     * description: TODO 107. lc207 Course Schedule
     * create time: Nov 26 2024 09:09
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // create a directed graph
        Map<Integer, List<Integer>> graph = changeToDe(prerequisites, numCourses);
        // determine if it has a cycle
        boolean res = hasCycle(graph);
        return !res;
    }

    /**
     * description: TODO reverse prerequisites to a directed graph
     */
    public Map<Integer, List<Integer>> changeToDe(int[][] prerequisites, int numCourses) {
        Map<Integer, List<Integer>> res = new HashMap<>(16);
        // 初始化图, 这个数组里面只放了有前修课程的课，没有的没放
        for (int i = 0; i < numCourses; i++) {
            res.put(i, new ArrayList<>());
        }
        // 添加依赖关系
        for (int[] item : prerequisites) {
            res.get(item[1]).add(item[0]);
        }
        return res;
    }

    /**
     * description: TODO  determine if a graph has cycle
     */
    boolean hasCycle = false;

    public boolean hasCycle(Map<Integer, List<Integer>> graph) {
        //traverse all node to see if a node has cycle
        boolean[] onPath = new boolean[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        List<Integer> path = new ArrayList<>();
        // to find all node's all paths
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            traverseHasCycle(graph, entry.getKey(), onPath, path, visited);
        }
        return hasCycle;
    }

    /**
     * description: TODO one of node's all paths
     */
    public void traverseHasCycle(Map<Integer, List<Integer>> graph, int source, boolean[] onPath, List<Integer> path, boolean[] visited) {
        path.add(source);
        if (hasCycle) {
            return;
        }
        if (onPath[source]) {
            hasCycle = true;
            return;
        }
        if (visited[source]) {
            return;
        }
        onPath[source] = true;
        visited[source] = true;
        for (Integer i : graph.get(source)) {
            traverseHasCycle(graph, i, onPath, path, visited);
        }
        path.removeLast();
        onPath[source] = false;
    }


    /**
     * description: TODO 108. lc210 Course Schedule 2
     * create time: Nov 26 2024 11:24
     */
    List<Integer> traverseList = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // create directed graph
        Map<Integer, List<Integer>> graph = changeToDe(prerequisites, numCourses);
        // determine if this graph has cycle
        boolean cycle = hasCycleForCourse(graph);
        if (cycle) {
            return new int[]{};
        }
        // topological sorting, after method traverseForCourse(), we get the traverseList
        Collections.reverse(traverseList);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = traverseList.get(i);
        }
        return res;
    }

    public boolean hasCycleForCourse(Map<Integer, List<Integer>> graph) {
        // traverse graph
        boolean[] visited = new boolean[graph.size()];
        boolean[] onPath = new boolean[graph.size()];
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            int node = entry.getKey();
            boolean cycle = traverseForCourse(graph, node, visited, onPath);
            if (cycle) {
                return true;
            }
        }
        return false;
    }

    /**
     * description: TODO traverse one of the node
     */
    public boolean traverseForCourse(Map<Integer, List<Integer>> graph, int node, boolean[] visited, boolean[] onPath) {
        if (visited[node]) {
            return false;
        }
        if (onPath[node]) {
            return true;
        }
        onPath[node] = true;

        if (graph.containsKey(node)) {
            for (Integer i : graph.get(node)) {
                boolean temp = traverseForCourse(graph, i, visited, onPath);
                if (temp) {
                    return true;
                }
            }
        }
        // postorder, recorde nodes 后序位置，只记录第一次访问的节点
        traverseList.add(node);
        visited[node] = true;
        onPath[node] = false;
        return false;
    }

    /**
     * description: TODO 109. Topological sorting, based on BSF
     * create time: Nov 27 2024 09:29
     */
    public List<Integer> topoSorting(int numNodes, int[][] edges) {
        // 构建图的邻接表, edges表示的是边的数组，比如[[1,0],[2,0],[3,1],[3,2]]
        Map<Integer, List<Integer>> graph = new HashMap<>(16);
        // 这个图的入度数组
        int[] inDegree = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] item : edges) {
            graph.get(item[1]).add(item[0]);
            inDegree[item[1]]++;
        }
        // 初始化队列：将所有入度为 0 的节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i : inDegree) {
            if (i == 0) {
                queue.offer(i);
            }
        }
        // 进行拓扑排序
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);
            // 更新邻居节点的入度,并将新的入度为0的节点加入queue
            for (Integer i : graph.get(node)) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        // 如果拓扑排序中节点数小于总节点数，说明有环
        if (topoOrder.size() < numNodes) {
            return new ArrayList<>();
        }
        return topoOrder;
    }

    /**
     * description: TODO lc207 Course Schedule, BFS version
     * create time: Nov 27 2024 10:10
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // Construct a directed graph with an adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>(16);
        // use to record in degree
        int[] inDegree = new int[numCourses];
        // Record the count of elements allowed into the queue
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] node : prerequisites) {
            graph.get(node[1]).add(node[0]);
            inDegree[node[0]]++;
        }
        // initialize queue
        Queue<Integer> queue = new LinkedList<>();
        // Place elements with an in-degree of 0 into the queue
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                // 正确地加入节点编号
                queue.offer(i);
            }
        }
        //
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            // Reduce the in-degree of nodes related to node by 1.
            for (int i : graph.get(node)) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }

    /**
     * description: TODO 110. lc277 Find the Celebrity
     * create time: Nov 28 2024 08:51
     */
    public int findCelebrity(int n) {
        int cand = 0;
        for (int other = 1; other < n; other++) {
            // if candidate knows other or other doesn't know candidate, which means other might be the celebrity
            if (knows(cand, other) || !knows(other, cand)) {
                cand = other;
            }
            // if not, we change other, because at this place, cand might be the celebrity
        }
        // determine if cand is celebrity, it is the last one, but not 100% sure it is celebrity,
        // maybe there is no celebrity
        for (int i = 0; i < n; i++) {
            // make sure others know candidate while candidate doesn't know others
            if (knows(cand, i) || !knows(i, cand)) {
                return -1;
            }
        }
        return cand;
    }

    /**
     * description: TODO if i knows j
     */
    public boolean knows(int i, int j) {
        if (i == j) {
            return false;
        }
        return matrix[i][j] == 1;
    }

    /**
     * description: TODO 111 lc200 Number of Islands
     * create time: Nov 28 2024 10:23
     */
    public int numIslands(char[][] grid) {
        // when encounter island, flood this islands
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // when we find an island
                if (grid[i][j] == '1') {
                    res++;
                    floodDfs(grid, i, j);
                }
            }
        }
        return res;
    }

    /**
     * description: TODO Starting from grid[i][]j, find all the connected islands and set their values to 0/2.
     */
    public void floodDfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // out of boundary
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        // if it's not island
        if (grid[i][j] != '1') {
            return;
        }
        // if it's island, flood it and the surrounding islands grid[i][j] = '0'
        // change it to something else it's better
        grid[i][j] = '2';
        // top
        floodDfs(grid, i - 1, j);
        // bottom
        floodDfs(grid, i + 1, j);
        // left
        floodDfs(grid, i, j - 1);
        // right
        floodDfs(grid, i, j + 1);
    }

    /**
     * description: TODO 112 lc1254 Number of Closed Islands
     * create time: Nov 28 2024 11:19
     */
    public int closedIsland(int[][] grid) {
        int res = 0;
        // flood all island that is at the boundary
        int m = grid.length;
        int n = grid[0].length;
        // flood top and bottom boundary
        for (int i = 0; i < m; i++) {
            floodDfs(grid, i, 0);
            floodDfs(grid, i, n - 1);
        }
        // flood left and right boundary
        for (int j = 0; j < n; j++) {
            floodDfs(grid, 0, j);
            floodDfs(grid, m - 1, j);
        }
        // find the rest of islands
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    floodDfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void floodDfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // out of boundary
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        // if it's not island
        if (grid[i][j] != 0) {
            return;
        }
        // if it's island, flood it and the surrounding islands grid[i][j] = '0'
        // change it to something else it's better
        grid[i][j] = 2;
        // top
        floodDfs(grid, i - 1, j);
        // bottom
        floodDfs(grid, i + 1, j);
        // left
        floodDfs(grid, i, j - 1);
        // right
        floodDfs(grid, i, j + 1);
    }

    /**
     * description: TODO 113. lc695 Max Area of Island --- DFS
     * create time: Nov 29 2024 08:48
     */
    int tempArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    maxAreaOfIslandDFS(grid, i, j);
                    res = Math.max(tempArea, res);
                    tempArea = 0;
                }
            }
        }
        return res;
    }

    public void maxAreaOfIslandDFS(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        // if it's not island
        if (grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        tempArea++;
        maxAreaOfIslandDFS(grid, i - 1, j);
        maxAreaOfIslandDFS(grid, i + 1, j);
        maxAreaOfIslandDFS(grid, i, j - 1);
        maxAreaOfIslandDFS(grid, i, j + 1);
    }

    /**
     * description: TODO 114. lc1905 Count Sub Islands
     * create time: Nov 29 2024 09:24
     */
    boolean isSubIsland = true;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid2.length, n = grid2[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    countSubIslandsDfs(grid1, grid2, i, j);
                    if (isSubIsland) {
                        res++;
                    }
                    // every island has a new start
                    isSubIsland = true;
                }
            }
        }
        return res;
    }

    public void countSubIslandsDfs(int[][] grid1, int[][] grid2, int i, int j) {
        int m = grid2.length, n = grid2[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (grid2[i][j] != 1) {
            return;
        }
        if (grid2[i][j] == 1 && grid1[i][j] != 1) {
            isSubIsland = false;
        }
        grid2[i][j] = 2;
        countSubIslandsDfs(grid1, grid2, i + 1, j);
        countSubIslandsDfs(grid1, grid2, i - 1, j);
        countSubIslandsDfs(grid1, grid2, i, j + 1);
        countSubIslandsDfs(grid1, grid2, i, j - 1);

    }

    /**
     * description: TODO 115. lc694 Count the Distinct Island
     * create time: Nov 29 2024 10:28
     */
    int numDistinctIslands(int[][] grid){
        int m = grid.length, n = grid[0].length;
        HashSet<String> islands = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder island = new StringBuilder();
                    numDistinctIslandsDfs(grid, i, j, 0, island);
                    islands.add(island.toString());
                }
            }
        }
        return islands.size();
    }
    public void numDistinctIslandsDfs(int[][] grid, int i, int j, int dir, StringBuilder island){
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        island.append(dir).append(",");
        // top
        numDistinctIslandsDfs(grid, i - 1, j, 1, island);
        // right
        numDistinctIslandsDfs(grid, i, j + 1, 2, island);
        // bottom
        numDistinctIslandsDfs(grid, i + 1, j, 3, island);
        // left
        numDistinctIslandsDfs(grid, i, j - 1, 4, island);
        // record retrieve path
        island.append(-dir).append(",");
    }


    /**
     * description: TODO 116. lc785 Is Graph Bipartite --- The colors of every two adjacent nodes are different
     * create time: Nov 30 2024 09:34
     */
    boolean isBipartite;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        // it's used to store the color of each node, true and false represent different colors
        boolean[] colors = new boolean[n];
        isBipartite = true;
        // Graph traversal requires calling the traversal method one node at a time
        for(int i = 0; i < n; i++){
            // if this node is not visited, then we traverse it
            if(!visited[i]){
                isBipartiteDfs(graph, i, visited, colors);
            }
        }
    return isBipartite;
    }
    public void isBipartiteDfs(int[][] graph, int node, boolean[] visited, boolean[] colors){
        // if it's not bipartite
        if(!isBipartite){
            return;
        }
        visited[node] = true;
        for(int relateNode: graph[node]){
            if(!visited[relateNode]){
                colors[relateNode] = !colors[node];
                isBipartiteDfs(graph, relateNode, visited, colors);
            } else {
                if(colors[relateNode] == colors[node]){
                    isBipartite = false;
                    return;
                }
            }
        }
    }
    
    
    /**
     * description: TODO 117. lc886 Possible Bipartite
     * create time: Nov 30 2024 10:41
     */



}
