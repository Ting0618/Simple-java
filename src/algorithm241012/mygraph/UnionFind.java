package algorithm241012.mygraph;

/**
 * @author ting
 */
public class UnionFind {
    /** 记录连通分量 */
    private int count;

    /** 节点 x 的父节点是 parent[x] */
    private final int[] parent;

    /** 新增一个数组记录树的“重量” */
    private final int[] size;

    /**
     * description: TODO 构造函数，n 为图的节点总数
     */
    public UnionFind(int n) {
        // 一开始互不连通
        this.count = n;
        // 父节点指针初始指向自己
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * description: TODO 如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    /**
     * description: TODO 返回某个节点 x 的根节点
     */
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * description: TODO 返回当前的连通分量个数
     */
    public int count() {
        return count;
    }

    /**
     * description: TODO 判断p q是否相连
     */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
}
