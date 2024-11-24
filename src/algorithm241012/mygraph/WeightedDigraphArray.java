package algorithm241012.mygraph;

/**
 * @author ting
 * 有向加权图，邻接矩阵实现
 */
public class WeightedDigraphArray {

    public static class Edge {
        // 存储相邻节点及边的权重
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
