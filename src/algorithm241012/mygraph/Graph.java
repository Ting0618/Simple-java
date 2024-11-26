package algorithm241012.mygraph;

import java.util.List;

interface Graph {
    /**
     * description: TODO 添加一条边（带权重）
     * create time: Nov 25 2024 12:26
     */
    void addEdge(int from, int to, int weight);

    /**
     * description: TODO 删除一条边
     * create time: Nov 25 2024 12:27
     */
    void removeEdge(int from, int to);

    /**
     * description: TODO 判断两个节点是否相邻
     * create time: Nov 25 2024 12:27
     */
    boolean hasEdge(int from, int to);

    /**
     * description: TODO 返回一条边的权重
     * create time: Nov 25 2024 12:27
     */
    int weight(int from, int to);

    /**
     * description: TODO 返回某个节点的所有邻居节点和对应权重
     * create time: Nov 25 2024 12:27
     */
    List<Edge> neighbors(int v);

    /**
     * description: TODO 返回节点总数
     * create time: Nov 25 2024 12:27
     */
    int size();
}
