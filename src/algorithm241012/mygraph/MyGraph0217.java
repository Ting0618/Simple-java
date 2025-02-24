package algorithm241012.mygraph;

import java.util.*;

/**
 * @author tingwong
 */
public class MyGraph0217 {
    /**
     * @Description TODO find all paths from root/ (or other node) to target, graph might have cycle
     * @Date 2025/2/18 11:27
     **/
    public void allPathDfs(Map<Integer, List<Integer>> graph, int startNode, int target, boolean[] onPath,
                           List<Integer> path, List<List<Integer>> res){
        if(!graph.containsKey(startNode) || !graph.containsKey(target)){
            return;
        }
        if(onPath[startNode]){
            return;
        }
        // onPath is used to record during this traversal which nodes are visited, these nodes form a path, using onPath to see
        // if these node are used, if a node is used which means this node is a cycle, we need to stop here.
        onPath[startNode] = true;
        path.add(startNode);
        if(startNode == target){
            res.add(new ArrayList<>(path));
            // here we need to manually backtrack, cause after executing this, it would return to previous layer of recursion,
            // then execute for iteration, we need to ensure path is its previous status
            path.removeLast();
            onPath[startNode] = false;
            return;
        }
        for(Integer item: graph.get(startNode)){
            // if we don't use if here, we can also get the correct result, while it's less efficient, code would enter next
            // level of recursion then return.
            if(!onPath[item]){
                allPathDfs(graph, item, target, onPath, path, res);
            }
        }
        onPath[startNode] = false;
        path.removeLast();
    }



    /**
     * @Description TODO traverse graph by bfs approach
     * @Date 2025/2/18 13:17
     **/
    public List<Integer> traverseGraphBfs(int[][] graph){
        int n = graph.length;
        Deque<Integer> queue = new LinkedList<>();
        boolean[] used = new boolean[n];
        List<Integer> res = new ArrayList<>();
        int step = 0;
        // I'd like to traverse from 0, so I put 0 into the queue first
        queue.offer(0);
        used[0] = true;
        while (!queue.isEmpty()){
            int pop = queue.poll();
            res.add(pop);
            step++;
            for(int i = 0; i < n; i++){
                // this is an array, if a node has neighbors, its value is 1 or 0
                if(graph[pop][i] == 1 && !used[i]){
                    // don't forget to make used[i] equals true
                    used[i] = true;
                    queue.offer(i);
                }
            }
        }
        return res;
    }

    boolean cycle = false;
    // we can abstract it as a graph problem, then check if it has a cycle
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        boolean[] visited = new boolean[graph.size()];
        boolean[] onPath = new boolean[graph.size()];
        // 1. this is a graph, we need to traverse all nodes, some nodes they are probably independent
        for (int i = 0; i < numCourses; i++) {
            // 2. skip the node that has already been visited
            if (!visited[i]) {
                hasCycle(graph, i, visited, onPath);
            }
        }
        return !cycle;
    }
    // construct the array as a graph
    public Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // initialize key
        for(int i = 0; i < numCourses; i++){
            graph.put(i, new LinkedList<Integer>());
        }
        for(int[] temp: prerequisites){
            graph.get(temp[1]).add(temp[0]);
        }
        return graph;
    }
    // traverse the graph to see if it has a cycle
    public void hasCycle(Map<Integer, List<Integer>> graph, int node, boolean[] visited, boolean[] onPath){
        // in one path this node appear twice, it must be because it has a cycle
        if(onPath[node]){
            cycle = true;
            return;
        }
        // it is cycle or this node has been visited or in this path it has been visited
        if(cycle || visited[node]){
            return;
        }
        onPath[node] = true;
        visited[node] = true;
        for(Integer neighbor: graph.get(node)){
            hasCycle(graph, neighbor, visited, onPath);
            // 4. we don't need to check visited[neighbor], visited[] applies to the entire traversal process,
            // in different paths we might use the same node, in cycle detection, we're supposed to focus on
            // if onPath[neighbor] == true rather than if a node is visited

            // if(!visited[neighbor]){
            // hasCycle(graph, neighbor, visited, onPath);
            // }
        }
        onPath[node] = false;
    }

    /**
     * @Description TODO check if has a cycle by bfs approach
     * @Date 2025/2/19 12:31
     **/
    public boolean hasACycle(int numCourses, int[][] prerequisites){
        boolean hasCycle = false;
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        Deque<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        int count = 0;
        // initialize inDegree array
        for(int[] item: prerequisites){
            int to = item[0];
            inDegree[to] += 1;
        }
        // put nodes that in degree is 0 into queue
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                count++;
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int node = queue.poll();
            // in degree -1
            for(int i: graph.get(node)){
                inDegree[i] -= 1;
            }
            for(int in: inDegree){
                if(in == 0){
                    count++;
                    queue.offer(in);
                }
            }
        }
        return !(count == numCourses);
    }
}
