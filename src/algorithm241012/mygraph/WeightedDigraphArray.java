package algorithm241012.mygraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ting
 * 有向加权图，邻接矩阵实现
 */
public class WeightedDigraphArray implements Graph{



    // 邻接矩阵，matrix[from][to] 存储从节点 from 到节点 to 的边的权重, 0 表示没有连接

    private final int[][] matrix;

    /**
     * description: TODO 构造方法
     * create time: Nov 24 2024 11:19
     */
    public WeightedDigraphArray(int n) {
        matrix = new int[n][n];
    }

    /**
     * description: TODO 增，添加一条带权重的有向边，复杂度 O(1)
     * create time: Nov 24 2024 11:19
     */
    @Override
    public void addEdge(int from, int to, int weight){
        matrix[from][to] = weight;
    }

    /**
     * description: TODO 删，删除一条有向边，复杂度 O(1)
     * create time: Nov 24 2024 11:21
     */
    @Override
    public void removeEdge(int from, int to){
        matrix[from][to] = 0;
    }

    /**
     * description: TODO 查，判断两个节点是否相邻，复杂度 O(1)
     * create time: Nov 24 2024 11:23
     */
    @Override
    public boolean hasEdge(int from, int to){
        return matrix[from][to] != 0;
    }

    /**
     * description: TODO 查，返回一条边的权重，复杂度 O(1)
     * create time: Nov 24 2024 11:25
     */
    @Override
    public int weight(int from, int to) {
        return matrix[from][to];
    }

    /**
     * description: TODO  查，返回某个节点的所有邻居节点，复杂度 O(V)
     * create time: Nov 24 2024 11:26
     */
    @Override
    public List<Edge> neighbors(int v){
        List<Edge> res = new ArrayList<>();
        for (int i = 0; i < matrix[v].length; i++) {
            if (matrix[v][i] > 0) {
                res.add(new Edge(i, matrix[v][i]));
            }
        }
        return res;
    }

    @Override
    public int size() {
        return matrix.length;
    }

}
