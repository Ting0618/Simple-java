package algorithm241012.mygraph;

import java.util.*;

/**
 * @author ting
 */
public class MyGraph {
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
        for(int i = 0; i < numCourses; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] node: prerequisites){
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
        while(!queue.isEmpty()){
            int node = queue.poll();
            count++;
            // Reduce the in-degree of nodes related to node by 1.
            for(int i: graph.get(node)){
                inDegree[i]--;
                if(inDegree[i] == 0){
                    queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }


}
