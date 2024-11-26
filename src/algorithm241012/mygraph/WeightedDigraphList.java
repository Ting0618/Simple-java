package algorithm241012.mygraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ting
 * 有向加权图，邻接表实现
 */
public class WeightedDigraphList implements Graph{

    private final Map<Integer, List<Edge>> graph;

    /**
     * description: TODO 构造方法
     * create time: Nov 24 2024 10:38
     */
    public WeightedDigraphList() {
        // 我们这里简单起见，建图时要传入节点总数，这其实可以优化
        // 比如把 graph 设置为 Map<Integer, List<Edge>>，就可以动态添加新节点了
        graph = new HashMap<>();
    }

    /**
     * description: TODO 增，添加一条带权重的有向边，复杂度 O(1)
     * create time: Nov 24 2024 10:40
     */
    public void addEdge(int from, int to, int weight) {
        graph.get(from).add(new Edge(to, weight));
    }

    /**
     * description: TODO 删，删除一条有向边，复杂度 O(V)
     * create time: Nov 24 2024 10:48
     * 是要删除graph【from】里面节点为to的那个节点
     */
    public void removeEdge(int from, int to) {
        List<Edge> temp = graph.get(from);
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).to == to){
                graph.get(from).remove(i);
                break;
            }
        }
    }

    /**
     * description: TODO  查，判断两个节点是否相邻，复杂度 O(V)
     * create time: Nov 24 2024 10:51
     */
    public boolean hasEdge(int from, int to) {
        for (Edge temp : graph.get(from)) {
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
        for (Edge temp : graph.get(from)) {
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
        return graph.get(v);
    }

    /**
     * description: TODO get size
     * create time: Nov 25 2024 09:28
     */
    public int size(){
        return graph.size();
    }

}
