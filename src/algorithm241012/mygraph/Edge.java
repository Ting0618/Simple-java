package algorithm241012.mygraph;

public class Edge {
    // 存储相邻节点及边的权重
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
