package algorithm241012.mygraph;

import javax.swing.text.html.parser.Entity;
import java.util.*;

/**
 * @author tingwong
 */
public class MyGraph0117 {
    /**
     * @Author tingwong
     * @Description TODO traverse a graph
     * @Date 2025/1/17 10:14
     **/
    public void traverseGraph(Map<Integer, List<Integer>> graph){
        int len = graph.size();
        boolean[] visited = new boolean[len];
        traverseGraphDfs(graph, visited, 0);
    }
    public void traverseGraphDfs(Map<Integer, List<Integer>> graph, boolean[] visited, int id){
        if(graph.isEmpty()){
            return;
        }
        if(!visited[id]){
            System.out.println(id);
            // it should be here to set this node true
            visited[id] = true;
            List<Integer> items = graph.get(id);
            for(Integer item: items){
                if(!visited[item]){
//                    visited[item] = true;
                    traverseGraphDfs(graph, visited, item);
                }
            }
        }
    }
    public void traverseGraphBfs(Map<Integer, List<Integer>> graph, int id){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        queue.offer(id);
        // set it to true when enter the queue
        visited[id] = true;
        while (!queue.isEmpty()){
            id = queue.poll();
            System.out.println(id);
            List<Integer> items = graph.get(id);
            for(Integer item: items){
                if(!visited[item]){
                    // at this point we need to set it to true
                    visited[item] = true;
                    queue.offer(item);
                }
            }
        }
    }

    /**
     * @Author tingwong
     * @Description TODO record all paths
     * @Date 2025/1/17 11:12
     **/
    public List<List<Integer>> allPathsSourceTarget(Map<Integer, List<Integer>> graph, int startNode, int endNode){
        List<List<Integer>> res = new LinkedList<>();

        return res;
    }
    public void allPathDfs(Map<Integer, List<Integer>> graph, int startNode, int endNode, boolean[] onPath, List<Integer> path, List<List<Integer>> res){
        if(!graph.containsKey(startNode) || !graph.containsKey(endNode)){
            return;
        }
        if(onPath[startNode]){
            return;
        }
        if(startNode == endNode){
            res.add(new ArrayList<>(path));
            return;
        }
        onPath[startNode] = true;
        path.add(startNode);
        for(Integer item: graph.get(startNode)){
            if(!onPath[item]){
                allPathDfs(graph, item, endNode, onPath, path, res);
            }
        }
        onPath[startNode] = false;
        path.removeLast();
    }

    public void allPathDfs(int[][] graph, int startNode, int endNode, boolean[] onPath, List<Integer> path, List<List<Integer>> res){
        if(onPath[startNode]){
            return;
        }
        if(startNode == endNode){
            path.add(startNode);
            res.add(new ArrayList<>(path));
            path.removeLast();
            return;
        }
        path.add(startNode);
        onPath[startNode] = true;
        for(int item: graph[startNode]){
            if(!onPath[item]){
                allPathDfs(graph, item, endNode, onPath, path, res);
            }
        }
        onPath[startNode] = false;
        path.removeLast();
    }

    /**
     * @Description TODO course schedule
     * @Date 2025/1/18 10:13
     **/
    boolean hasCycle = false;
    public boolean canFinish(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        boolean[] onPath = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++){
            traversePath(graph, i, onPath, visited);
        }
        return !hasCycle;
    }

    /**
     * @Description TODO build a Map<Integer, List<Integer>> graph
     * @Date 2025/1/18 09:40
     **/
    public Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < numCourses; i++){
            graph.put(i, new LinkedList<>());
        }
        for (int[] item : prerequisites) {
            int from = item[0];
            int to = item[1];
            graph.get(from).add(to);
        }
        return graph;
    }
    /**
     * @Description TODO build a adjacency matrix graph
     **/
    public int[][] buildGraph2(int numCourses, int[][] prerequisites){
        int[][] graph = new int[numCourses][numCourses];
        for(int[] item: prerequisites){
            int from = item[0];
            int to = item[1];
            graph[from][to] = 1;
        }
        return graph;
    }

    /**
     * @Description TODO determine if exists a cycle
     * @Date 2025/1/18 09:42
     **/
    public void traversePath(Map<Integer, List<Integer>> graph, int start, boolean[] onPath, boolean[] visited){
        if(!graph.containsKey(start)){
            return;
        }
        if(onPath[start] ){
            hasCycle = true;
            return;
        }
        if(visited[start] || hasCycle){
            return;
        }
        visited[start] = true;
        onPath[start] = true;
        for(Integer item: graph.get(start)){
            // it is wrong to be here, because if set it to true, when execute to visited[start], it is always true
//            visited[item] = true;
            traversePath(graph, item, onPath, visited);
        }
        onPath[start] = false;
    }


    /**
     * @Description TODO topological sorting by Bfs, it has two functions -- check if a cycle exist and given topological sorting
     * @Date 2025/1/18 11:33
     **/
    public boolean canFinish2(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // create an inDegree array
        int[] inDegree = new int[numCourses];
        for(int[] edge: prerequisites){
            int to = edge[1];
            inDegree[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        // add node that inDegree is 0 into queue
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }
        // starts traverse
        int count = 0;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            count++;
            for(Integer neighbor: graph.get(cur)){
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }
        return count == numCourses;
    }
    



}
