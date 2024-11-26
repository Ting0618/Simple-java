package algorithm241012.mygraph;

import java.util.ArrayList;
import java.util.List;

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
     * description: TODO lc797
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
    public void allPathsSourceTarget3(Graph graph, int id, List<Integer> path, List<List<Integer>> res){
        path.add(id);
        // collect result
        if(id == graph.size() - 1){
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        for(Edge e: graph.neighbors(id)){
            allPathsSourceTarget3(graph, e.to, path, res);
        }
        path.removeLast();
    }


}
