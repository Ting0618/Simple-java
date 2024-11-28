package algorithm241012.mygraph;


class State {
    // 图结构的 BFS 遍历，从节点 s 开始进行 BFS，且记录路径的权重和
    // 每个节点自行维护 State 类，记录从 s 走来的权重和
    /** 当前节点 ID */
    int node;
    /** 从起点 s 到当前节点的权重和 */
    int weight;

    public State(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
