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
    int numDistinctIslands(int[][] grid) {
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

    public void numDistinctIslandsDfs(int[][] grid, int i, int j, int dir, StringBuilder island) {
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
        for (int i = 0; i < n; i++) {
            // if this node is not visited, then we traverse it
            if (!visited[i]) {
                isBipartiteDfs(graph, i, visited, colors);
            }
        }
        return isBipartite;
    }

    public void isBipartiteDfs(int[][] graph, int node, boolean[] visited, boolean[] colors) {
        // if it's not bipartite
        if (!isBipartite) {
            return;
        }
        visited[node] = true;
        for (int relateNode : graph[node]) {
            if (!visited[relateNode]) {
                colors[relateNode] = !colors[node];
                isBipartiteDfs(graph, relateNode, visited, colors);
            } else {
                if (colors[relateNode] == colors[node]) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }

    /**
     * description: TODO 117, lc323 无向连通图中的联通数量
     * create time: Dec 01 2024 09:43
     */
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] temp : edges) {
            uf.union(temp[0], temp[1]);
        }
        return uf.count();
    }

    /**
     * description: TODO 118. lc130 Surrounded Regions
     * create time: Dec 01 2024 10:05
     */
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        // Reserve an extra spot for the dummy
        UnionFind uf = new UnionFind(m * n + 1);
        int dummy = m * n;
        // Connect the O’s in the first and last raws to the dummy
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                uf.union(dummy, i * n);
            }
            if (board[i][n - 1] == 'O') {
                uf.union(i * n + n - 1, dummy);
            }
        }

        // Connect the O’s in the first and last columns to the dummy
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                uf.union(dummy, j);
            }
            if (board[m - 1][j] == 'O') {
                uf.union(n * (m - 1) + j, dummy);
            }
        }
        // direction array
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        // Connect the remaining O’s to each other, note: i,j start with 1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board[i][j] == 'O') {
                    // Link this O to the adjacent O’s in all four directions: up, down, left, and right.
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }
        // Replace any O’s that are not linked to the dummy
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * description: TODO 119 lc990 Satisfiability of Equality Equations
     * create time: Dec 01 2024 10:58
     */
    public boolean equationsPossible(String[] equations) {
        // create a UnionFind class
        UnionFind uf = new UnionFind(26);
        // Form connected components with identical letters
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        // Verify whether the inequalities disrupt the connectivity formed by equalities
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                // If the equality relationship holds, it results in a logical conflict
                if (uf.connected(x - 'a', y - 'a'))
                    return false;
            }
        }
        return true;
    }

    /**
     * description: TODO 120 lc261 Check if the structure created by these edges qualifies as a tree
     * create time: Dec 01 2024 11:33
     */
    public boolean validTree(int n, int[][] edges) {
        // initialization, from 0 to n-1, in total n nodes
        UnionFind uf = new UnionFind(n);
        // Traverse all edges and connect the two nodes that form each edge
        for (int[] item : edges) {
            int u = item[0];
            int v = item[1];
            // u and v are already connected
            if (uf.connected(u, v)) {
                return false;
            }
            // otherwise connect u and v
            uf.union(u, v);
        }
        return true;
    }

    /**
     * description: TODO 121 lc1135 Connect all cities at the lowest cost
     * create time: Dec 02 2024 09:04
     */
    public int minimumCost(int n, int[][] connections) {
        int cost = 0;
        // use unionFind to check if it is a tree
        UnionFind uf = new UnionFind(n);
        // sort connection array by ascend order
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        // iterator
        for (int[] edge : connections) {
            // if edge[0] and edge[1] are already connected
            if (uf.connected(edge[0], edge[1])) {
                // here can't return, has to continue,when the condition is true, which means these two nodes has been
                // connected
                // return -1, means these edges can't structure a tree
                continue;
            }
            uf.union(edge[0], edge[1]);
            cost = +edge[2];
        }
        // because n starts from 1, so node 0 is extra
        return uf.count() == 2 ? cost : -1;
    }


    /**
     * description: TODO 122 lc1584 Min cost to Connect all Points
     * create time: Dec 02 2024 09:42
     */
    public int minCostConnectPoints(int[][] points) {
        // Create an edge-weight array connecting one point to all other nodes
        List<int[]> edges = new ArrayList<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // point[i] to point[j]
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                // 用坐标点在 points 中的索引表示坐标点
                edges.add(new int[]{
                        i, j, Math.abs(xi - xj) + Math.abs(yi - yj)
                });
            }
        }

        // 将边按照权重从小到大排序
        edges.sort((a, b) -> {
            return a[2] - b[2];
        });

        // 执行 Kruskal 算法
        int mst = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 若这条边会产生环，则不能加入 mst
            if (uf.connected(u, v)) {
                continue;
            }
            // 若这条边不会产生环，则属于最小生成树
            mst += weight;
            uf.union(u, v);
        }
        return mst;
    }


    /**
     * description: TODO 123 lc752 Open the Lock
     * create time: Dec 03 2024 08:49
     */
    public int openLock(String[] deadends, String target) {
        // put dead passwords into a set
        Set<String> deads = new HashSet<>();
        // record already be visited password
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // record step
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                // 判断是否到达终点
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                // 将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 在这里增加步数
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    /**
     * description: TODO 将 s[j] 向上拨动一次
     */
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }

    /**
     * description: TODO 将 s[i] 向下拨动一次
     */
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    /**
     * description: TODO 124. lc127 Word Ladder
     * create time: Dec 03 2024 09:46
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // it is used to
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        // Store each new string obtained by changing one character at a time, provided it exists in the container.
        Queue<String> queue = new LinkedList<>();
        // Record the path length from the starting word to the current word
        Map<String, Integer> visitMap = new HashMap<>(16);
        queue.offer(beginWord);
        visitMap.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int path = visitMap.get(cur);
            // if this is the endWord
            if (Objects.equals(cur, endWord)) {
                return path;
            }
            // change each position of cur with 26 letters
            char[] ch = cur.toCharArray();
            for (int i = 0; i < cur.length(); i++) {
                // 保存原始字符
                char original = ch[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    ch[i] = c;
                    String newWord = new String(ch);
                    // if new word is contained in set and is not visited
                    if (set.contains(newWord) && !visitMap.containsKey(newWord)) {
                        visitMap.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
                ch[i] = original;
            }
        }
        return 0;
    }

    /**
     * description: TODO 125 给定一个由 1（陆地）和 0（水）组成的矩阵，你最多可以将矩阵中的一格水变为一块陆地，在执行了此操作之后，矩阵中最大的岛屿面积是多少。
     * create time: Dec 04 2024 08:37
     */
    int islandArea = 0;
    public int largestArea(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int keyName = 2;
        int res = 0;
        Map<Integer, Integer> areaMap = new HashMap<>(16);
        // first traverse to find the area of islands
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    findAreaDfs(grid, keyName, i, j);
                    areaMap.put(keyName, islandArea);
                    islandArea = 0;
                    keyName++;
                }
            }
        }
        // is case all the cells are 1
        if(areaMap.size() == 1 && areaMap.get(2) == m * n){
            return m * n;
        }
        // traverse all 0 to find the biggest area
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // if it's water, change it to land and sum up it's top, bottom, left, right islands area
                if(grid[i][j] == 0){
                    int curArea = 1;
                    Set<Integer> seen = new HashSet<>();
                    // top
                    if(i - 1 >= 0 && grid[i - 1][j] > 1) {
                        seen.add(grid[i - 1][j]);
                        int adjArea = areaMap.get(grid[i - 1][j]);
                        curArea += adjArea;
                    }
                    // bottom
                    if(i + 1 < m && grid[i + 1][j] > 1 && seen.add(grid[i + 1][j])){
                        int adjArea = areaMap.get(grid[i + 1][j]);
                        curArea += adjArea;
                    }
                    // left
                    if(j - 1 >= 0 && grid[i][j - 1] > 1 && seen.add(grid[i + 1][j])){
                        int adjArea = areaMap.get(grid[i][j - 1]);
                        curArea += adjArea;
                    }
                    // right
                    if(j + 1 < n && grid[i][j + 1] > 1 && seen.add(grid[i + 1][j])){
                        int adjArea = areaMap.get(grid[i][j + 1]);
                        curArea += adjArea;
                    }
                    res = Math.max(res, curArea);
                }
            }
        }
        return res;
    }
    /**
     * description: TODO find out all islands and change islands to a keyName
     */
    public void findAreaDfs(int[][] grid, int keyName, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n){
            return;
        }
        if(grid[i][j] != 1){
            return;
        }
        islandArea++;
        grid[i][j] = keyName;
        findAreaDfs(grid, keyName, i - 1, j);
        findAreaDfs(grid, keyName, i + 1, j);
        findAreaDfs(grid, keyName, i, j + 1);
        findAreaDfs(grid, keyName, i, j - 1);
    }


    /**
     * description: TODO 126 给定一个有向图，包含 N 个节点，节点编号分别为 1，2，...，N。现从 1 号节点开始，
     * 如果可以从 1 号节点的边可以到达任何节点，则输出 1，否则输出 -1。
     * create time: Dec 10 2024 09:12
     */
    public int reachAll(Graph graph){
        int size = graph.size();
        boolean[] visited = new boolean[size];
        Arrays.fill(visited, false);
        // traverse through graph and mark nodes that node1 can reach
        reachAllDfs(graph, 1, visited);
        for(boolean temp: visited){
            if(!temp){
                return -1;
            }
        }
        return 1;
    }
    public void reachAllDfs(Graph graph, int cur, boolean[] visited){
        if(cur < 1 || cur > graph.size()){
            return;
        }
        if(visited[cur - 1]){
            return;
        }
        visited[cur - 1] = true;
        for(Edge edge: graph.neighbors(cur)){
            reachAllDfs(graph, edge.to, visited);
        }
    }


    /**
     * description: TODO 127 给定一个由 1（陆地）和 0（水）组成的矩阵，岛屿是被水包围，在矩阵中恰好拥有一个岛屿，假设组成岛屿的陆地边长都为 1，请计算岛屿的周长。岛屿内部没有水域。
     * create time: Dec 10 2024 10:03
     */
//    public int findPerimeter(int[][] grid){
//
//    }



}
