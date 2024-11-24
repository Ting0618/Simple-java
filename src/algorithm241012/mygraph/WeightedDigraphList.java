package algorithm241012.mygraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ting
 * 有向加权图，邻接表实现
 */
public class WeightedDigraphList {
    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private List<Edge>[] graph;

    /**
     * description: TODO 构造方法
     * create time: Nov 24 2024 10:38
     */
    public WeightedDigraphList(int n) {
        // 我们这里简单起见，建图时要传入节点总数，这其实可以优化
        // 比如把 graph 设置为 Map<Integer, List<Edge>>，就可以动态添加新节点了
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    /**
     * description: TODO 增，添加一条带权重的有向边，复杂度 O(1)
     * create time: Nov 24 2024 10:40
     */
    public void addEdge(int from, int to, int weight) {
        graph[from].add(new Edge(to, weight));
    }

    /**
     * description: TODO 删，删除一条有向边，复杂度 O(V)
     * create time: Nov 24 2024 10:48
     * 是要删除graph【from】里面节点为to的那个节点
     */
    public void removeEdge(int from, int to) {
        for (int i = 0; i < graph[from].size(); i++) {
            if (graph[from].get(i).to == to) {
                graph[from].remove(i);
                break;
            }
        }
    }

    /**
     * description: TODO  查，判断两个节点是否相邻，复杂度 O(V)
     * create time: Nov 24 2024 10:51
     */
    public boolean hasEdge(int from, int to) {
        for (Edge temp : graph[from]) {
            if (temp.to == to) {
                return true;
            }
        }
        return false;
    }

    /**
     * description: TODO  查，返回一条边的权重，复杂度 O(V)
     * create time: Nov 24 2024 10:54
     */
    public int weight(int from, int to) {
        for (Edge temp : graph[from]) {
            if (temp.to == to) {
                return temp.weight;
            }
        }
        return 0;
    }

    /**
     * description: TODO 查，返回某个节点的所有邻居节点，复杂度 O(1)
     * create time: Nov 24 2024 11:07
     */
    public List<Edge> neighbors(int v) {
        return graph[v];
    }

}
